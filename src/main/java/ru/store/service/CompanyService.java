package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.store.beans.SearchRequestKeeper;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;
import ru.store.exceptions.DuplicateException;
import ru.store.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private CompanyReminderService companyReminderService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SearchRequestKeeper searchRequestKeeper;

    public void createCompany(Company company) {
        if (getCompanyByName(company.getName()) == null) {
            companyDAO.createCompany(company);
            searchRequestKeeper.save(company.getName(), 3);
        } else {
            throw new DuplicateException("Компания с таким именем уже существует!");
        }
    }

    public void updateCompany(Company company) {
        Company oldCompany = getCompany(company.getId());
        if (oldCompany == null) {
            throw new NotFoundException("Фирма не найдена.");
        }
        company.setCreatedDate(oldCompany.getCreatedDate());
        company.setLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
        company.setOwner(oldCompany.getOwner());
        companyDAO.updateCompany(company);
    }

    public void updateCompanyByManager(Company company) {
        Company oldCompany = getCompany(company.getId());
        if (oldCompany == null) {
            throw new NotFoundException("Фирма не найдена.");
        }
        oldCompany.setDirectorFullName(company.getDirectorFullName());
        oldCompany.setContactPerson(company.getContactPerson());
        oldCompany.setEmail(company.getEmail());
        oldCompany.setSite(company.getSite());
        oldCompany.setInn(company.getInn());
        oldCompany.setLegalName(company.getLegalName());
        oldCompany.setLegalAddress(company.getLegalAddress());
        oldCompany.setPhone(company.getPhone());
        oldCompany.setFax(company.getFax());
        oldCompany.setLastModifiedBy(company.getLastModifiedBy());
        oldCompany.setLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
        companyDAO.updateCompany(oldCompany);
    }

    public void deleteCompany(int id) {
        Company tmp = companyDAO.getCompany(id);
        if (tmp.getImageId() != null)
            imageService.deleteImage(tmp.getImageId());
        companyDAO.deleteCompany(id);
        companyAddressService.deleteCompanyAddressByCompany(id);
        companyReminderService.deleteCompanyRemindersByCompany(id);
    }

    public Company getCompany(int id) {
        return companyDAO.getCompany(id);
    }

    public Company getCompanyByName(String name) {
        return companyDAO.getCompanyByName(name);
    }

    public List<Company> getCompanies() {
        return companyDAO.getCompanies();
    }

    public List<Company> getCompaniesByManagerName(String name) {
        return companyDAO.getCompaniesByManagerName(name);
    }


    public List<Company> getCompaniesByPaymentStatus(String selectSearchCompanyByPaymentStatus,Authentication auth) {
        return companyDAO.getCompaniesByPaymentStatus(selectSearchCompanyByPaymentStatus,auth);
    }

    public List<Company> getCompaniesByLastUpdate() { return companyDAO.getCompaniesByLastUpdate(); }

    public List<Company> findCompaniesByName(String name) {
        return companyDAO.findCompaniesByName(name);
    }

    public List<Company> findCompaniesByNameAndByManager(String name,String managerName) {
        return companyDAO.findCompaniesByNameAndByManager(name,managerName);
    }

    public List<Company> findCompaniesByNameAndSearchPaymentStatus(String name, String selectSearchCompanyByPaymentStatus, Authentication auth) {
        return companyDAO.findCompaniesByNameAndSearchPaymentStatus(name,selectSearchCompanyByPaymentStatus,auth);
    }

    public List<Company> findCompaniesByLegalName(String legalName) {
        return companyDAO.findCompaniesByLegalName(legalName);
    }

    public List<Company> findCompaniesByPhone(String phone) {
        return companyDAO.findCompaniesByPhone(phone);
    }

    public List<Company> findCompaniesByPhoneAndByManager(String phone,String managerName) {
        return companyDAO.findCompaniesByPhoneAndByManager(phone,managerName);
    }

    public List<Company> findCompaniesByPhoneAndSearchPaymentStatus(String phone,String selectSearchCompanyByPaymentStatus,Authentication auth) {
        return companyDAO.findCompaniesByPhoneAndSearchPaymentStatus(phone,selectSearchCompanyByPaymentStatus,auth);
    }

    public List<Company> findCompaniesByEmail(String email) {
        return companyDAO.findCompaniesByEmail(email);
    }

    public List<Company> findCompaniesByEmailAndByManager(String email,String managerName) {
        return companyDAO.findCompaniesByEmailAndByManager(email,managerName);
    }

    public List<Company> findCompaniesByEmailAndSearchPaymentStatus(String email,String selectSearchCompanyByPaymentStatus,Authentication auth) {
        return companyDAO.findCompaniesByEmailAndSearchPaymentStatus(email,selectSearchCompanyByPaymentStatus,auth);
    }

    public List<Company> getCompanies(List<Integer> companyIds) {
        return companyDAO.getCompanies(companyIds);
    }

    public List<Company> getPortalCompanies(List<Integer> companyIds) {
        return companyDAO.getPortalCompanies(companyIds);
    }

    public List<Company> findCompaniesByKeyword(String keywords) {
        return companyDAO.findCompaniesByKeyword(keywords);
    }

    public List<Integer> getOptimizationCompanies() {
        return companyDAO.getOptimizationCompanies();
    }

    public List<Company> findPortalCompaniesByName(String name) {
        return companyDAO.findPortalCompaniesByName(name);
    }

    public List<Company> findPortalCompaniesByKeyword(String keywords) {
        return companyDAO.findPortalCompaniesByKeyword(keywords);
    }
}

