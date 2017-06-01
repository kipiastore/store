package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.SearchRequestDAO;
import ru.store.entities.SearchRequest;

import java.util.List;

/**
 *
 */
@Service
public class SearchRequestService {

    @Autowired
    private SearchRequestDAO searchRequestDAO;

    public void createSearchRequest(SearchRequest searchRequest) {
        searchRequest.setValue(searchRequest.getValue().trim());
        searchRequestDAO.createSearchRequest(searchRequest);
    }

    public void updateSearchRequest(SearchRequest searchRequest) {
        searchRequestDAO.updateSearchRequest(searchRequest);
    }

    public SearchRequest findSearchRequest(String value) {
        return searchRequestDAO.findSearchRequest(value);
    }

    public List<SearchRequest> findSearchRequests(String value) {
        return searchRequestDAO.findSearchRequests(value);
    }

}
