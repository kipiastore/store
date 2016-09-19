package ru.store.controllers.callService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.callService.models.CallServiceSubPartitionModel;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Company;
import ru.store.entities.SubPartition;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class CallServiceSubPartitionController {

    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private CompanyDAO companyDAO;

    @RequestMapping(value = "/callService/subPartition/*", method = RequestMethod.GET)
    public String subPartition(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("subPartition/");
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            subPartitionId = Integer.valueOf(splitResult[1]);
        else
            return "redirect:/callService";

        SubPartition subPartition = subPartitionDAO.getSubPartitionById(subPartitionId);
        List<Company> companies = companyDAO.getCompaniesBySubPartition(subPartition);
        // build the model
        CallServiceSubPartitionModel.PartitionItem partitionItem = new CallServiceSubPartitionModel.PartitionItem();
        partitionItem.setPartitionId(subPartition.getPartition().getId());
        partitionItem.setPartitionName(subPartition.getPartition().getName());
        CallServiceSubPartitionModel.SubPartitionItem subPartitionItem = new CallServiceSubPartitionModel.SubPartitionItem();
        subPartitionItem.setSubPartitionId(subPartition.getId());
        subPartitionItem.setSubPartitionName(subPartition.getName());
        subPartitionItem.setPartitionItem(partitionItem);
        List<CallServiceSubPartitionModel.CompanyItem> companyItems = new ArrayList<>();
        CallServiceSubPartitionModel.CompanyItem companyItem;
        for (Company company : companies) {
            companyItem = new CallServiceSubPartitionModel.CompanyItem();
            companyItem.setCompanyId(company.getId());
            companyItem.setCompanyName(company.getName());
            companyItems.add(companyItem);
        }
        CallServiceSubPartitionModel callServiceSubPartitionModel = new CallServiceSubPartitionModel();
        callServiceSubPartitionModel.setSubPartitionItem(subPartitionItem);
        callServiceSubPartitionModel.setCompanyItems(companyItems);
        model.addAttribute("callServiceSubPartitionModel", callServiceSubPartitionModel);

        model.addAttribute("prefix", "../");
        return "callService/callServiceSubPartition";
    }
}
