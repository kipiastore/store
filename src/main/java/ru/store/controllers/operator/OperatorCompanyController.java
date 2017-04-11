package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.*;
import ru.store.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
public class OperatorCompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private UserService userService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

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
            List<CompanySubPartition> tmpCompanySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(companyId);
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

        Company company = companyService.getCompany(companyId);
        SubPartition subPartition = subPartitionService.getSubPartitionById(subPartitionId);
        if (subPartition == null || company == null) {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }
        Partition partition = partitionService.getPartitionById(subPartition.getPartitionId());

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
        model.managerName = userService.getUser(company.getManager()).getFullName();
        model.packageName = packageService.getPackage(company.getCompanyPackageId()).getName();
        model.companyAddresses = companyAddressService.getCompanyAddresses(company.getId());

        List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(companyId);
        List<Integer> subPartitionIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            subPartitionIds.add(companySubPartition.getSubPartitionId());
        }
        model.subPartitions = subPartitionService.getSubPartitions(subPartitionIds);
        for (SubPartition subPartition1 : model.subPartitions) {
            subPartition1.setName(getNormalName(subPartition1.getName(),36));
        }

        List<Region> regions = regionService.getRegions();
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
