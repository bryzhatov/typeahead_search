package service.indexing;

import service.search.SearchService;

/**
 * @author Dmitry Bryzhatov
 * @since 11.12.2018
 */
public interface IndexingService {
    SearchService indexing(String path, SearchService searchService);
}
