package service.indexing;

import entity.Meta;
import service.search.SearchService;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WordIndexingService implements IndexingService {

    private static final Set<String> STOP_WORDS = new HashSet<>();

    static {
        STOP_WORDS.add("the");
        STOP_WORDS.add("and");
        STOP_WORDS.add("as");
        STOP_WORDS.add("an");
        STOP_WORDS.add("th");
        STOP_WORDS.add("a");
        STOP_WORDS.add("at");
        STOP_WORDS.add("in");
        STOP_WORDS.add("is");
        STOP_WORDS.add("am");
        STOP_WORDS.add("are");
    }

    @Override
    public SearchService indexing(String path, SearchService searchService) {
        StringBuilder wordBuilder = new StringBuilder();

        try (BufferedReader stream = new BufferedReader(new FileReader(new File(path)))) {

            int cursorIndex = -1;
            int startIndex = 0;
            int character = 0;
            int column = 1;
            int row = 1;
            int startRow = 1;

            while ((character = stream.read()) != -1) {
                cursorIndex++;

                if ((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z')) {

                    if (wordBuilder.length() == 0) {
                        startIndex = cursorIndex;
                        startRow = row;
                    }

                    wordBuilder.append((char) character);

                } else {
                    String word = wordBuilder.toString();

                    if (!STOP_WORDS.contains(word.toLowerCase()) && word.length() > 0) {
                        searchService.add(word.toLowerCase(), new Meta(column, startRow, startIndex));
                    }
                    wordBuilder.delete(0, wordBuilder.length());
                }

                if (character == '\n') {
                    column++;
                    row = 0;
                }

                row++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Problems with file.");
        }
        return searchService;
    }
}
