package ru.store.dao.interfaces;

import ru.store.entities.CompanyAddress;

import java.util.List;

/**
 *
 */
public interface CompanyAddressDAO {

    void createCompanyAddress(CompanyAddress companyAddress);

    void createCompanyAddress(List<CompanyAddress> companyAddresses);

    void updateCompanyAddress(CompanyAddress companyAddress);

    void updateCompanyAddresses(List<CompanyAddress> companyAddresses);

    void deleteCompanyAddress(Integer id);

    void deleteCompanyAddress(String[] idList);

    void deleteCompanyAddressByCompany(Integer companyId);

    CompanyAddress getCompanyAddress(Integer id);

    List<CompanyAddress> getCompanyAddresses();

    List<CompanyAddress> getCompanyAddresses(Integer companyId);

}
