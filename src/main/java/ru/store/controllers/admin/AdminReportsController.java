package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.Partition;
import ru.store.entities.Report;
import ru.store.entities.SubPartition;
import ru.store.service.CompanyService;
import ru.store.service.PartitionService;
import ru.store.service.SubPartitionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class AdminReportsController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private PartitionService partitionService;

    @RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
    public ModelAndView reports() {
        ModelAndView modelAndView = new ModelAndView();
        AdminPositionsController.Model model = new AdminPositionsController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addreport", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("report") Report report) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        AdminPositionsController.Model model = new AdminPositionsController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addreport", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/reports";
    }
    @RequestMapping(value = "/admin/reportssearchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/reports";
    }


    @RequestMapping(value = "/admin/reportssearchcompany", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies = new ArrayList<>();
        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = searchMap.get(key).get(0).trim();
                System.out.println(value);
                if (!key.equals("_csrf") && !value.isEmpty()) {
                    if (key.equals("name")) {
                        companies = companyService.findCompaniesByName(value);
                        break;
                    }
                    if (key.equals("legalName")) {
                        companies = companyService.findCompaniesByLegalName(value);
                        break;
                    }
                    if (key.equals("phone")) {
                        companies = companyService.findCompaniesByPhone(value);
                        break;
                    }
                    if (key.equals("contractNum")) {
                        //companies = companyService.getCompaniesByLastUpdate(); wtf?
                        break;
                    }
                    if (key.equals("email")) {
                        companies = companyService.findCompaniesByEmail(value);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        AdminPositionsController.Model model = new AdminPositionsController.Model();
        loadPage(model, modelAndView);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            model.companyList.add(convert(company));
        }
        return modelAndView;
    }

    private void loadPage(AdminPositionsController.Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 8;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/reports");
    }

    private void loadCompanies(AdminPositionsController.Model model) {
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();
        for (Company company : companyService.getCompaniesByLastUpdate()) {
            model.companyList.add(convert(company));
        }
        List<AdminPositionsController.Model.PartitionItem> partitionItems = new ArrayList<>();
        AdminPositionsController.Model.PartitionItem partitionItem;
        AdminPositionsController.Model.PartitionItem mainPartitionItem;
        Map<Integer, AdminPositionsController.Model.PartitionItem> partitionIdToPartitionItem = new HashMap<>();
        Map<AdminPositionsController.Model.PartitionItem, List<AdminPositionsController.Model.PartitionItem>> subPartitionsGroupedByPartition = new HashMap<>();
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new AdminPositionsController.Model.PartitionItem();
            partitionItem.id = partition.getId();
            partitionItem.name = getNormalName(partition.getName());
            partitionIdToPartitionItem.put(partition.getId(), partitionItem);
            partitionItems.add(partitionItem);
            subPartitionsGroupedByPartition.put(partitionItem, null);
        }
        for (SubPartition subPartition : subPartitionService.getSubPartitions()) {
            if (subPartitionsGroupedByPartition.get(new AdminPositionsController.Model.PartitionItem(subPartition.getPartitionId())) != null) {
                partitionItem = new AdminPositionsController.Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                subPartitionsGroupedByPartition.get(new AdminPositionsController.Model.PartitionItem(subPartition.getPartitionId())).add(partitionItem);
            } else {
                mainPartitionItem = new AdminPositionsController.Model.PartitionItem();
                mainPartitionItem.id = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getId();
                mainPartitionItem.name = getNormalName(partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName());
                partitionItems = new ArrayList<>();
                partitionItem = new AdminPositionsController.Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItems.add(partitionItem);
                subPartitionsGroupedByPartition.put(mainPartitionItem, partitionItems);
            }
        }
        model.subPartitionsGroupedByPartition = subPartitionsGroupedByPartition;
    }

    private AdminPositionsController.Model.CompaniesItem convert(Company company) {
        AdminPositionsController.Model.CompaniesItem companyItem = new AdminPositionsController.Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        return companyItem;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }

}
