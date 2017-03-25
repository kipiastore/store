package ru.store.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Akex on 19.09.2016.
 */
@Controller
public class ClientController {

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView clientPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form for client");
        model.addObject("message", "This is protected page!");
        model.setViewName("client/index");

        return model;

    }

}
