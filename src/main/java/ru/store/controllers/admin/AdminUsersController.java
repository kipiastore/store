package ru.store.controllers.admin;

import org.hibernate.exception.ConstraintViolationException;
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
public class AdminUsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        model.selectedPageNum = 4;
        loadUsers(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam("role") String role, @ModelAttribute("user") User user)  {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.createUser(user, role);
        }
        catch(ConstraintViolationException ex){
            modelAndView.addObject("error", "Этот логин уже используется.");
        }
        Model model = new Model();
        model.selectedPageNum = 4;
        loadUsers(model);
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/users");
        modelAndView.addObject("prefix","");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/updateuser", method = RequestMethod.POST)
    public ModelAndView updateUser(@RequestParam("role") String role, @ModelAttribute("user") User user)  {
        ModelAndView modelAndView = new ModelAndView();
        try {
            System.out.println(user);
            userService.updateUser(user, role);
        }
        catch(ConstraintViolationException ex){
            modelAndView.addObject("error", "Возникла ошибка.");
        }
        Model model = new Model();
        model.selectedPageNum = 4;
        loadUsers(model);
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/users");
        modelAndView.addObject("prefix","");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.POST)
    public ModelAndView deleteUser(@RequestParam("username") String username)  {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUser(username);
        }
        catch(ConstraintViolationException ex){
            modelAndView.addObject("error", "Этот логин уже используется.");
        }
        Model model = new Model();
        model.selectedPageNum = 4;
        loadUsers(model);
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/users");
        modelAndView.addObject("prefix","");
        return modelAndView;
    }

    private Model loadUsers(Model model) {
        List<Model.UserItem> userItems = new ArrayList<>();
        Model.UserItem userItem;
        List<User> users = userService.getUsers();
        for (User user : users) {
            if (user.getUserRole().isEmpty()) {
                continue;
            }
            if (!new ArrayList<>(user.getUserRole()).get(0).getRole().equals("ROLE_ADMIN")) {
                userItem = new Model.UserItem();
                userItem.fullName = "test";
                userItem.userName = user.getUsername();
                userItems.add(userItem);
            }
        }
        model.userItems = userItems;
        model.usersJson = users.toString();
        return model;
    }

    @RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
     public String redirect1() {
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/admin/updateuser", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/admin/users";
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

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<UserItem> getUserItems() {
            return userItems;
        }
        public String getUsersJson() {
            return usersJson;
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





/*
    private ArrayList<String> loadList() {
        List<UserRole> userlist = userService.getUser();
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<userlist.size();i++){
            list.add(i,userlist.get(i).getUser().getUsername());
        }
        return list;
    }






    @RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
    public ModelAndView createUserPost(@RequestParam("role") String role,@ModelAttribute("user") User user)  {
        ModelAndView model = new ModelAndView();
        try {
            userService.createUser(user,role);
            list=loadList();
            model.addObject("userlist",list);
            model.addObject("prefix","");
            model.setViewName("admin/index");
        }
        catch(ConstraintViolationException ex){
            model.addObject("prefix","");
            model.addObject("error", "This name is already used ");
            model.setViewName("admin/index");
        }
        return model;
    }
    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.POST)
    public ModelAndView deleteUserPost(@RequestParam("username") String username) {
        ModelAndView model = new ModelAndView();
        try {
            userService.deleteUser(username);
            list=loadList();
            model.addObject("userlist",list);
            model.addObject("prefix","");
            model.setViewName("admin/index");
        }
        catch(ConstraintViolationException ex){
            model.addObject("error", "Error ");
            model.setViewName("admin/index");
        }
        return model;
    }
*/
}

