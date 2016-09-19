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
    public SubPartition getSubPartitionById(int id) {
        SubPartition subPartition = null;

        // mock
        for (SubPartition subPartitionItem : getSubPartitions()) {
            if (subPartitionItem.getId() == id)
                subPartition = subPartitionItem;
        }

        return subPartition;
    }

    @Override
    public List<SubPartition> getSubPartitions() {
        List<SubPartition> subPartitions = new ArrayList<>();

        // Mock
        SubPartition subPartition;
        int count = 0;
        for (Partition partition : new PartitionDAOImpl().getPartitions()) {
            for (int i = 0; i < 30; i++) {
                subPartition = new SubPartition();
                subPartition.setId(i + count);
                subPartition.setName("Test " + (i + count));
                subPartition.setPartition(partition);
                subPartitions.add(subPartition);
                count++;
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
