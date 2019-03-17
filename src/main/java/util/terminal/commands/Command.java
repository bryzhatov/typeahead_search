package util.terminal.commands;

import service.search.SearchService;
import util.terminal.commands.cache.CacheStatusCommand;
import util.terminal.commands.cache.ClearCacheCommand;
import util.terminal.commands.cache.PrintCacheCommand;
import util.terminal.commands.cache.WarmUpCacheCommand;
import util.terminal.commands.service.FindCommand;
import util.terminal.commands.service.PrintAllCommand;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public abstract class Command {
    protected Command next;

    public abstract void handle(String input, SearchService searchService);

    public void setNext(Command nextCommand) {
        this.next = nextCommand;
    }

    protected void handleNext(String input, SearchService searchService) {
        if (next != null) {
            next.handle(input, searchService);
        }
    }

    public static Command build() {
        CacheStatusCommand statusChain = new CacheStatusCommand();
        ClearCacheCommand clearCacheCommandChain = new ClearCacheCommand();
        PrintCacheCommand printCacheCommand = new PrintCacheCommand();
        PrintAllCommand printAllCommandCain = new PrintAllCommand();
        WarmUpCacheCommand warmUpCommand = new WarmUpCacheCommand();
        FindCommand findCommand = new FindCommand();

        statusChain.setNext(printAllCommandCain);
        printAllCommandCain.setNext(warmUpCommand);
        warmUpCommand.setNext(printCacheCommand);
        printCacheCommand.setNext(clearCacheCommandChain);
        clearCacheCommandChain.setNext(findCommand);

        return statusChain;
    }
}
