package ru.store.controllers.callService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class CallServiceSearchController {

    @RequestMapping(value = "/callService/search", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request) {
        System.out.println(request.getParameter("byKeyword"));
        System.out.println(request.getParameter("byCompany"));
        System.out.println(request.getParameter("byAddress"));

        model.addAttribute("prefix", "");

        return "callService/callServiceSearch";
    }

    @RequestMapping(value = "/callService/search", method = RequestMethod.GET)
    public String searchGET() {
        return "redirect:/callService";
    }

}
