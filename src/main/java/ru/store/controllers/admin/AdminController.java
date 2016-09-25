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
import ru.store.entities.UserRole;
import ru.store.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage() {
        return "redirect:/admin/companies";
    }

    /*
    @Autowired
    private UserService userService;
    private ArrayList<String> list;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        list=loadList();
        model.addObject("userlist",list);
        model.addObject("prefix","");
        model.setViewName("admin/index");

        return model;
    }
    @RequestMapping(value = "/admin/addmanager", method = RequestMethod.POST)
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
    @RequestMapping(value = "/admin/deletemanager", method = RequestMethod.POST)
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
    private ArrayList<String> loadList(){
        List<UserRole> userlist=userService.getUser();
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<userlist.size();i++){
            list.add(i,userlist.get(i).getUser().getUsername());
        }
        return list;
    }
    */
}
