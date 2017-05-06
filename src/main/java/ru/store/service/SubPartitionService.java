package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.exceptions.DuplicateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service
public class SubPartitionService {

    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    public void createSubPartition(SubPartition subPartition) {
        SubPartition tmp = getSubPartition(subPartition.getName());
        if (tmp != null && Objects.equals(tmp.getPartitionId(), subPartition.getPartitionId()))
            throw new DuplicateException("Подраздел с тиким именем в указанном разделе уже существует!");
        else
            subPartitionDAO.createSubPartition(subPartition);

    }

    public void updateSubPartition(SubPartition subPartition) {
        subPartitionDAO.updateSubPartition(subPartition);
    }

    public void deleteSubPartition(int id) {
        List<SubPartition> tmp = new ArrayList<>();
        tmp.add(subPartitionDAO.getSubPartitionById(id));
        deleteSubPartitionHandler(tmp);
        subPartitionDAO.deleteSubPartition(id);
    }

    public List<SubPartition> getSubPartitions() {
        return subPartitionDAO.getSubPartitions();
    }

    public SubPartition getSubPartitionById(int id) {
        return subPartitionDAO.getSubPartitionById(id);
    }

    public List<SubPartition> getSubPartitionsByPartition(Partition partition) {
        return subPartitionDAO.getSubPartitionsByPartition(partition);
    }

    public List<SubPartition> getSubPartitions(List<Integer> subPartitionIds) {
        return subPartitionDAO.getSubPartitions(subPartitionIds);
    }

    public SubPartition getSubPartition(String name) {
        return subPartitionDAO.getSubPartition(name);
    }

    public SubPartition getSubPartition(int id) {
        return subPartitionDAO.getSubPartition(id);
    }

    public List<SubPartition> getSubPartitionsByPartitionId(Integer partitionId) {
        return subPartitionDAO.getSubPartitionsByPartitionId(partitionId);
    }

    public void deleteSubPartitionByPartition(int partitionId) {
        deleteSubPartitionHandler(subPartitionDAO.getSubPartitionsByPartitionId(partitionId));
        subPartitionDAO.deleteSubPartitionByPartition(partitionId);
    }

    private void deleteSubPartitionHandler(List<SubPartition> subPartitions) {
        List<Integer> subPartitionIdList = new ArrayList<>();
        for (SubPartition subPartition : subPartitions) {
            subPartitionIdList.add(subPartition.getId());
        }
        companySubPartitionService.deleteCompanySubpartitionBySubPartitionIds(subPartitionIdList);
    }

    public List<SubPartition> findPortalSubPartitionsByName(String name) {
        return subPartitionDAO.findPortalSubPartitionsByName(name);
    }

}
