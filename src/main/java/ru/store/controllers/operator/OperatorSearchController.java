package ru.store.controllers.operator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
public class OperatorSearchController {

    @RequestMapping(value = "/operator/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String byKeyword = request.getParameter("byKeyword");
        String byCompany = request.getParameter("byCompany");
        String byAddress = request.getParameter("byAddress");

        Model.CompanyItem companyItem;
        //for () {}
        Model model = new Model();

        modelAndView.addObject("prefix", "../operator/");
        modelAndView.setViewName("operator/search");
        return modelAndView;
    }

    public static class Model {
        public List<CompanyItem> companyItems;

        public List<CompanyItem> getCompanyItems() {
            return companyItems;
        }

        public static class CompanyItem {
            public int companyId;
            public String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyName() {
                return companyName;
            }
        }
    }
}
