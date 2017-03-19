package ru.store.controllers.mainAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainAdminController {

    @RequestMapping(value = "/mainAdmin", method = RequestMethod.GET)
    public String adminPage() {
        return "redirect:/mainAdmin/companies";
    }

}
