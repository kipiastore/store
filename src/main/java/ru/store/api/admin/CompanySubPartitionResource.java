package ru.store.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.CompanySubPartition;

import java.util.List;

/**
 *
 */
@RestController
public class CompanySubPartitionResource {

    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;

    @RequestMapping(value = "/api/admin/resource/v1/companySubpartition/company/{companyId}", method = RequestMethod.GET)
    public List<CompanySubPartition> company(@PathVariable String companyId) {
        return companySubPartitionDAO.findCompanySubpartitionByCompanyId(Integer.valueOf(companyId));
    }

}
