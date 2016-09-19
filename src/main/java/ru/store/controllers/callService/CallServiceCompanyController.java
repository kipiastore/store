package ru.store.controllers.callService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.callService.models.CallServiceCompanyModel;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Company;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class CallServiceCompanyController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/callService/company/*", method = RequestMethod.GET)
    public String company(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+-\\d+")) {
            subPartitionId = Integer.valueOf(splitResult[1].split("-")[0]);
            companyId = Integer.valueOf(splitResult[1].split("-")[1]);
        }
        else
            return "redirect:/callService";
        Company company = companyDAO.getCompanyById(companyId);
        SubPartition subPartition = subPartitionDAO.getSubPartitionById(subPartitionId);
        Partition partition = company.getPartition();

        CallServiceCompanyModel.PartitionItem partitionItem = new CallServiceCompanyModel.PartitionItem();
        partitionItem.setPartitionId(partition.getId());
        partitionItem.setPartitionName(partition.getName());
        CallServiceCompanyModel.SubPartitionItem subPartitionItem = new CallServiceCompanyModel.SubPartitionItem();
        subPartitionItem.setSubPartitionId(subPartition.getId());
        subPartitionItem.setSubPartitionName(subPartition.getName());
        subPartitionItem.setPartitionItem(partitionItem);
        CallServiceCompanyModel callServiceCompanyModel = new CallServiceCompanyModel();
        callServiceCompanyModel.setCompanyName(company.getName());
        callServiceCompanyModel.setCompanyId(company.getId());
        callServiceCompanyModel.setSubPartitionItem(subPartitionItem);
        model.addAttribute("callServiceCompanyModel", callServiceCompanyModel);

        model.addAttribute("prefix", "../");
        return "callService/callServiceCompany";
    }

}
