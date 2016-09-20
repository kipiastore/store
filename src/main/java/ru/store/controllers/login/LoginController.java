package ru.store.controllers.login;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login/index");
        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg", "You do not have permission to access this page!");
        }

        model.setViewName("error/403");
        return model;

    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        System.out.println(exception);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }


    /*
    @RequestMapping(value = "/login/action", method = RequestMethod.POST)
    public String action(HttpServletRequest request) {

        String errorMsg = "Вы ввели неверные данные!"; // get string from resource bundle
        try {
            // encode string to url format. "Тест" -> %D0%A2%D0%B5%D1%81%D1%82
            errorMsg = URLEncoder.encode(new String(errorMsg.getBytes("UTF-8"), "UTF-8"), "UTF-8");
        } catch (Exception ex) {
            errorMsg = "Incorrect data!"; // get string from resource bundle
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

        return null;

        //request.getParameter("msg") == null ? "" : URLDecoder.decode(request.getParameter("msg"), "UTF-8")
    }
    */
}
