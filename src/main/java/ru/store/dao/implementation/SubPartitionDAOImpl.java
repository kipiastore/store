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
    public SubPartition getSubPartition(String name) {
        String hql = "from SubPartition where name =?";
        List<SubPartition> subPartitions = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).list();
        if (subPartitions != null && subPartitions.size() > 0)
            return subPartitions.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<SubPartition> getSubPartitions() {
        String hql = "from SubPartition order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public SubPartition getSubPartitionById(int id) {
        String hql = "from SubPartition where id =?";
        List<SubPartition> subPartitions = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (subPartitions != null && subPartitions.size() > 0)
            return subPartitions.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<SubPartition> getSubPartitionsByPartition(Partition partition) {
        String hql = "from SubPartition where partitionId =?";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, partition.getId()).list();
    }

    @Override
    @Transactional
    public List<SubPartition> getSubPartitionsByPartitionId(Integer partitionId) {
        String hql = "from SubPartition where partitionId =?";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, partitionId).list();
    }

    @Override
    @Transactional
    public List<SubPartition> getSubPartitions(List<Integer> subPartitionIds) {
        if (subPartitionIds.size() == 0)
            return new ArrayList<>();
        String hql = "from SubPartition where id IN (:subPartitionIds)";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("subPartitionIds", subPartitionIds).list();
    }
}
