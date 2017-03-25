package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanySubpartitionContentDAO;
import ru.store.entities.CompanySubpartitionContent;

import java.util.List;

/**
 *
 */
@Service
public class CompanySubpartitionContentService {

    @Autowired
    private CompanySubpartitionContentDAO companySubpartitionContentDAO;

    void createCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        companySubpartitionContentDAO.createCompanySubpartitionContent(companySubpartitionContent);
    }

    void deleteCompanySubpartitionContent(Integer id) {
        companySubpartitionContentDAO.deleteCompanySubpartitionContent(id);
    }

    void updateCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        companySubpartitionContentDAO.updateCompanySubpartitionContent(companySubpartitionContent);
    }

    List<CompanySubpartitionContent> getCompanySubpartitionContents() {
        return companySubpartitionContentDAO.getCompanySubpartitionContents();
    }

    CompanySubpartitionContent getCompanySubpartitionContent(Integer id) {
        return companySubpartitionContentDAO.getCompanySubpartitionContent(id);
    }
}
