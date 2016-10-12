package ru.store.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class AdminPositionsController {

    @RequestMapping(value = "/admin/positions", method = RequestMethod.GET)
    public ModelAndView positions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        model.selectedPageNum = 2;
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/positions");
        return modelAndView;
    }

    public static class Model {
        /**
         * 1 = companies
         * 2 = positions
         * 3 = partitions
         * 4 = users
         * 5 = managers
         * 6 = regions
         * 7 = packages
         * 8 = reports
         */
        public int selectedPageNum;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
    }
}