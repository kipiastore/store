package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    @RequestMapping(value = "/search/1", method = RequestMethod.GET)
    public ModelAndView search1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    @RequestMapping(value = "/search/2", method = RequestMethod.GET)
    public ModelAndView search2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    @RequestMapping(value = "/search/3", method = RequestMethod.GET)
    public ModelAndView search3() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

}
