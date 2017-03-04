package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanyAddressDAO;
import ru.store.entities.CompanyAddress;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CompanyAddressService {

    @Autowired
    private CompanyAddressDAO companyAddressDAO;

    public void createCompanyAddress(CompanyAddress companyAddress) {
        companyAddressDAO.createCompanyAddress(companyAddress);
    }

    public void createCompanyAddress(List<CompanyAddress> companyAddresses) {
        companyAddressDAO.createCompanyAddress(companyAddresses);
    }

    public void updateCompanyAddress(CompanyAddress companyAddress) {
        companyAddressDAO.updateCompanyAddress(companyAddress);
    }

    public void updateCompanyAddresses(List<CompanyAddress> companyAddresses) {
        companyAddressDAO.updateCompanyAddresses(companyAddresses);
    }

    public void deleteCompanyAddress(Integer id) {
        companyAddressDAO.deleteCompanyAddress(id);
    }

    public void deleteCompanyAddress(String[] idList) {
        companyAddressDAO.deleteCompanyAddress(idList);
    }

    public void deleteCompanyAddressByCompany(Integer companyId) {
        companyAddressDAO.deleteCompanyAddressByCompany(companyId);
    }

    public List<String> getCompanyAddressString(Integer id) {
        List<CompanyAddress> companyAddresses=companyAddressDAO.getCompanyAddresses(id);
        List<String> list=new ArrayList<>();
        for(CompanyAddress companyAddress:companyAddresses){
            list.add(companyAddress.getAddress());
        }
        return list ;
    }

    public CompanyAddress getCompanyAddress(Integer id) {
        return companyAddressDAO.getCompanyAddress(id);
    }

    public List<CompanyAddress> getCompanyAddresses() {
        return companyAddressDAO.getCompanyAddresses();
    }

    public List<CompanyAddress> getCompanyAddresses(Integer companyId) {
        return companyAddressDAO.getCompanyAddresses(companyId);
    }

}
