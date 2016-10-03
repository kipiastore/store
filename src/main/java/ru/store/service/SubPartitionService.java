package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.SubPartition;
import java.util.List;

/**
 *
 */
@Service
public class SubPartitionService {

    @Autowired
    private SubPartitionDAO subPartitionDAO;

    public void createSubPartition(SubPartition subPartition) {
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
}
