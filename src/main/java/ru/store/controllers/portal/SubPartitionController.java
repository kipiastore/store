package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class SubPartitionController {

    @RequestMapping(value = "/subPartition/*", method = RequestMethod.GET)
    public ModelAndView subPartition() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("portal/subpartition");
        return modelAndView;
    }

}
