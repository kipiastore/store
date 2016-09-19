package ru.store.dao.implementation;

import org.springframework.stereotype.Repository;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.entities.Partition;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Repository
public class PartitionDAOImpl implements PartitionDAO {

    @Override
    public List<Partition> getPartitions() {
        List<Partition> partitions = new ArrayList<>();

        // Mock
        Partition partition;
        for (int i = 0; i < 30; i++) {
            partition = new Partition();
            partition.setId(10 + i);
            partition.setName("Дизайн - Графика - Фото " + (10 + i));
            partitions.add(partition);
        }

        return partitions;
    }

    @Override
    public Partition getPartitionById(int id) {
        Partition partition = null;

        // mock
        for (Partition partitionItem : getPartitions()) {
            if (partitionItem.getId() == id)
                partition = partitionItem;
        }

        return partition;
    }
}
