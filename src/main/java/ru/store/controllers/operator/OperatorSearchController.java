package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.CompanyAddressDAO;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanySubPartition;
import ru.store.entities.Package;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
public class OperatorSearchController {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;
    @Autowired
    private CompanyAddressDAO companyAddressDAO;
    @Autowired
    private PackageDAO packageDAO;

    @RequestMapping(value = "/operator/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String byKeyword = request.getParameter("byKeyword");
        String byCompany = request.getParameter("byCompany");
        String byAddress = request.getParameter("byAddress");

        Model model = new Model();

        if (byKeyword != null && !byKeyword.isEmpty()) {
            List<Company> companies = companyDAO.findCompaniesByKeyword(byKeyword);
            model.companyItemsByKeyword = searchCompany(companies);
        }

        if (byCompany != null && !byCompany.isEmpty()) {
            List<Company> companies = companyDAO.findCompaniesByName(byCompany);
            model.companyItemsByCompany = searchCompany(companies);
        }

        if (byAddress != null && !byAddress.isEmpty()) {
            List<CompanyAddress> companyAddresses = companyAddressDAO.findCompanyAddressByAddress(byAddress);
            List<Integer> companyIds = new ArrayList<>();
            for (CompanyAddress companyAddress : companyAddresses) {
                companyIds.add(companyAddress.getCompanyId());
            }
            List<Company> companies = companyDAO.getCompanies(companyIds);
            model.companyItemsByAddress = searchCompany(companies);
        }


        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "../operator/");
        modelAndView.setViewName("operator/search");
        return modelAndView;
    }

    private List<Model.CompanyItem> searchCompany(List<Company> companies) {
        Map<Integer, Integer> packageIdToPriority = priorityHandler(packageDAO.getPackages());
        List<Model.CompanyItem> result = new ArrayList<>();
        Model.CompanyItem companyItem;
        Map<Integer, Integer> companyWithPosition = new HashMap<>();
        List<Integer> companyIds = new ArrayList<>();
        for (Company company : companies) {
            companyIds.add(company.getId());
        }
        List<CompanySubPartition> companySubPartitions = new ArrayList<>();
        if (companyIds.size() > 0)
            companySubPartitions = companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyIds);
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyWithPosition.put(companySubPartition.getCompanyId(), companySubPartition.getSubPartitionId());
        }
        for (Company company : companies) {
            companyItem = new Model.CompanyItem();
            companyItem.companyId = company.getId();
            companyItem.companyName = company.getName();
            if (packageIdToPriority.get(company.getCompanyPackageId()) != null)
                companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
            if (company.getIsPriority())
                companyItem.colorPoint = 100;
            if (company.getIsShowForOperator() &&
                    companyWithPosition.get(company.getId()) != null &&
                    !company.getIsOffPosition())
            {
                result.add(companyItem);
            }
        }
        return result;
    }

    public static Map<Integer, Integer> priorityHandler(List<Package> packages) {
        Map<Integer, Integer> result = new HashMap<>();

        Set<Integer> prioritySet = new TreeSet<>();
        for (Package aPackages : packages) {
            prioritySet.add(aPackages.getPriority());
        }
        int step = 100 / (prioritySet.size() + 1) + 1;
        int counter;
        for (Package aPackages : packages) {
            counter = 0;
            for (Integer priority : prioritySet) {
                counter++;
                if (Objects.equals(aPackages.getPriority(), priority)) {
                    result.put(aPackages.getId(), step * counter - step);
                }
            }
        }
        return result;
    }

    public static class Model {
        public List<CompanyItem> companyItemsByKeyword;
        public List<CompanyItem> companyItemsByCompany;
        public List<CompanyItem> companyItemsByAddress;

        public List<CompanyItem> getCompanyItemsByKeyword() {
            return companyItemsByKeyword;
        }
        public List<CompanyItem> getCompanyItemsByCompany() {
            return companyItemsByCompany;
        }
        public List<CompanyItem> getCompanyItemsByAddress() {
            return companyItemsByAddress;
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
