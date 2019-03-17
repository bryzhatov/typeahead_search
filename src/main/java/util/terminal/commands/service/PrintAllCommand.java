package util.terminal.commands.service;

import service.search.SearchService;
import util.terminal.commands.Command;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class PrintAllCommand extends Command {

    @Override
    public void handle(String input, SearchService searchService) {

        StringBuilder builder = new StringBuilder();

        if (input.equals("-h")) {
            builder
                    .append("\n[common]\n")
                    .append("-h (help)\n")
                    .append("\n[cache]\n")
                    .append("-s (cache status)\n")
                    .append("-w (warmup cache)\n")
                    .append("-c (clear cache)\n")
                    .append("-p (print cache)]\n");
            System.out.println(builder);
        } else {
            handleNext(input, searchService);
        }
    }
}
