package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/search/1", method = RequestMethod.GET)
    public String search1() {
        return "search";
    }

    @RequestMapping(value = "/search/2", method = RequestMethod.GET)
    public String search2() {
        return "search";
    }

    @RequestMapping(value = "/search/3", method = RequestMethod.GET)
    public String search3() {
        return "search";
    }

}
