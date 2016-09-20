package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "portal/search";
    }

    @RequestMapping(value = "/search/1", method = RequestMethod.GET)
    public String search1() {
        return "portal/search";
    }

    @RequestMapping(value = "/search/2", method = RequestMethod.GET)
    public String search2() {
        return "portal/search";
    }

    @RequestMapping(value = "/search/3", method = RequestMethod.GET)
    public String search3() {
        return "portal/search";
    }

}
