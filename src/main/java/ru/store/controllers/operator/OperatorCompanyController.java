package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView company(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+-\\d+")) {
            subPartitionId = Integer.valueOf(splitResult[1].split("-")[0]);
            companyId = Integer.valueOf(splitResult[1].split("-")[1]);
        } else {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }

        Company company = companyDAO.getCompanyById(companyId);
        SubPartition subPartition = subPartitionDAO.getSubPartitionById(subPartitionId);
        Partition partition = company.getPartition();

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = partition.getName();
        Model.SubPartitionItem subPartitionItem = new Model.SubPartitionItem();
        subPartitionItem.subPartitionId = subPartition.getId();
        subPartitionItem.subPartitionName = subPartition.getName();
        subPartitionItem.partitionItem = partitionItem;
        Model model = new Model();
        model.companyName = company.getName();
        model.companyId = company.getId();
        model.subPartitionItem = subPartitionItem;
        modelAndView.addObject("model", model);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("operator/company");
        return modelAndView;
    }

    public static class Model {
        public int companyId;
        public String companyName;
        public SubPartitionItem subPartitionItem;

        public int getCompanyId() {
            return companyId;
        }
        public String getCompanyName() {
            return companyName;
        }
        public SubPartitionItem getSubPartitionItem() {
            return subPartitionItem;
        }

        public static class SubPartitionItem {
            public int subPartitionId;
            public String subPartitionName;
            public PartitionItem partitionItem;

            public int getSubPartitionId() {
                return subPartitionId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
            }
            public PartitionItem getPartitionItem() {
                return partitionItem;
            }
        }

        public static class PartitionItem {
            public int partitionId;
            public String partitionName;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
        }
    }
}
