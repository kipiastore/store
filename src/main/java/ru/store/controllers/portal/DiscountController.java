package ru.store.controllers.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DiscountController {

    @RequestMapping(value = "/discount", method = RequestMethod.GET)
    public String discount() {
        return "portal/discount";
    }

}
