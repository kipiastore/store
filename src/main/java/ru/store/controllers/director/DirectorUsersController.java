package ru.store.controllers.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.User;
import ru.store.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class DirectorUsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/director/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/adduser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.createUser(user, "ROLE_CLIENT");
            modelAndView.addObject("successMessage", "Пользователь успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        model.addingUserJson = user.toString();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/updateuser", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam("usernameHidden") String username, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            user.setUsername(username); // user will not contains the username because a input username is disabled
            userService.updateUser(user, "ROLE_CLIENT");
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/deleteuser", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUser(username);
            modelAndView.addObject("successMessage", "Пользователь успешно удален.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    private void loadPage(Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 7;
        loadUsers(model, userService, "ROLE_CLIENT");
        modelAndView.addObject("model", model);
        modelAndView.setViewName("director/users");
        modelAndView.addObject("prefix", "");
    }

    public static void loadUsers(Model model, UserService userService, String role) {
        List<Model.UserItem> userItems = new ArrayList<>();
        Model.UserItem userItem;
        List<User> users = userService.getUsers();
        List<User> usersFilteredByRole = new ArrayList<>();
        for (User user : users) {
            if (user.getUserRole().isEmpty()) {
                continue;
            }
            if (new ArrayList<>(user.getUserRole()).get(0).getRole().equals(role)) {
                userItem = new Model.UserItem();
                if (user.getFullName() != null && user.getFullName().length() > 24)
                    userItem.fullName = user.getFullName().substring(0, 24) + "..";
                else
                    userItem.fullName = user.getFullName();
                userItem.userName = user.getUsername();
                userItems.add(userItem);
                usersFilteredByRole.add(user);
            }
        }
        model.userItems = userItems;
        model.usersJson = usersFilteredByRole.toString();
    }

    @RequestMapping(value = "/director/adduser", method = RequestMethod.GET)
     public String redirect1() {
        return "redirect:/director/users";
    }
    @RequestMapping(value = "/director/updateuser", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/director/users";
    }
    @RequestMapping(value = "/director/deleteuser", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/director/users";
    }

    public static class Model {
        /**
         * 1 = companies
         * 2 = positions
         * 3 = partitions
         * 4 = users
         * 5 = managers
         * 6 = packages
         * 7 = reports
         */
        public int selectedPageNum;
        public List<UserItem> userItems;
        public String usersJson;
        public String addingUserJson;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<UserItem> getUserItems() {
            return userItems;
        }
        public String getUsersJson() {
            return usersJson;
        }
        public String getAddingUserJson() {
            return addingUserJson;
        }

        public static class UserItem {
            public String userName;
            public String fullName;

            public String getUserName() {
                return userName;
            }
            public String getFullName() {
                return fullName;
            }
        }
    }
}

