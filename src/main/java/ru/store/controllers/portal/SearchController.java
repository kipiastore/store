package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.api.portal.PriorityResource;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanySubPartition;
import ru.store.service.CompanyAddressService;
import ru.store.service.CompanyService;
import ru.store.service.CompanySubPartitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SearchController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private PriorityResource priorityResource;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String searchKey = request.getParameter("value");
        List<Company> companies = companyService.findPortalCompaniesByName(searchKey);
        companies.addAll(companyService.findPortalCompaniesByKeyword(searchKey));
        Set<Integer> companyIds = new HashSet<>();
        Map<Integer, List<CompanyAddress>> companyToCompanyAddress = new HashMap<>();
        List<CompanySubPartition> companySubPartitions = new ArrayList<>();
        Map<Integer, Company> companyMap = new HashMap<>();
        for (Company company : companies) {
            company.setDescription(company.getDescription().replaceAll("\n","<br/>").replaceAll("script",""));
            companyIds.add(company.getId());
            companyMap.put(company.getId(), company);
            companyToCompanyAddress.put(company.getId(), new ArrayList<CompanyAddress>());
        }

        List<Company> finalCompanyList = new ArrayList<>();
        if (companyIds.size() > 0)
            companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(new ArrayList<>(companyIds));
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            finalCompanyList.add(companyMap.get(companySubPartition.getCompanyId()));
        }

        List<CompanyAddress> companyAddresses = companyAddressService.getCompanyAddresses(new ArrayList<>(companyIds));
        for (CompanyAddress companyAddress : companyAddresses) {
            companyToCompanyAddress.get(companyAddress.getCompanyId()).add(companyAddress);
        }
        Map<Integer, Integer> packageToColor = new HashMap<>();
        for (PriorityResource.PriorityModel priorityModel : priorityResource.priorityHandler()) {
            packageToColor.put(priorityModel.getPackageId(), priorityModel.getPriority());
        }
        modelAndView.addObject("packageToColor", packageToColor);
        modelAndView.addObject("companyToCompanyAddress", companyToCompanyAddress);
        modelAndView.addObject("companies", finalCompanyList);
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
