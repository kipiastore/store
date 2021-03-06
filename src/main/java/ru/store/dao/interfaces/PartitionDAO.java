package ru.store.dao.interfaces;

import ru.store.entities.Partition;

import java.util.List;

/**
 *
 */
public interface PartitionDAO {

    void createPartition(Partition partition);

    void updatePartition(Partition partition);

    void refreshCountPartitionToday();

    void deletePartition(int id);

    List<Partition> getPartitions();

    Partition getPartitionById(int id);

    Partition getPartitionByName(String name);

    List<Partition> findPortalPartitionsByName(String name);

    List<Partition> getPartitions(List<Integer> partitionIds);
}
