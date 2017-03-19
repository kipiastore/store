package ru.store.controllers.mainAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.User;
import ru.store.service.UserService;

/**
 * Created by Akex on 17.03.2017.
 */
@Controller
public class MainAdminAdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/mainAdmin/admins", method = RequestMethod.GET)
    public ModelAndView managers() {
        ModelAndView modelAndView = new ModelAndView();
        MainAdminUsersController.Model model = new MainAdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/mainAdmin/addadmin", method = RequestMethod.POST)
    public ModelAndView createManager(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.createUser(user, "ROLE_ADMIN");
            modelAndView.addObject("successMessage", "Администратор успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        MainAdminUsersController.Model model = new MainAdminUsersController.Model();
        model.addingUserJson = user.toString();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/mainAdmin/updateadmin", method = RequestMethod.POST)
    public ModelAndView updateManager(@RequestParam("usernameHidden") String username,
                                      @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            user.setUsername(username); // user will not contains the username because a input username is disabled
            userService.updateUser(user, "ROLE_ADMIN");
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
        }
        MainAdminUsersController.Model model = new MainAdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/mainAdmin/deleteadmin", method = RequestMethod.POST)
    public ModelAndView deleteManager(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUser(username);
            modelAndView.addObject("successMessage", "Админ успешно удален.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        MainAdminUsersController.Model model = new MainAdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    private void loadPage(MainAdminUsersController.Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 4;
        MainAdminUsersController.loadUsers(model, userService, "ROLE_ADMIN");
        modelAndView.addObject("model", model);
        modelAndView.setViewName("mainAdmin/admins");
        modelAndView.addObject("prefix", "");
    }

    @RequestMapping(value = "/mainAdmin/addadmin", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/mainAdmin/admins";
    }
    @RequestMapping(value = "/mainAdmin/updateadmin", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/mainAdmin/admins";
    }
    @RequestMapping(value = "/mainAdmin/deleteadmin", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/mainAdmin/admins";
    }

}
