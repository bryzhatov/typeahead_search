package util.terminal.commands.service;

import entity.Meta;
import formatter.ConsoleFormatter;
import service.search.SearchService;
import util.terminal.commands.Command;

import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Bryzhatov
 * @since 2018-12-14
 */
public class FindCommand extends Command {

    private static final int DEFAULT_PRINT_WORDS = 5;

    @Override
    public void handle(String input, SearchService searchService) {
        if (check(input)) {

            String[] params = input.split("\\s");
            int countPrint = DEFAULT_PRINT_WORDS;

            if (params.length == 2) {
                input = params[0];
                countPrint = Integer.valueOf(params[1]);
                if(countPrint < -1){
                    countPrint = DEFAULT_PRINT_WORDS;
                }
            }

            long start = System.nanoTime();
            Map<String, List<Meta>> answer = searchService.get(input, countPrint);
            long end = System.nanoTime();

            System.out.println(new ConsoleFormatter<>().format(answer));

            System.out.println("Answer for: " + (end - start) + " nano\n");
        } else {
            System.out.println("Incorrect format input: [word] [count found matches]\n");
        }
    }

    private boolean check(String input) {
        return input.matches("^([A-z]+)([\\s](-)?([\\d]+))?$");
    }
}
