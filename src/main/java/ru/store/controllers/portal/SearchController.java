package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.api.portal.PriorityResource;
import ru.store.beans.SearchRequestKeeper;
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
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private SearchRequestKeeper searchRequestKeeper;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String searchKey = request.getParameter("value");

        searchKey = searchKey.trim();
        if (searchKey.length() > 255)
            searchKey = searchKey.substring(0, 254);

        if (!searchKey.isEmpty()) {
            searchRequestKeeper.save(searchKey, 1);
        }

        List<Company> companies = companyService.findPortalCompaniesByName(searchKey);
        companies.addAll(companyService.findPortalCompaniesByKeyword(searchKey));

        //calculating portal visitors
        CountingPortalPage countingPortalPage = countingService.getCountPortalPage();
        countingPortalPage.setCountPortal();
        countingPortalPage.setCountPortalToday();
        countingService.addCountPortalPage(countingPortalPage);
        modelAndView.addObject("countInfo","ресурса за весь период");
        modelAndView.addObject("portalCount",countingPortalPage.getCountPortal());
        modelAndView.addObject("countTodayInfo","ресурса за сегодня");
        modelAndView.addObject("portalTodayCount",countingPortalPage.getCountPortalToday());
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

        List<PartitionController.Model.PartitionItem.SubPartitionItem> subPartitions1 = new ArrayList<>();
        List<PartitionController.Model.PartitionItem.SubPartitionItem> subPartitions2 = new ArrayList<>();
        for (int i = 0; i < subPartitionItems.size(); i++) {
            if ((subPartitionItems.size() % 2) == 0) {
                if (i >= subPartitionItems.size() / 2) {
                    subPartitions1.add(subPartitionItems.get(i));
                } else {
                    subPartitions2.add(subPartitionItems.get(i));
                }
            } else {
                if (i > subPartitionItems.size() / 2) {
                    subPartitions1.add(subPartitionItems.get(i));
                } else {
                    subPartitions2.add(subPartitionItems.get(i));
                }
            }
        }

        List<Company> finalCompanyList = new ArrayList<>(finalCompanyMap.values());
        List<CompanyAddress> companyAddresses = companyAddressService.getCompanyAddresses(new ArrayList<>(companyIds));
        for (CompanyAddress companyAddress : companyAddresses) {
            companyToCompanyAddress.get(companyAddress.getCompanyId()).add(companyAddress);
        }
        Map<Integer, Integer> packageToColor = new HashMap<>();
        for (PriorityResource.PriorityModel priorityModel : priorityResource.priorityHandler()) {
            packageToColor.put(priorityModel.getPackageId(), priorityModel.getPriority());
        }

        modelAndView.addObject("model", getModel(searchKey));

        modelAndView.addObject("packageToColor", packageToColor);
        modelAndView.addObject("companyToCompanyAddress", companyToCompanyAddress);
        modelAndView.addObject("companies", finalCompanyList);
        modelAndView.addObject("subPartitions1", subPartitions1);
        modelAndView.addObject("subPartitions2", subPartitions2);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("portal/search");
        return modelAndView;
    }

    private PortalController.Model getModel(String searchKey) {

        Set<Integer> partitionIds = new HashSet<>();
        List<Partition> partitions = partitionService.findPortalPartitionsByName(searchKey);
        for (Partition partition : partitions) {
            partitionIds.add(partition.getId());
        }
        List<SubPartition> subPartitions = subPartitionService.findPortalSubPartitionsByName(searchKey);
        for (SubPartition subPartition : subPartitions) {
            partitionIds.add(subPartition.getPartitionId());
        }
        partitions = partitionService.getPartitions(new ArrayList<>(partitionIds));

        PortalController.Model model = new PortalController.Model();
        Map<Integer, Integer> subPartitionIdToCount = new HashMap<>();
        List<Integer> companiesIds = companyService.getOptimizationCompanies();
        Set<Integer> availableCompany = new TreeSet<>();
        for (Integer companyId : companiesIds) {
            availableCompany.add(companyId);
        }
        List<CompanySubPartition> companySubPartitions = companySubPartitionService.getCompanySubPartitions();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            if (!availableCompany.contains(companySubPartition.getCompanyId()))
                continue;
            if (subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) == null) {
                subPartitionIdToCount.put(companySubPartition.getSubPartitionId(), 1);
            } else {
                subPartitionIdToCount.put(
                        companySubPartition.getSubPartitionId(),
                        subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) + 1);
            }
        }

        Map<Integer, List<PortalController.Model.PartitionItem.SubPartitionItem>> subPartitionItemsGroupByPartitionId = new HashMap<>();
        List<PortalController.Model.PartitionItem.SubPartitionItem> subPartitionItemsModel;
        PortalController.Model.PartitionItem.SubPartitionItem subPartitionItemModel;
        for (SubPartition subPartition : subPartitions) {
            subPartitionItemModel = new PortalController.Model.PartitionItem.SubPartitionItem();
            subPartitionItemModel.subPartitionId = subPartition.getId();
            subPartitionItemModel.subPartitionName = subPartition.getName();
            if (subPartitionIdToCount.get(subPartition.getId()) != null)
                subPartitionItemModel.companyCount = subPartitionIdToCount.get(subPartition.getId());
            if (subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()) != null) {
                subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()).add(subPartitionItemModel);
            } else {
                subPartitionItemsModel = new ArrayList<>();
                subPartitionItemsModel.add(subPartitionItemModel);
                subPartitionItemsGroupByPartitionId.put(subPartition.getPartitionId(), subPartitionItemsModel);
            }
        }

        for (Integer idKey : subPartitionItemsGroupByPartitionId.keySet()) {
            Collections.sort(subPartitionItemsGroupByPartitionId.get(idKey));
        }

        Map<Integer, Integer> partitionIdToCount = new HashMap<>();
        for (SubPartition subPartition : subPartitions) {
            if (subPartitionIdToCount.get(subPartition.getId()) != null) {
                if (partitionIdToCount.get(subPartition.getPartitionId()) == null) {
                    partitionIdToCount.put(subPartition.getPartitionId(), subPartitionIdToCount.get(subPartition.getId()));
                } else {
                    partitionIdToCount.put(subPartition.getPartitionId(),
                            partitionIdToCount.get(subPartition.getPartitionId()) + subPartitionIdToCount.get(subPartition.getId()));
                }
            }
        }

        List<PortalController.Model.PartitionItem> partitionItems = new ArrayList<>();
        PortalController.Model.PartitionItem partitionItem;
        for (Partition partition : partitions) {
            partitionItem = new PortalController.Model.PartitionItem();
            if (partitionIdToCount.get(partition.getId()) != null)
                partitionItem.companyCount = partitionIdToCount.get(partition.getId());
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = partition.getName();
            partitionItem.subPartitionItems = subPartitionItemsGroupByPartitionId.get(partition.getId());
            partitionItems.add(partitionItem);
        }

        Collections.sort(partitionItems);
        List<PortalController.Model.PartitionItem> tmp1 = new ArrayList<>();
        List<PortalController.Model.PartitionItem> tmp2 = new ArrayList<>();
        for (int i = 0; i < partitionItems.size(); i++) {
            if ((partitionItems.size() % 2) == 0) {
                if (i >= partitionItems.size() / 2) {
                    tmp1.add(partitionItems.get(i));
                } else {
                    tmp2.add(partitionItems.get(i));
                }
            } else {
                if (i > partitionItems.size() / 2) {
                    tmp1.add(partitionItems.get(i));
                } else {
                    tmp2.add(partitionItems.get(i));
                }
            }
        }
        model.partitionItems = tmp1;
        model.partitionItems2 = tmp2;
        return model;
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
