package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.Company;
import ru.store.entities.CompanySubPartition;

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

    @RequestMapping(value = "/operator/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String byKeyword = request.getParameter("byKeyword");
        String byCompany = request.getParameter("byCompany");
        String byAddress = request.getParameter("byAddress");

        Model model = new Model();
        model.companyItemsByKeyword = new ArrayList<>();
        model.companyItemsByCompany = new ArrayList<>();
        model.companyItemsByAddress = new ArrayList<>();
        Model.CompanyItem companyItem;

        if (byCompany != null && !byCompany.isEmpty()) {
            List<Company> companies = companyDAO.findCompaniesByName(byCompany);
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
                if (company.getIsShowForOperator() &&
                    companyWithPosition.get(company.getId()) != null &&
                    !company.getIsOffPosition())
                {
                    model.companyItemsByCompany.add(companyItem);
                }
            }
        }

        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "../operator/");
        modelAndView.setViewName("operator/search");
        return modelAndView;
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

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyName() {
                return companyName;
            }
        }
    }
}
