package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class OperatorCompanyController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/operator/company/*", method = RequestMethod.GET)
    public String company(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+-\\d+")) {
            subPartitionId = Integer.valueOf(splitResult[1].split("-")[0]);
            companyId = Integer.valueOf(splitResult[1].split("-")[1]);
        }
        else
            return "redirect:/operator";
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
        return "operator/company";
    }

    public static class CallServiceCompanyModel {
        private int companyId;
        private String companyName;
        private SubPartitionItem subPartitionItem;

        public int getCompanyId() {
            return companyId;
        }
        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }
        public String getCompanyName() {
            return companyName;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
        public SubPartitionItem getSubPartitionItem() {
            return subPartitionItem;
        }
        public void setSubPartitionItem(SubPartitionItem subPartitionItem) {
            this.subPartitionItem = subPartitionItem;
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
    }
}
