package ru.store.dao.interfaces;

import ru.store.entities.Company;
import ru.store.entities.SubPartition;

import java.util.List;

/**
 *
 */
public interface CompanyDAO {

    Company getCompanyById(int id);
    List<Company> getCompaniesByName(String name);
    List<Company> getCompanies();
    List<Company> getCompaniesBySubPartition(SubPartition subPartition);

}
