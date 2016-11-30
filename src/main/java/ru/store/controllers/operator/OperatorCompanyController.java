package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.*;
import ru.store.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
public class OperatorCompanyController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private CompanyAddressDAO companyAddressDAO;
    @Autowired
    private RegionDAO regionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PackageDAO packageDAO;
    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;

    @RequestMapping(value = "/operator/company/*", method = RequestMethod.GET)
    public ModelAndView company(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+-\\d+")) {
            subPartitionId = Integer.valueOf(splitResult[1].split("-")[0]);
            companyId = Integer.valueOf(splitResult[1].split("-")[1]);
        } else if (splitResult.length == 2 && splitResult[1].matches("A-\\d+")) {
            companyId = Integer.valueOf(splitResult[1].split("-")[1]);
            List<CompanySubPartition> tmpCompanySubPartitions = companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyId);
            if (tmpCompanySubPartitions != null && tmpCompanySubPartitions.size() > 0)
                subPartitionId = tmpCompanySubPartitions.get(0).getSubPartitionId();
            else {
                modelAndView.setViewName("redirect:/operator");
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }

        Company company = companyDAO.getCompany(companyId);
        SubPartition subPartition = subPartitionDAO.getSubPartitionById(subPartitionId);
        if (subPartition == null || company == null) {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }
        Partition partition = partitionDAO.getPartitionById(subPartition.getPartitionId());

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = getNormalName(partition.getName(), 36);
        Model.SubPartitionItem subPartitionItem = new Model.SubPartitionItem();
        subPartitionItem.subPartitionId = subPartition.getId();
        subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 36);
        subPartitionItem.partitionItem = partitionItem;
        Model model = new Model();
        model.companyName = company.getName();
        model.companyId = company.getId();
        model.subPartitionItem = subPartitionItem;
        model.company = company;
        model.managerName = userDAO.getUser(company.getManager()).getFullName();
        model.packageName = packageDAO.getPackage(company.getCompanyPackageId()).getName();
        model.companyAddresses = companyAddressDAO.getCompanyAddresses(company.getId());

        List<CompanySubPartition> companySubPartitions = companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyId);
        List<Integer> subPartitionIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionIds.add(companySubPartition.getSubPartitionId());
        }
        model.subPartitions = subPartitionDAO.getSubPartitions(subPartitionIds);
        for (SubPartition subPartition1 : model.subPartitions) {
            subPartition1.setName(getNormalName(subPartition1.getName(),36));
        }

        List<Region> regions = regionDAO.getRegions();
        Map<Integer, String> idToRegionName = new HashMap<>();
        for (CompanyAddress companyAddress : model.companyAddresses) {
            for (Region region : regions) {
                if (Objects.equals(region.getId(), companyAddress.getRegionId()))  {
                    idToRegionName.put(region.getId(), region.getName());
                }
            }
        }
        model.idToRegionName = idToRegionName;

        modelAndView.addObject("model", model);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("operator/company");
        return modelAndView;
    }

    private String getNormalName(String name, int length) {
        if (name != null && name.length() > length)
            return name.substring(0, length) + "..";
        else
            return name;
    }

    public static class Model {
        public int companyId;
        public String companyName;
        public SubPartitionItem subPartitionItem;
        public Company company;
        public String managerName;
        public String packageName;
        public List<CompanyAddress> companyAddresses;
        public Map<Integer, String> idToRegionName;
        public List<SubPartition> subPartitions;

        public int getCompanyId() {
            return companyId;
        }
        public String getCompanyName() {
            return companyName;
        }
        public SubPartitionItem getSubPartitionItem() {
            return subPartitionItem;
        }
        public Company getCompany() {
            return company;
        }
        public List<CompanyAddress> getCompanyAddresses() {
            return companyAddresses;
        }
        public Map<Integer, String> getIdToRegionName() {
            return idToRegionName;
        }
        public String getManagerName() {
            return managerName;
        }
        public String getPackageName() {
            return packageName;
        }
        public List<SubPartition> getSubPartitions() {
            return subPartitions;
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
