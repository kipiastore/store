package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class OperatorSubPartitionController {

    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private CompanyDAO companyDAO;

    @RequestMapping(value = "/operator/subpartition/*", method = RequestMethod.GET)
    public String subPartition(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("subpartition/");
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            subPartitionId = Integer.valueOf(splitResult[1]);
        else
            return "redirect:/operator";

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
        return "operator/subpartition";
    }

    public static class CallServiceSubPartitionModel {
        private SubPartitionItem subPartitionItem;
        private List<CompanyItem> companyItems;

        public SubPartitionItem getSubPartitionItem() {
            return subPartitionItem;
        }
        public void setSubPartitionItem(SubPartitionItem subPartitionItem) {
            this.subPartitionItem = subPartitionItem;
        }
        public List<CompanyItem> getCompanyItems() {
            return companyItems;
        }
        public void setCompanyItems(List<CompanyItem> companyItems) {
            this.companyItems = companyItems;
        }

        public static class SubPartitionItem {
            private int subPartitionId;
            private String subPartitionName;
            private PartitionItem partitionItem;

            public int getSubPartitionId() {
                return subPartitionId;
            }
            public void setSubPartitionId(int subPartitionId) {
                this.subPartitionId = subPartitionId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
            }
            public void setSubPartitionName(String subPartitionName) {
                this.subPartitionName = subPartitionName;
            }
            public PartitionItem getPartitionItem() {
                return partitionItem;
            }
            public void setPartitionItem(PartitionItem partitionItem) {
                this.partitionItem = partitionItem;
            }
        }

        public static class PartitionItem {
            private int partitionId;
            private String partitionName;

            public int getPartitionId() {
                return partitionId;
            }
            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public void setPartitionName(String partitionName) {
                this.partitionName = partitionName;
            }
        }

        public static class CompanyItem {
            private int companyId;
            private String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public void setCompanyId(int companyInt) {
                this.companyId = companyInt;
            }
            public String getCompanyName() {
                return companyName;
            }
            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }
}
