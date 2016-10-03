package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.entities.Partition;

import java.util.List;

/**
 *
 */
@Service
public class PartitionService {

    @Autowired
    private PartitionDAO partitionDAO;

    public void createPartition(Partition partition) {
        partitionDAO.createPartition(partition);
    }

    public void updatePartition(Partition partition) {
        partitionDAO.updatePartition(partition);
    }

    public void deletePartition(int id) {
        partitionDAO.deletePartition(id);
    }

    public List<Partition> getPartitions() {
        return partitionDAO.getPartitions();
    }

    public Partition PartitionById(int id) {
        return partitionDAO.getPartitionById(id);
    }

}
