package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;
import ru.store.entities.SubPartition;

import java.util.List;

/**
 *
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    public void createCompany(Company company) {
        companyDAO.createCompany(company);
    }

    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    public void deleteCompany(int id) {
        companyDAO.deleteCompany(id);
    }

    public Company getCompany(int id) {
        return companyDAO.getCompany(id);
    }

    public Company getCompany(String name) {
        return companyDAO.getCompany(name);
    }

    public List<Company> getCompanies() {
        return companyDAO.getCompanies();
    }

    public List<Company> getCompanies(SubPartition subPartition) {
        return companyDAO.getCompanies(subPartition);
    }

}

