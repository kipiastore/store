package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.entities.Partition;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Repository
public class PartitionDAOImpl implements PartitionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createPartition(Partition partition) {
        sessionFactory.getCurrentSession().save(partition);
    }

    @Override
    @Transactional
    public void updatePartition(Partition partition) {
        sessionFactory.getCurrentSession().update(partition);
    }

    @Override
    @Transactional
    public void refreshCountPartitionToday() {
        String hql = "update Partition set countPartitionToday = :countPartitionToday";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("countPartitionToday", 0).executeUpdate();
    }

    @Override
    @Transactional
    public void deletePartition(int id) {
        String hql = "delete from Partition where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    @Override
    @Transactional
    public List<Partition> getPartitions() {
        String hql = "from Partition order by name";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Partition getPartitionById(int id) {
        String hql = "from Partition where id =?";
        List<Partition> partitions = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (partitions != null && partitions.size() > 0)
            return partitions.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public Partition getPartitionByName(String name) {
        String hql = "from Partition where name =?";
        List<Partition> partitions = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).list();
        if (partitions != null && partitions.size() > 0)
            return partitions.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<Partition> findPortalPartitionsByName(String name) {
        String hql = "from Partition where lower(name) LIKE lower(:name) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("name", "%" + name + "%").list();
    }

    @Override
    @Transactional
    public List<Partition> getPartitions(List<Integer> partitionIds) {
        if (partitionIds.size() == 0)
            return new ArrayList<>();
        String hql = "from Partition where id IN (:partitionIds) order by name";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("partitionIds", partitionIds).list();
    }

}
