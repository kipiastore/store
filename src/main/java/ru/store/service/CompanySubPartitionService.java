package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.CompanySubPartition;
import ru.store.entities.CompanySubpartitionContent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CompanySubPartitionService {

    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;
    @Autowired
    private CompanySubpartitionContentService companySubpartitionContentService;

    public void createCompanySubPartition(CompanySubPartition companySubPartition) {
        companySubPartitionDAO.createCompanySubPartition(companySubPartition);
    }

    public void deleteCompanySubpartitionByCompanyId(Integer companyId) {
        deleteHandler(companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyId));
        companySubPartitionDAO.deleteCompanySubpartitionByCompanyId(companyId);
    }

    public void deleteCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        deleteHandler(companySubPartitionDAO.findCompanySubpartitionBySubPartitionId(subPartitionId));
        companySubPartitionDAO.deleteCompanySubpartitionBySubPartitionId(subPartitionId);
    }

    public List<CompanySubPartition> findCompanySubpartitionByCompanyId(Integer companyId) {
        return companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyId);
    }

    public List<CompanySubPartition> findCompanySubpartitionByCompanyId(List<Integer> companyIds) {
        return companySubPartitionDAO.findCompanySubpartitionByCompanyId(companyIds);
    }

    public List<CompanySubPartition> findCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        return companySubPartitionDAO.findCompanySubpartitionBySubPartitionId(subPartitionId);
    }

    public List<CompanySubPartition> findCompanySubpartitionBySubPartitionsId(List<Integer> subPartitionId) {
        return companySubPartitionDAO.findCompanySubpartitionBySubPartitionsId(subPartitionId);
    }

    public List<CompanySubPartition> getCompanySubPartitions() {
        return companySubPartitionDAO.getCompanySubPartitions();
    }

    public void deleteCompanySubpartitionBySubPartitionIds(List<Integer> subPartitionIds) {
        deleteHandler(companySubPartitionDAO.findCompanySubpartitionBySubPartitionsId(subPartitionIds));
        companySubPartitionDAO.deleteCompanySubpartitionBySubPartitionIds(subPartitionIds);
    }

    public void deleteCompanySubpartitionIds(List<Integer> idList) {
        deleteHandler(companySubPartitionDAO.findCompanySubpartitionByIds(idList));
        companySubPartitionDAO.deleteCompanySubpartitionIds(idList);
    }

    public List<CompanySubPartition> findCompanySubpartitionByIds(List<Integer> idList) {
        return companySubPartitionDAO.findCompanySubpartitionByIds(idList);
    }

    private void deleteHandler(List<CompanySubPartition> companySubPartitions) {
        List<Integer> companySubpartitionIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companySubpartitionIds.add(companySubPartition.getId());
        }
        System.out.println("CompanySubPartitionService.deleteHandler -------- " + companySubpartitionIds);
        companySubpartitionContentService.deleteCompanySubpartitionContent(companySubpartitionIds);
    }

}
