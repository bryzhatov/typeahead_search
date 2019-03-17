package service.search;

import cache.HashCache;
import entity.Meta;
import util.sort.ComparatorCreator;
import util.sort.SortMode;

import java.util.*;

public class TreeMapSearchService implements SearchService {
    private HashCache<String, TreeSet<Map.Entry<String, List<Meta>>>> cache;
    private TreeMap<String, List<Meta>> indexedWords;
    private SortMode sortMode;

    public TreeMapSearchService(int cacheCapacity, double clearFactor) {
        cache = new HashCache<>(cacheCapacity, clearFactor);
        this.sortMode = SortMode.OCCURRENCE_TOP;
        this.indexedWords = new TreeMap<>();
    }

    public TreeMapSearchService(int cacheCapacity, double clearFactor, SortMode sortMode) {
        this(cacheCapacity, clearFactor);
        this.sortMode = sortMode;
    }

    @Override
    public boolean add(String word, Meta meta) {
        if (word != null && meta != null) {
            return indexedWords.computeIfAbsent(word, k -> new ArrayList<>()).add(meta);
        }
        return false;
    }

    @Override
    public Map<String, List<Meta>> get(String word, int count) {
        if (word != null && (count > 0 || count == -1)) {

            word = word.toLowerCase();

            TreeSet<Map.Entry<String, List<Meta>>> inCache = cache.get(word);

            if (inCache == null) {
                String toWord = incrementWord(word);

                inCache = sort(indexedWords.subMap(word, true, toWord, false), sortMode, count);
                if (inCache.size() > 0) {
                    cache.cache(word, inCache);
                }
            }

            Map<String, List<Meta>> answer = new LinkedHashMap<>();
            int i = 0;
            for (Map.Entry<String, List<Meta>> entry : inCache) {
                if (count == -1 || i < count) {
                    answer.put(entry.getKey(), entry.getValue());
                    i++;
                } else {
                    break;
                }
            }

            return answer;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public HashCache getCache() {
        return cache;
    }

    public void warmUpCache() {
        for (int i = 'a'; i <= 'z'; i++) {
            get(Character.valueOf((char) i).toString(), -1);
        }
    }

    private String incrementWord(String fromWord) {
        char[] fromWordChars = fromWord.toCharArray();
        int lastSymbolIndex = fromWordChars.length - 1;

        fromWordChars[lastSymbolIndex] = (char) (fromWordChars[lastSymbolIndex] + 1);

        return new String(fromWordChars);
    }

    private TreeSet<Map.Entry<String, List<Meta>>> sort(SortedMap<String, List<Meta>> rawData, SortMode sortMode, int count) {
        TreeSet<Map.Entry<String, List<Meta>>> sortRawData =
                new TreeSet<>(ComparatorCreatorHolder.COMPARATOR_CREATOR.createComparator(sortMode));
        sortRawData.addAll(rawData.entrySet());

        return sortRawData;
    }

    private static class ComparatorCreatorHolder {
        private static final ComparatorCreator COMPARATOR_CREATOR = new ComparatorCreator();
    }
}
