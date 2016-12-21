package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.exceptions.DuplicateException;

import java.util.List;

/**
 *
 */
@Service
public class SubPartitionService {

    @Autowired
    private SubPartitionDAO subPartitionDAO;

    public void createSubPartition(SubPartition subPartition) {
        SubPartition tmp = getSubPartition(subPartition.getName());
        if (tmp != null && tmp.getPartitionId() == subPartition.getPartitionId())
            throw new DuplicateException("Подраздел с тиким именем в указанном разделе уже существует!");
        else
            subPartitionDAO.createSubPartition(subPartition);

    }

    public void updateSubPartition(SubPartition subPartition) {
        subPartitionDAO.updateSubPartition(subPartition);
    }

    public void deleteSubPartition(int id) {
        subPartitionDAO.deleteSubPartition(id);
    }

    public List<SubPartition> getSubPartitions() {
        return subPartitionDAO.getSubPartitions();
    }

    public SubPartition SubPartitionById(int id) {
        return subPartitionDAO.getSubPartitionById(id);
    }

    public List<SubPartition> getSubPartitionsByPartition(Partition partition) {
        return subPartitionDAO.getSubPartitionsByPartition(partition);
    }

    public SubPartition getSubPartition(String name) {
        return subPartitionDAO.getSubPartition(name);
    }

    public SubPartition getSubPartition(int id) {
        return subPartitionDAO.getSubPartition(id);
    }
}
