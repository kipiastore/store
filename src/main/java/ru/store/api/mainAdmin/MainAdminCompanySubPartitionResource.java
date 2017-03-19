package ru.store.api.mainAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.CompanySubPartition;
import ru.store.service.CompanySubPartitionService;

import java.util.List;

/**
 *
 */
@RestController
public class MainAdminCompanySubPartitionResource {

    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    @RequestMapping(value = "/api/mainAdmin/resource/v1/companySubpartition/company/{companyId}", method = RequestMethod.GET)
    public List<CompanySubPartition> company(@PathVariable String companyId) {
        return companySubPartitionService.findCompanySubpartitionByCompanyId(Integer.valueOf(companyId));
    }

}
