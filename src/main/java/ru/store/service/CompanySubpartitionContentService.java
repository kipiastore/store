package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanySubpartitionContentDAO;
import ru.store.entities.CompanySubpartitionContent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CompanySubpartitionContentService {

    @Autowired
    private CompanySubpartitionContentDAO companySubpartitionContentDAO;
    @Autowired
    private ImageService imageService;

    public void createCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        companySubpartitionContentDAO.createCompanySubpartitionContent(companySubpartitionContent);
    }

    public void deleteCompanySubpartitionContent(Integer id) {
        List<CompanySubpartitionContent> tmp = new ArrayList<>();
        tmp.add(companySubpartitionContentDAO.getCompanySubpartitionContent(id));
        deleteHandler(tmp);
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

    public void deleteCompanySubpartitionContent(List<Integer> companySubpartitionIds) {
        deleteHandler(companySubpartitionContentDAO.getCompanySubpartitionContents(companySubpartitionIds));
        companySubpartitionContentDAO.deleteCompanySubpartitionContent(companySubpartitionIds);
    }

    private void deleteHandler(List<CompanySubpartitionContent> companySubpartitionContents) {
        List<Integer> imageIds = new ArrayList<>();
        for (CompanySubpartitionContent companySubpartitionContent : companySubpartitionContents) {
            imageIds.add(companySubpartitionContent.getImageId());
        }
        imageService.deleteImage(imageIds);
    }
}
