package ru.store.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;

/**
 *
 */
@RestController
public class CompanyResource {

    @Autowired
    private CompanyDAO companyDAO;

    @RequestMapping(value = "/api/resource/v1/company/{id}", method = RequestMethod.GET)
    public Company company(@PathVariable String id) {
        return companyDAO.getCompany(Integer.valueOf(id));
    }

}
