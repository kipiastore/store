package ru.store.dao.interfaces;

import ru.store.entities.Partition;

import java.util.List;

/**
 *
 */
public interface PartitionDAO {

    List<Partition> getPartitions();
    Partition getPartitionById(int id);

}
