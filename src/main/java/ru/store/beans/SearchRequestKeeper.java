package ru.store.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.store.entities.SearchRequest;
import ru.store.service.SearchRequestService;

/**
 *
 */
@Component
public class SearchRequestKeeper {

    @Autowired
    private BadWordsFilter badWordsFilter;

    @Autowired
    private SearchRequestService searchRequestService;

    public void save(String requestString, int counterValue) {
        if (!badWordsFilter.hasBadWords(requestString)) {
            SearchRequest searchRequest = searchRequestService.findSearchRequest(requestString);
            if (searchRequest == null) {
                searchRequest = new SearchRequest();
                searchRequest.setCounter(counterValue);
                searchRequest.setValue(requestString);
                searchRequestService.createSearchRequest(searchRequest);
            } else {
                searchRequest.setCounter(searchRequest.getCounter() + 1);
                searchRequestService.updateSearchRequest(searchRequest);
            }
        }
    }

}
