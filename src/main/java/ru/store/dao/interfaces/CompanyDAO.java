package ru.store.dao.interfaces;

import org.springframework.security.core.Authentication;
import ru.store.entities.Company;

import java.util.List;

/**
 *
 */
public interface CompanyDAO {

    void createCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int id);

    Company getCompany(int id);

    List<Company> getCompanies();

    List<Company> getCompaniesByManagerName(String name);

    List<Company> getCompaniesByPaymentStatus(String selectSearchCompanyByPaymentStatus,Authentication auth);

    List<Company> getCompanies(List<Integer> companyIds);

    List<Company> getPortalCompanies(List<Integer> companyIds);

    List<Company> findCompaniesByName(String name);

    List<Company> findCompaniesByNameAndByManager(String name,String managerName);

    List<Company> findCompaniesByNameAndSearchPaymentStatus(String name,String selectSearchCompanyByPaymentStatus,Authentication auth);

    List<Company> findCompaniesByLegalName(String legalName);

    List<Company> findCompaniesByPhone(String phone);

    List<Company> findCompaniesByPhoneAndByManager(String phone,String managerName);

    List<Company> findCompaniesByPhoneAndSearchPaymentStatus(String phone,String selectSearchCompanyByPaymentStatus,Authentication auth);

    List<Company> findCompaniesByEmail(String email);

    List<Company> findCompaniesByEmailAndByManager(String email,String managerName);

    List<Company> findCompaniesByEmailAndSearchPaymentStatus(String email,String selectSearchCompanyByPaymentStatus,Authentication auth);

    List<Company> findCompaniesByKeyword(String keywords);

    List<Company> getCompaniesByLastUpdate();

    List<Integer> getOptimizationCompanies();

    List<Company> findPortalCompaniesByName(String name);

    List<Company> findPortalCompaniesByKeyword(String keywords);

}
