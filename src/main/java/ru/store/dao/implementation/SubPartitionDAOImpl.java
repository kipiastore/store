package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createSubPartition(SubPartition subPartition) {
        sessionFactory.getCurrentSession().save(subPartition);
    }

    @Override
    @Transactional
    public void updateSubPartition(SubPartition subPartition) {
        sessionFactory.getCurrentSession().update(subPartition);
    }

    @Override
    @Transactional
    public void deleteSubPartition(int id) {
        String hql = "delete from SubPartition where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<SubPartition> getSubPartitions() {
        String hql = "from SubPartition order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

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
    public List<SubPartition> getSubPartitionsByPartition(Partition partition) {
        List<SubPartition> subPartitions = new ArrayList<>();
        // Mock
        for (SubPartition subPartition : getSubPartitions()) {
            //if (subPartition.getPartition().equals(partition))
                //subPartitions.add(subPartition);
        }
        return subPartitions;
    }
}
