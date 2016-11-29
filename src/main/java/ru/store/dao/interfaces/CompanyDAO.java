package ru.store.dao.interfaces;

import ru.store.entities.Company;
import ru.store.entities.SubPartition;

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

    List<Company> getCompanies(List<Integer> companyIds);

    List<Company> findCompaniesByName(String name);

    List<Company> findCompaniesByLegalName(String legalName);

    List<Company> findCompaniesByPhone(String phone);

    List<Company> findCompaniesByEmail(String email);

    List<Company> getCompaniesByLastUpdate();
}
