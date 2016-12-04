package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CallInfoController {

    @RequestMapping(value = "/callinfo", method = RequestMethod.GET)
    public ModelAndView callCenter() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/callinfo");
        return modelAndView;
    }

}
