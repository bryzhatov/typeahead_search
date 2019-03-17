package service.search;

import cache.HashCache;
import entity.Meta;

import java.util.List;
import java.util.Map;

public interface SearchService {
    boolean add(String word, Meta meta);

    Map<String, List<Meta>> get(String word, int count);

    void warmUpCache();

    HashCache getCache();
}
