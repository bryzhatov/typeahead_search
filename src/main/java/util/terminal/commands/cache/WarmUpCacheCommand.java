package util.terminal.commands.cache;

import service.search.SearchService;
import util.terminal.commands.Command;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class WarmUpCacheCommand extends Command {

    @Override
    public void handle(String input, SearchService searchService) {
        if (input.equals("-w")) {
            long start = System.currentTimeMillis();
            searchService.warmUpCache();
            long end = System.currentTimeMillis();
            System.out.println("Warmup cache: " + (end - start) + " ms\n");
        } else {
            handleNext(input, searchService);
        }
    }
}
