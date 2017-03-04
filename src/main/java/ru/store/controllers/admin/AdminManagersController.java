package ru.store.controllers.admin;

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
 *
 */
@Controller
public class AdminManagersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/managers", method = RequestMethod.GET)
    public ModelAndView managers() {
        ModelAndView modelAndView = new ModelAndView();
        AdminUsersController.Model model = new AdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addmanager", method = RequestMethod.POST)
    public ModelAndView createManager(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.createUser(user, "ROLE_MANAGER");
            modelAndView.addObject("successMessage", "Менеджер успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        AdminUsersController.Model model = new AdminUsersController.Model();
        model.addingUserJson = user.toString();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/updatemanager", method = RequestMethod.POST)
    public ModelAndView updateManager(@RequestParam("usernameHidden") String username,
                                      @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            user.setUsername(username); // user will not contains the username because a input username is disabled
            userService.updateUser(user, "ROLE_MANAGER");
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
        }
        AdminUsersController.Model model = new AdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletemanager", method = RequestMethod.POST)
    public ModelAndView deleteManager(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUser(username);
            modelAndView.addObject("successMessage", "Менеджер успешно удален.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        AdminUsersController.Model model = new AdminUsersController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    private void loadPage(AdminUsersController.Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 5;
        AdminUsersController.loadUsers(model, userService, "ROLE_MANAGER");
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/managers");
        modelAndView.addObject("prefix", "");
    }

    @RequestMapping(value = "/admin/addmanager", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/managers";
    }
    @RequestMapping(value = "/admin/updatemanager", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/managers";
    }
    @RequestMapping(value = "/admin/deletemanager", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/admin/managers";
    }

}
