package ru.store.dao.implementation;

import org.springframework.stereotype.Repository;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Company getCompanyById(int id) {
        Company company = null;

        // mock
        for (Company companyItem : getCompanies()) {
            if (companyItem.getId() == id)
                company = companyItem;
        }

        return company;
    }

    @Override
    public List<Company> getCompaniesByName(String name) {
        List<Company> companies = new ArrayList<>();

        // mock
        for (Company companyItem : getCompanies()) {
            if (companyItem.getName().contains(name))
                companies.add(companyItem);
        }

        return companies;
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();

        // Mock
        Company company;
        Partition p = new PartitionDAOImpl().getPartitions().get(1);
        for (int i = 0; i < 3; i++) {
            company = new Company();
            company.setId(i + 1000);
            company.setName("Фото на заказ " + (i + 1000));
            company.setPartition(p);
            company.setSubPartitions(new SubPartitionDAOImpl().getSubPartitionsByPartition(p));
            companies.add(company);
        }

        return companies;
    }

    @Override
    public List<Company> getCompaniesBySubPartition(SubPartition subPartition) {
        List<Company> companies = new ArrayList<>();

        // mock
        for (Company companyItem : getCompanies()) {
            if (companyItem.getSubPartitions().contains(subPartition)) {
                companies.add(companyItem);
            }

        }

        return companies;
    }
}
