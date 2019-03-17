package util.terminal.commands.cache;

import service.search.SearchService;
import util.terminal.commands.Command;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class PrintCacheCommand extends Command {

    @Override
    public void handle(String input, SearchService searchService) {
        if (input.equals("-p")) {
            System.out.println(searchService.getCache().toString()+"\n");
        } else {
            handleNext(input, searchService);
        }
    }
}
