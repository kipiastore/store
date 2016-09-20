package ru.store.controllers.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController {

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(Model model) {
        model.addAttribute("title", "Spring Security Custom Login Form for admin");
        model.addAttribute("message", "This is protected page!");
        return "manager/index";
    }

}
