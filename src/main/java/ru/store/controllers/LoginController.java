package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by User on 30.08.2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String main() {
        System.out.println("need add log4j"); //  here and in another places todo !!!
        return "login/index";
    }

    @RequestMapping(value = "/login/action", method = RequestMethod.POST)
    public String action(HttpServletRequest request) {
        String errorMsg = "Вы ввели неверные данные."; // get string from resource bundle
        try {
            // encode string to url format. "Тест" -> %D0%A2%D0%B5%D1%81%D1%82
            errorMsg = URLEncoder.encode(new String(errorMsg.getBytes("UTF-8"), "UTF-8"), "UTF-8");
        } catch (Exception ex) {
            errorMsg = "Incorrect data."; // get string from resource bundle
        }
        String adminPage = "redirect:/admin";
        String managerPage = "redirect:/manager";
        String portalPage = "redirect:/portal/personalArea";
        String incorrectData = "redirect:/login?msg=" + errorMsg;
        boolean isAdmin = false;
        boolean isManager = false;
        boolean isUser = false;
        boolean isLogged = false;
        boolean isNotRobot = false;

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty())
            return incorrectData;

        // check isNotRobot // TODO: 30.08.2016
        // check isLogged
        // get user type

        if (isNotRobot && isLogged) {
            if (isAdmin)
                return adminPage;
            if (isManager)
                return managerPage;
            if (isUser)
                return portalPage;
        }
        return incorrectData;
    }

}
