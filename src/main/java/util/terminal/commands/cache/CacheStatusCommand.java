package util.terminal.commands.cache;

import service.search.SearchService;
import util.terminal.commands.Command;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class CacheStatusCommand extends Command {

    @Override
    public void handle(String input, SearchService searchService) {
        if (input.equals("-s")) {
            System.out.println("Status: " + searchService.getCache().getSize() + "/" + searchService.getCache().getCapacity()+"\n");
        } else {
            handleNext(input, searchService);
        }
    }
}
