package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.api.portal.PriorityResource;
import ru.store.controllers.designer.DesignerCompanyPositionsController;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.*;
import ru.store.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CompanyController {

    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private PriorityResource priorityResource;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanySubpartitionContentService companySubpartitionContentService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private SubPartitionDAO subPartitionService;
    @Autowired
    private PartitionService partitionService;

    @RequestMapping(value = "/company/*", method = RequestMethod.GET)
    public ModelAndView company(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        Partition partition = null;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {
            companyId = Integer.valueOf(splitResult[1]);

            //подсчет переходов на компании
            Company company=companyService.getCompany(companyId);
            company.setCountCompany();
            companyService.updateCompany(company);
            modelAndView.addObject("countInfo","компании");
            modelAndView.addObject("portalCount",company.getCountCompany());
            //

        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Company company = companyService.getCompany(companyId);
        if (company == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        company.setDescription(company.getDescription().replaceAll("\n","<br/>").replaceAll("script",""));
        for (PriorityResource.PriorityModel priorityModel : priorityResource.priorityHandler()) {
            if (Objects.equals(priorityModel.getPackageId(), company.getCompanyPackageId()))
                modelAndView.addObject("color", priorityModel.getPriority());
        }

        DesignerCompanyPositionsController.Model model = new DesignerCompanyPositionsController.Model();
        List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(companyId);

        List<Integer> subPartitionIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionIds.add(companySubPartition.getSubPartitionId());
        }
        List<SubPartition> subPartitions = subPartitionService.getSubPartitions(subPartitionIds);
        for (SubPartition subPartition : subPartitions) {
            partition = partitionService.getPartitionById(subPartition.getPartitionId());
            break;
        }

        model.companySubpartitionContentList = new ArrayList<>();
        DesignerCompanyPositionsController.Model.CompanySubpartitionContentItem item;
        for (CompanySubpartitionContent content : companySubpartitionContentService.getCompanySubpartitionContents(companyId)) {
            item = new DesignerCompanyPositionsController.Model.CompanySubpartitionContentItem();
            item.companyId = content.getCompanyId();
            item.companySubpartitionId = content.getCompanySubpartitionId();
            item.id = content.getId();
            item.imageId = content.getImageId();
            item.info = content.getInfo();

            for (CompanySubPartition companySubPartition : companySubPartitions) {
                if (!Objects.equals(companySubPartition.getId(), content.getCompanySubpartitionId()))
                    continue;
                for (SubPartition subPartition : subPartitions) {
                    if (companySubPartition.getSubPartitionId() == subPartition.getId()) {
                        item.subPartitionName = subPartition.getName();
                    }
                }
            }
            model.companySubpartitionContentList.add(item);
        }

        modelAndView.addObject("model", model);
        modelAndView.addObject("company", company);
        modelAndView.addObject("partition", partition);
        modelAndView.addObject("addresses", companyAddressService.getCompanyAddresses(companyId));
        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("portal/company");
        return modelAndView;
    }
}
