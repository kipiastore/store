package ru.store.api.portal;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.SearchRequest;
import ru.store.service.SearchRequestService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class SearchRequestLoader {

    @Autowired
    private SearchRequestService searchRequestService;

    @RequestMapping(value = "/api/portal/resource/v1/search/{value}", method = RequestMethod.GET)
    public List<String> getSearchRequests(@PathVariable String value) {
        List<String> result = new ArrayList<>();
        for (SearchRequest searchRequest : searchRequestService.findSearchRequests(value)) {
            result.add(searchRequest.getValue());
        }
        return result;
    }
}
