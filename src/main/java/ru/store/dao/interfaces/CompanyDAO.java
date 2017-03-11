package ru.store.dao.interfaces;

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

    List<Company> getCompaniesByPaymentStatus(String selectSearchCompanyByPaymentStatus);

    List<Company> getCompanies(List<Integer> companyIds);

    List<Company> getPortalCompanies(List<Integer> companyIds);

    List<Company> findCompaniesByName(String name);

    List<Company> findCompaniesByNameAndSearchPaymentStatus(String name,String selectSearchCompanyByPaymentStatus);

    List<Company> findCompaniesByLegalName(String legalName);

    List<Company> findCompaniesByPhone(String phone);

    List<Company> findCompaniesByPhoneAndSearchPaymentStatus(String phone,String selectSearchCompanyByPaymentStatus);

    List<Company> findCompaniesByEmail(String email);

    List<Company> findCompaniesByEmailAndSearchPaymentStatus(String email,String selectSearchCompanyByPaymentStatus);

    List<Company> findCompaniesByKeyword(String keywords);

    List<Company> getCompaniesByLastUpdate();

    List<Integer> getOptimizationCompanies();

    List<Company> findPortalCompaniesByName(String name);

    List<Company> findPortalCompaniesByKeyword(String keywords);

}
