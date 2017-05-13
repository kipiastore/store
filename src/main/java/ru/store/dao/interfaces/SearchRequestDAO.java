package ru.store.dao.interfaces;

import ru.store.entities.SearchRequest;

import java.util.List;

/**
 *
 */
public interface SearchRequestDAO {

    void createSearchRequest(SearchRequest searchRequest);

    void updateSearchRequest(SearchRequest searchRequest);

    SearchRequest findSearchRequest(String value);

    List<SearchRequest> findSearchRequests(String value);

}
