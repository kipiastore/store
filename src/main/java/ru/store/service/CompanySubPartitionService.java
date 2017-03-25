package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.CompanySubPartition;

import java.util.List;

/**
 *
 */
@Service
public class CompanySubPartitionService {

    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;

    public void createCompanySubPartition(CompanySubPartition companySubPartition) {
        companySubPartitionDAO.createCompanySubPartition(companySubPartition);
    }

    public void deleteCompanySubpartitionByCompanyId(Integer companyId) {
        companySubPartitionDAO.deleteCompanySubpartitionByCompanyId(companyId);
    }

    public void deleteCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        companySubPartitionDAO.deleteCompanySubpartitionBySubPartitionId(subPartitionId);
    }

    public List<CompanySubPartition> findCompanySubpartitionByCompanyId(Integer companyId) {
        return companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyId);
    }

    public List<CompanySubPartition> findCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        return companySubPartitionDAO.findCompanySubpartitionBySubPartitionId(subPartitionId);
    }
}
