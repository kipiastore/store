package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.*;
import ru.store.entities.Company;
import ru.store.entities.CompanySubPartition;
import ru.store.entities.SubPartition;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class OperatorSubPartitionController {

    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;
    @Autowired
    private PackageDAO packageDAO;

    @RequestMapping(value = "/operator/subpartition/*", method = RequestMethod.GET)
    public ModelAndView subPartition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("subpartition/");
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            subPartitionId = Integer.valueOf(splitResult[1]);
        else {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }

        SubPartition subPartition = subPartitionDAO.getSubPartitionById(subPartitionId);
        if (subPartition == null) {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }
        List<CompanySubPartition> companySubPartitions = companySubPartitionDAO.findCompanySubpartitionBySubPartitionId(subPartitionId);
        List<Integer> companyIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyIds.add(companySubPartition.getCompanyId());
        }
        List<Company> companies = companyDAO.getCompanies(companyIds);
        Map<Integer, Integer> packageIdToPriority = OperatorSearchController.priorityHandler(packageDAO.getPackages());

        Model.SubPartitionItem subPartitionItem = new Model.SubPartitionItem();
        subPartitionItem.subPartitionId = subPartition.getId();
        subPartitionItem.subPartitionName = subPartition.getName();
        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = subPartition.getPartitionId();
        partitionItem.partitionName = partitionDAO.getPartitionById(subPartition.getPartitionId()).getName();
        List<Model.CompanyItem> companyItems = new ArrayList<>();
        Model.CompanyItem companyItem;
        for (Company company : companies) {
            companyItem = new Model.CompanyItem();
            companyItem.companyId = company.getId();
            companyItem.companyName = company.getName();
            companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
            if (company.getIsPriority())
                companyItem.colorPoint = 100;
            companyItems.add(companyItem);
        }

        Model model = new Model();
        model.subPartitionItem = subPartitionItem;
        model.companyItems = companyItems;
        model.partitionItem = partitionItem;
        modelAndView.addObject("model", model);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("operator/subpartition");
        return modelAndView;
    }

    public static class Model {
        public SubPartitionItem subPartitionItem;
        public List<CompanyItem> companyItems;
        public PartitionItem partitionItem;

        public SubPartitionItem getSubPartitionItem() {
            return subPartitionItem;
        }
        public List<CompanyItem> getCompanyItems() {
            return companyItems;
        }
        public PartitionItem getPartitionItem() {
            return partitionItem;
        }

        public static class SubPartitionItem {
            public int subPartitionId;
            public String subPartitionName;

            public int getSubPartitionId() {
                return subPartitionId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
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

        public static class CompanyItem {
            public int companyId;
            public String companyName;
            public Integer colorPoint;

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyName() {
                return companyName;
            }
            public Integer getColorPoint() {
                return colorPoint;
            }
        }
    }
}
