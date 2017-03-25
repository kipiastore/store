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

    public void createCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        companySubpartitionContentDAO.createCompanySubpartitionContent(companySubpartitionContent);
    }

    public void deleteCompanySubpartitionContent(Integer id) {
        companySubpartitionContentDAO.deleteCompanySubpartitionContent(id);
    }

    public void updateCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        companySubpartitionContentDAO.updateCompanySubpartitionContent(companySubpartitionContent);
    }

    public List<CompanySubpartitionContent> getCompanySubpartitionContents() {
        return companySubpartitionContentDAO.getCompanySubpartitionContents();
    }

    public CompanySubpartitionContent getCompanySubpartitionContent(Integer id) {
        return companySubpartitionContentDAO.getCompanySubpartitionContent(id);
    }

    public List<CompanySubpartitionContent> getCompanySubpartitionContents(Integer companyId) {
        return companySubpartitionContentDAO.getCompanySubpartitionContents(companyId);
    }
}
