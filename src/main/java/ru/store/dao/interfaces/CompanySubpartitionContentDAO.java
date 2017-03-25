package ru.store.dao.interfaces;

import ru.store.entities.CompanySubpartitionContent;

import java.util.List;

/**
 *
 */
public interface CompanySubpartitionContentDAO {

    void createCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent);

    void deleteCompanySubpartitionContent(Integer id);

    void updateCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent);

    List<CompanySubpartitionContent> getCompanySubpartitionContents();

    CompanySubpartitionContent getCompanySubpartitionContent(Integer id);

    List<CompanySubpartitionContent> getCompanySubpartitionContents(Integer companyId);

}
