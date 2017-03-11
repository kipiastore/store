package ru.store.dao.interfaces;

import ru.store.entities.CompanySubPartition;

import java.util.List;

/**
 *
 */
public interface CompanySubPartitionDAO {

    void createCompanySubPartition(CompanySubPartition companySubPartition);

    void deleteCompanySubpartitionByCompanyId(Integer companyId);

    void deleteCompanySubpartitionBySubPartitionId(Integer subPartitionId);

    List<CompanySubPartition> findCompanySubpartitionByCompanyId(Integer companyId);

    List<CompanySubPartition> findCompanySubpartitionBySubPartitionId(Integer subPartitionId);

    List<CompanySubPartition> findCompanySubpartitionByCompanyId(List<Integer> companyIds);

    List<CompanySubPartition> getCompanySubPartitions();


    List<CompanySubPartition> findCompanySubpartitionBySubPartitionsId(List<Integer> subPartitionId);


    }
