package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Act;
import ru.store.entities.Company;
import ru.store.entities.File;
import ru.store.exceptions.NotSupportedFormat;
import ru.store.service.*;
import ru.store.servlets.DownloadServlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Controller
public class ManagerActsController {

    @Autowired
    SearchByPage searchByPage;
    @Autowired
    CompanyReminderService companyReminderService;
    @Autowired
    PackageService packageService;
    @Autowired
    CompanyAddressService companyAddressService;
    @Autowired
    CompanyService companyService;
    @Autowired
    private ActService actService;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/manager/acts", method = RequestMethod.GET)
    public ModelAndView acts() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(modelAndView,model);
        return modelAndView;
    }

    @RequestMapping(value = "/manager/addact", method = RequestMethod.POST)
    public ModelAndView addAct(@ModelAttribute("act") Act act, @RequestParam("file") MultipartFile multipartFile) {
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

            if (act.getName().isEmpty()) {
                act.setName(file.getName());
            }
            act.setFileId(file.getId());
            actService.createAct(act);
            modelAndView.addObject("successMessage", "Акт успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/manager/addact", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/reports";
    }
    @RequestMapping(value = "/manager/searchcompanybyacts", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/reports";
    }


    @RequestMapping(value = "/manager/searchcompanybyacts", method = RequestMethod.POST)
    public ModelAndView searchByActs(@RequestParam MultiValueMap<String, String> searchMap) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        List<Company> companies = searchByPage.search(searchMap, "searchAllCompany","selectSearchCompanyByPaymentStatusAll", modelAndView);
        loadPage(modelAndView,model);
        loadPage(modelAndView,model);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        System.out.println("Компании-----------"+companies);
        for (Company company : companies) {
            List<Model.CompaniesItem> list = convert(company);
            for (Model.CompaniesItem m : list) {
                model.companyList.add(m);
            }
        }
        return modelAndView;
    }
    private void loadPage(ModelAndView modelAndView, Model model){
        model.selectedPageNum = 2;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("manager/acts");
    }
    private void loadCompanies(Model model){
        List<Company> companies = companyService.getCompanies();
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
                List<Model.CompaniesItem> list = convert(company);
                for (Model.CompaniesItem m : list) {
                    model.companyList.add(m);

            }
        }
    }
    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }
    private List<Model.CompaniesItem> convert(Company company) {
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        companyItem = new Model.CompaniesItem();
        companyItem.name = company.getName();
        companyItem.manager = company.getManager();
        companyItem.timeOfContract = "";
        companyItem.costOf = company.getCostOf();
        companyItem.noteOfActs = "";
        companyItem.dateOfPaid = "";
        companyItem.act = "";
        companyItems.add(companyItem);
        return companyItems;
    }
}
