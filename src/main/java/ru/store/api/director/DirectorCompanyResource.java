package ru.store.api.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.Company;
import ru.store.service.CompanyService;

/**
 *
 */
@RestController
public class DirectorCompanyResource {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/api/mainAdmin/resource/v1/company/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable String id) {
        return companyService.getCompany(Integer.valueOf(id));
    }


}
