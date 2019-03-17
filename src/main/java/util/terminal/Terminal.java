package util.terminal;

import service.indexing.IndexingService;
import service.indexing.WordIndexingService;
import service.search.SearchService;
import service.search.TreeMapSearchService;
import util.terminal.commands.Command;

import java.util.Scanner;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class Terminal {
    private static final String PATH_FILE = "./src/resources/book.txt";

    public void open() {
        IndexingService indexingService = new WordIndexingService();

        long start = System.currentTimeMillis();
        SearchService searchService = indexingService
                .indexing(PATH_FILE, new TreeMapSearchService(40, 0.25));
        long end = System.currentTimeMillis();

        System.out.println("Indexing file: " + (end - start) + " ms");

        Command chain = Command.build();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter: ");
            chain.handle(scanner.nextLine(), searchService);
        }
    }
}
