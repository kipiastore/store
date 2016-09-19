package ru.store.dao.interfaces;

import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.List;

/**
 *
 */
public interface SubPartitionDAO {

    List<SubPartition> getSubPartitions();
    List<SubPartition> getSubPartitionsByPartition(Partition partition);

}
