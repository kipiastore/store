package ru.store.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class AdminReportsController {

    @RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
    public ModelAndView reports() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        model.selectedPageNum = 8;
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/reports");
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
