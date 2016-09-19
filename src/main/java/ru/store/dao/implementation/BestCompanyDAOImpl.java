package ru.store.dao.implementation;

import org.springframework.stereotype.Repository;
import ru.store.dao.interfaces.BestCompanyDAO;
import ru.store.entities.BestCompany;
import ru.store.entities.Company;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class BestCompanyDAOImpl implements BestCompanyDAO {

    @Override
    public List<BestCompany> getBestCompanies() {
        List<BestCompany> bestCompanies = new ArrayList<>();

        // Mock
        BestCompany bestCompany;
        Company company;
        for (int i = 0; i < 26; i++) {
            bestCompany = new BestCompany();
            company = new Company();
            company.setId(i + 10);
            company.setName("Дизайн - Графика - Фото");
            company.setLogo("testLogo.jpg");
            bestCompany.setId(i);
            bestCompany.setCompany(company);
            bestCompanies.add(bestCompany);
        }

        return bestCompanies;
    }
}
