package ru.store.dao.interfaces;

import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.List;

/**
 *
 */
public interface SubPartitionDAO {

    SubPartition getSubPartitionById(int id);

    List<SubPartition> getSubPartitions();

    List<SubPartition> getSubPartitionsByPartition(Partition partition);

    void createSubPartition(SubPartition subPartition);

    void updateSubPartition(SubPartition subPartition);

    void deleteSubPartition(int id);
}
