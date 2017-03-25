package ru.store.controllers.designer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DesignerController {

    @RequestMapping(value = "/designer", method = RequestMethod.GET)
    public String adminPage() {
        return "redirect:/designer/positions";
    }

}
