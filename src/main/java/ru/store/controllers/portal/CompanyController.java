package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.api.portal.PriorityResource;
import ru.store.dao.interfaces.CompanyAddressDAO;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class CompanyController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanyAddressDAO companyAddressDAO;
    @Autowired
    private PriorityResource priorityResource;

    @RequestMapping(value = "/company/*", method = RequestMethod.GET)
    public ModelAndView company(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {
            companyId = Integer.valueOf(splitResult[1]);
        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Company company = companyDAO.getCompany(companyId);
        if (company == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        for (PriorityResource.PriorityModel priorityModel : priorityResource.priorityHandler()) {
            if (Objects.equals(priorityModel.getPackageId(), company.getCompanyPackageId()))
                modelAndView.addObject("color", priorityModel.getPriority());
        }
        modelAndView.addObject("company", company);
        modelAndView.addObject("addresses", companyAddressDAO.getCompanyAddresses(companyId));
        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("portal/company");
        return modelAndView;
    }
}
