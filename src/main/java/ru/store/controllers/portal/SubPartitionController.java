package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.controllers.operator.OperatorSearchController;
import ru.store.entities.*;
import ru.store.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
public class SubPartitionController {

    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private PartitionService partitionService;

    @RequestMapping(value = "/subPartition/*", method = RequestMethod.GET)
    public ModelAndView subPartition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("subPartition/");
        int subPartitionId;
        SubPartition subPartition;
        Partition partition;
        PartitionController.Model model = new PartitionController.Model();
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {

            subPartitionId = Integer.valueOf(splitResult[1]);

            //подсчет переходов на подразделы
            subPartition = subPartitionService.getSubPartitionById(subPartitionId);
            if (subPartition == null) {
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }
            subPartition.setCountSubPartition();
            subPartition.setCountSubPartitionToday();
            subPartitionService.updateSubPartition(subPartition);
            modelAndView.addObject("countInfo","подраздела за весь период");
            modelAndView.addObject("portalCount",subPartition.getCountSubPartition());
            modelAndView.addObject("countTodayInfo","подраздела за сегодня");
            modelAndView.addObject("portalTodayCount",subPartition.getCountSubPartitionToday());
            //

            partition = partitionService.getPartitionById(subPartition.getPartitionId());

            Map<Integer, Integer> packageIdToPriority = OperatorSearchController.priorityHandler(packageService.getPackages());
            List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionBySubPartitionId(subPartitionId);
            List<Integer> companyId = new ArrayList<>();
            for (CompanySubPartition companySubPartition : companySubPartitions) {
                companyId.add(companySubPartition.getCompanyId());
            }
            List<Company> companies = companyService.getPortalCompanies(companyId);
            Map<Integer, List<CompanyAddress>> companyToCompanyAddress = new HashMap<>();
            List<PartitionController.Model.CompanyItem> companyItems = new ArrayList<>();
            PartitionController.Model.CompanyItem companyItem;
            for (Company company : companies) {
                company.setDescription(company.getDescription().replaceAll("\n","<br/>").replaceAll("script",""));
                companyToCompanyAddress.put(company.getId(), new ArrayList<CompanyAddress>());
                companyItem = new PartitionController.Model.CompanyItem();
                if (packageIdToPriority.get(company.getCompanyPackageId()) != null)
                    companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
                if (company.getIsPriority())
                    companyItem.colorPoint = 100;
                companyItem.companyId = company.getId();
                companyItem.companyInformation = company.getDescription();
                companyItem.companyName = company.getName();
                companyItem.costOf = company.getCostOf();
                companyItem.imageId = company.getImageId();
                companyItem.isPaid = company.getIsPaid();
                companyItem.countCompany = company.getCountCompany();
                companyItems.add(companyItem);
            }
            model.companyHiPrior = new ArrayList<>();
            int counter = 0;
            for (PartitionController.Model.CompanyItem companyItem1 : companyItems) {
                model.companyHiPrior.add(companyItem1);
                counter++;
                if (counter == 10)
                    break;
            }
            List<CompanyAddress> companyAddresses =
                    companyAddressService.getCompanyAddresses(new ArrayList<>(companyToCompanyAddress.keySet()));
            for (CompanyAddress companyAddress : companyAddresses) {
                companyToCompanyAddress.get(companyAddress.getCompanyId()).add(companyAddress);
            }
            model.companyToCompanyAddress = companyToCompanyAddress;

        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        modelAndView.addObject("model", model);
        modelAndView.addObject("companyCounter", model.companyHiPrior.size() + "");
        modelAndView.addObject("subPartitionName", subPartition.getName());
        modelAndView.addObject("subPartitionId", subPartitionId);
        modelAndView.addObject("partition", partition);
        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("portal/subpartition");
        return modelAndView;
    }

}
