package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Partition;
import ru.store.exceptions.DuplicateException;

import java.util.List;

/**
 *
 */
@Service
public class PartitionService {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    public void createPartition(Partition partition) {
        if (getPartitionByName(partition.getName()) == null)
            partitionDAO.createPartition(partition);
        else
            throw new DuplicateException("Раздел с тиким именем уже существует!");
    }

    public void updatePartition(Partition partition) {
        partitionDAO.updatePartition(partition);
    }

    public void deletePartition(int id) {
        subPartitionDAO.deleteSubPartitionByPartition(id);
        partitionDAO.deletePartition(id);
    }

    public List<Partition> getPartitions() {
        return partitionDAO.getPartitions();
    }

    public Partition PartitionById(int id) {
        return partitionDAO.getPartitionById(id);
    }

    public Partition getPartitionByName(String name) {
        return partitionDAO.getPartitionByName(name);
    }

}
