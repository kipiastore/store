package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.api.portal.PriorityResource;
import ru.store.entities.*;
import ru.store.service.*;

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
    @Autowired
    private CountingService countingService;
    @Autowired
    private SubPartitionService subPartitionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String searchKey = request.getParameter("value");
        List<Company> companies = companyService.findPortalCompaniesByName(searchKey);
        companies.addAll(companyService.findPortalCompaniesByKeyword(searchKey));

        //calculating portal visitors
        CountingPortalPage countingPortalPage=countingService.getCountPortalPage();
        countingPortalPage.setCountPortal();
        countingService.addCountPortalPage(countingPortalPage);
        modelAndView.addObject("countInfo","ресурса");
        modelAndView.addObject("portalCount",countingPortalPage.getCountPortal());
        //

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

        Map<Integer, Company> finalCompanyMap = new HashMap<>();
        if (companyIds.size() > 0)
            companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(new ArrayList<>(companyIds));
        Set<Integer> subPartitionIds = new HashSet<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionIds.add(companySubPartition.getSubPartitionId());
            finalCompanyMap.put(companySubPartition.getCompanyId(), companyMap.get(companySubPartition.getCompanyId()));
        }


        Map<Integer, Integer> subPartitionIdToCount = new HashMap<>();
        List<PartitionController.Model.PartitionItem.SubPartitionItem> subPartitionItems = new ArrayList<>();
        PartitionController.Model.PartitionItem.SubPartitionItem subPartitionItem;
        for (CompanySubPartition companySubPartition : companySubPartitionService.getCompanySubPartitions()) {
            if (!finalCompanyMap.keySet().contains(companySubPartition.getCompanyId()))
                continue;
            if (subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) == null) {
                subPartitionIdToCount.put(companySubPartition.getSubPartitionId(), 1);
            } else {
                subPartitionIdToCount.put(
                        companySubPartition.getSubPartitionId(),
                        subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) + 1);
            }
        }

        for (SubPartition subPartition : subPartitionService.getSubPartitions(new ArrayList<>(subPartitionIds))) {
            subPartitionItem = new PartitionController.Model.PartitionItem.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = PartitionController.getNormalName(subPartition.getName(), 31);
            if (subPartitionIdToCount.get(subPartition.getId()) != null)
                subPartitionItem.companyCount = subPartitionIdToCount.get(subPartition.getId());
            subPartitionItems.add(subPartitionItem);
        }

        Collections.sort(subPartitionItems);


        List<PartitionController.Model.PartitionItem.SubPartitionItem> tmp1 = new ArrayList<>();
        List<PartitionController.Model.PartitionItem.SubPartitionItem> tmp2 = new ArrayList<>();
        for (int i = 0; i < subPartitionItems.size(); i++) {
            if ((subPartitionItems.size() % 2) == 0) {
                if (i >= subPartitionItems.size() / 2) {
                    tmp1.add(subPartitionItems.get(i));
                } else {
                    tmp2.add(subPartitionItems.get(i));
                }
            } else {
                if (i > subPartitionItems.size() / 2) {
                    tmp1.add(subPartitionItems.get(i));
                } else {
                    tmp2.add(subPartitionItems.get(i));
                }
            }
        }


        /*
        List<SubPartition> subPartitions = subPartitionService.getSubPartitions(new ArrayList<>(subPartitionIds));
        List<SubPartition> tmp1 = new ArrayList<>();
        List<SubPartition> tmp2 = new ArrayList<>();
        for (int i = 0; i < subPartitions.size(); i++) {
            if ((subPartitions.size() % 2) == 0) {
                if (i >= subPartitions.size() / 2) {
                    tmp1.add(subPartitions.get(i));
                } else {
                    tmp2.add(subPartitions.get(i));
                }
            } else {
                if (i > subPartitions.size() / 2) {
                    tmp1.add(subPartitions.get(i));
                } else {
                    tmp2.add(subPartitions.get(i));
                }
            }
        }
        */


        List<Company> finalCompanyList = new ArrayList<>(finalCompanyMap.values());

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
        modelAndView.addObject("subPartitions1", tmp1);
        modelAndView.addObject("subPartitions2", tmp2);
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
