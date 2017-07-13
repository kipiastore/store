package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.beans.SearchRequestKeeper;
import ru.store.dao.interfaces.PartitionDAO;
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
    private SubPartitionService subPartitionService;
    @Autowired
    private SearchRequestKeeper searchRequestKeeper;

    public void createPartition(Partition partition) {
        partition.setName(partition.getName().trim());
        if (getPartitionByName(partition.getName()) == null)
            partitionDAO.createPartition(partition);
        else
            throw new DuplicateException("Раздел с таким именем уже существует!");
        searchRequestKeeper.save(partition.getName(), 3);
    }

    public void updatePartition(Partition partition) {
        partition.setName(partition.getName().trim());
        partitionDAO.updatePartition(partition);
    }

    public void refreshCountPartitionToday() {
        partitionDAO.refreshCountPartitionToday();
    }

    public void deletePartition(int id) {
        subPartitionService.deleteSubPartitionByPartition(id);
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

    public Partition getPartitionById(int id) {
        return partitionDAO.getPartitionById(id);
    }

    public List<Partition> findPortalPartitionsByName(String name) {
        return partitionDAO.findPortalPartitionsByName(name);
    }

    public List<Partition> getPartitions(List<Integer> partitionIds) {
        return partitionDAO.getPartitions(partitionIds);
    }
}
