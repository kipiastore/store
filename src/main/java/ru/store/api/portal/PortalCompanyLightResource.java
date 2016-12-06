package ru.store.api.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.Company;
import ru.store.entities.CompanySubPartition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class PortalCompanyLightResource {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;

    @RequestMapping(value = "/api/portal/resource/v1/company/SubPartition/{subPartitionId}/{position}",
            method = RequestMethod.GET)
    public List<Company> getCompany(@PathVariable String subPartitionId, @PathVariable String position) {

        List<CompanySubPartition> companySubPartitions =
                companySubPartitionDAO.findCompanySubpartitionBySubPartitionId(Integer.valueOf(subPartitionId));
        List<Integer> companyId = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyId.add(companySubPartition.getCompanyId());
        }

        int positionCounter = Integer.valueOf(position);
        int maxPosition = positionCounter + 10;
        List<Company> tmpCompanies = new ArrayList<>();
        List<Company> companies = companyDAO.getPortalCompanies(companyId);
        for (Company company : companies) {
            tmpCompanies.add(company);
            positionCounter++;
            if (positionCounter == maxPosition)
                break;
        }
        return tmpCompanies;
    }


}
