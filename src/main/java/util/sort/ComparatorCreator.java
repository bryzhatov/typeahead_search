package util.sort;

import entity.Meta;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Bryzhatov
 * @since 11.12.2018
 */
public class ComparatorCreator {

    public Comparator<Map.Entry<String, List<Meta>>> createComparator(SortMode sortMode) {
        switch (sortMode) {
            case OCCURRENCE_TOP:
                return ComparatorHolder.OCCURRENCE_TOP;
            default:
                return null;
        }
    }

    private static class ComparatorHolder {
        private static final Comparator<Map.Entry<String, List<Meta>>> OCCURRENCE_TOP = new Comparator<Map.Entry<String, List<Meta>>>() {
            @Override
            public int compare(Map.Entry<String, List<Meta>> entry1, Map.Entry<String, List<Meta>> entry2) {
                int sizeKey1 = entry1.getValue().size();
                int sizeKey2 = entry2.getValue().size();

                return sizeKey1 > sizeKey2 ? -1 : sizeKey1 == sizeKey2 ? entry1.getKey().compareTo(entry2.getKey()) : 1;
            }
        };
    }
}
