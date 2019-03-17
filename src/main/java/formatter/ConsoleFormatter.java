package formatter;

import entity.Meta;

import java.util.List;
import java.util.Map;

public class ConsoleFormatter<T extends String> implements Formatter<T> {

    @Override
    public T format(Map<String, List<Meta>> locations) {
        StringBuilder builder = new StringBuilder();

        if (locations.size() == 0) {
            builder.append("Nothing found!");
        } else {
            builder.append("order: word (count) [column, row]\n\n");

            int i = 0;
            for (String s : locations.keySet()) {
                i++;
                builder.append(i).append(": ")
                        .append(s)
                        .append(" ");

                List<Meta> list = locations.get(s);
                builder.append("(").append(list.size()).append(")").append(" ");

                for (Meta meta : list) {
                    builder.append("[");
                    builder.append(meta.getColumn());
                    builder.append(":");
                    builder.append(meta.getRow());
                    builder.append("]").append(" ");
                }
                builder.append("\n");

            }
        }

        return (T) builder.toString();
    }
}
