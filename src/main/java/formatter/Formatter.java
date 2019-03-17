package formatter;

import entity.Meta;

import java.util.List;
import java.util.Map;

public interface Formatter<T> {
    T format(Map<String, List<Meta>> locations);
}
