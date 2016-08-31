package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by User on 30.08.2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String main() {
        return "login/index";
    }

    @RequestMapping(value = "/login/action", method = RequestMethod.POST)
    public String action(HttpServletRequest request, HttpServletResponse response) {
        boolean isAdmin = false;
        boolean isManager = false;
        boolean isUser = false;
        boolean isLogged = false;
        boolean isNotRobot = false;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println(login);
        System.out.println(password);

        // check isNotRobot // TODO: 30.08.2016
        // check isLogged
        // get user type

        // for test
        //isNotRobot = true;
        //isLogged = true;
        //isAdmin = true;
        //

        if (isNotRobot && isLogged) {
            if (isAdmin)
                return "redirect:/admin";
            if (isManager)
                return "redirect:/manager";
            if (isUser)
                return "redirect:/portal";
        }
        String msg = "Вы ввели неверные данные.";
        return "redirect:/login?msg=" + msg;
    }

}
