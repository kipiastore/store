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
import ru.store.entities.CompanyAddress;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SearchController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanyAddressDAO companyAddressDAO;
    @Autowired
    private PriorityResource priorityResource;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String searchKey = request.getParameter("value");
        List<Company> companies =  companyDAO.findCompaniesByKeyword(searchKey);
        Set<Integer> companyIds = new HashSet<>();
        Map<Integer, List<CompanyAddress>> companyToCompanyAddress = new HashMap<>();
        for (Company company : companies) {
            companyIds.add(company.getId());
            companyToCompanyAddress.put(company.getId(), new ArrayList<CompanyAddress>());
        }
        List<CompanyAddress> companyAddresses = companyAddressDAO.getCompanyAddresses(new ArrayList<>(companyIds));
        for (CompanyAddress companyAddress : companyAddresses) {
            companyToCompanyAddress.get(companyAddress.getCompanyId()).add(companyAddress);
        }
        Map<Integer, Integer> packageToColor = new HashMap<>();
        for (PriorityResource.PriorityModel priorityModel : priorityResource.priorityHandler()) {
            packageToColor.put(priorityModel.getPackageId(), priorityModel.getPriority());
        }
        modelAndView.addObject("packageToColor", packageToColor);
        modelAndView.addObject("companyToCompanyAddress", companyToCompanyAddress);
        modelAndView.addObject("companies", companies);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }





    @RequestMapping(value = "/search/1", method = RequestMethod.GET)
    public ModelAndView search1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    @RequestMapping(value = "/search/2", method = RequestMethod.GET)
    public ModelAndView search2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    @RequestMapping(value = "/search/3", method = RequestMethod.GET)
    public ModelAndView search3() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

}
