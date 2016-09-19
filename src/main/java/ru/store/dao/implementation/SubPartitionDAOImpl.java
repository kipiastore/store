package ru.store.dao.implementation;

import org.springframework.stereotype.Repository;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class SubPartitionDAOImpl implements SubPartitionDAO {

    @Override
    public List<SubPartition> getSubPartitions() {
        List<SubPartition> subPartitions = new ArrayList<>();

        // Mock
        SubPartition subPartition;
        for (Partition partition : new PartitionDAOImpl().getPartitions()) {
            for (int i = 0; i < 5; i++) {
                subPartition = new SubPartition();
                subPartition.setId(i);
                subPartition.setName("Test " + i);
                subPartition.setPartition(partition);
                subPartitions.add(subPartition);
            }
        }

        return subPartitions;
    }

    @Override
    public List<SubPartition> getSubPartitionsByPartition(Partition partition) {
        List<SubPartition> subPartitions = new ArrayList<>();

        // Mock
        for (SubPartition subPartition : getSubPartitions()) {
            if (subPartition.getPartition().equals(partition))
                subPartitions.add(subPartition);
        }

        return subPartitions;
    }
}
