package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.*;
import ru.store.exceptions.NotSupportedFormat;
import ru.store.service.*;
import ru.store.servlets.DownloadServlet;

import java.util.*;

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
    @Autowired
    private FileService fileService;
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
    public ModelAndView reports() {
        ModelAndView modelAndView = new ModelAndView();
        AdminPositionsController.Model model = new AdminPositionsController.Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addreport", method = RequestMethod.POST)
    public ModelAndView addReport(@ModelAttribute("report") Report report,
                                  @RequestParam("file") MultipartFile multipartFile) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String[] tmp = multipartFile.getOriginalFilename().split("\\.");
            System.out.println(Arrays.toString(tmp));
            if (tmp.length < 2 || DownloadServlet.FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) == null) {
                throw new NotSupportedFormat("Этот формат не поддерживается.");
            }
            File file = new File();
            file.setFile(multipartFile.getBytes());
            file.setName(multipartFile.getOriginalFilename());
            fileService.createFile(file);
            System.out.println(file);

            if (report.getName().isEmpty()) {
                report.setName(file.getName());
            }
            report.setFileId(file.getId());
            reportService.createReport(report);
            modelAndView.addObject("successMessage", "Отчет успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
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
                //7.03.17
                else{
                    companies = companyService.getCompanies();
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
