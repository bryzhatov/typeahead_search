package util.terminal.commands.cache;

import service.search.SearchService;
import util.terminal.commands.Command;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class ClearCacheCommand extends Command {

    @Override
    public void handle(String input, SearchService searchService) {
        if (input.equals("-c")) {
            System.out.println("Clean cache: ["+ searchService.getCache().getSize()+"]\n");
            searchService.getCache().clear();
        } else {
            handleNext(input, searchService);
        }
    }
}
