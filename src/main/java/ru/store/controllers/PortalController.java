package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.models.BestResourcesItem;
import ru.store.controllers.models.HomeModel;
import ru.store.controllers.models.MenuItem;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PortalController {

    /**
     *
     * HOME
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) {

        List<MenuItem> menuItemList = new ArrayList<>();
        MenuItem menuItem;
        for (int i = 0; i < 30; i++) {
            menuItem = new MenuItem(i + "", "Дизайн - Графика - Фото", (int) (Math.random() * 3000));
            menuItemList.add(menuItem);
        }

        Map<String, List<BestResourcesItem>> bestResourcesItemMap = new HashMap<>();
        List<BestResourcesItem> bestResourcesItemList;
        BestResourcesItem resourcesItem;
        for (int k = 0; k < 3; k++) {
            bestResourcesItemList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                resourcesItem = new BestResourcesItem(i + "", "Дизайн - Графика - Фото", i + "");
                bestResourcesItemList.add(resourcesItem);
            }
            bestResourcesItemMap.put(k + "", bestResourcesItemList);
        }

        HomeModel homeModel = new HomeModel(menuItemList, bestResourcesItemMap);
        model.addAttribute("homeModel", homeModel);
        return "portal/home";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        return main(request, model);
    }


    /**
     *
     * DISCOUNTS
     *
     */
    @RequestMapping(value = "/discounts", method = RequestMethod.GET)
    public String discounts() {
        return "portal/discounts";
    }


    /**
     *
     * COMPANY
     *
     */
    @RequestMapping(value = "/company/*", method = RequestMethod.GET)
    public String company() {
        return "portal/company";
    }


    /**
     *
     * CALLCENTER
     *
     */
    @RequestMapping(value = "/callcenter", method = RequestMethod.GET)
    public String callcenter() {
        // need add log4j. here and in another places todo !!!
        return "portal/callcenter";
    }


    /**
     *
     * SEARCH
     *
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "portal/search";
    }
    @RequestMapping(value = "/search/1", method = RequestMethod.GET)
    public String search1() {
        return "portal/search";
    }
    @RequestMapping(value = "/search/2", method = RequestMethod.GET)
    public String search2() {
        return "portal/search";
    }
    @RequestMapping(value = "/search/3", method = RequestMethod.GET)
    public String search3() {
        return "portal/search";
    }


    /**
     *
     * LOGIN
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "portal/login";
    }
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
        String portalPage = "redirect:/portal/personalarea";
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
