package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CallInfoController {

    @RequestMapping(value = "/callinfo", method = RequestMethod.GET)
    public String callCenter() {
        return "portal/callinfo";
    }

}
