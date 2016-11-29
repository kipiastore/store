package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanySubPartitionDAO;
import ru.store.entities.CompanySubPartition;

import java.util.List;

/**
 *
 */
@Repository
public class CompanySubPartitionDAOImpl implements CompanySubPartitionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<CompanySubPartition> findCompanySubpartitionByCompanyId(Integer companyId) {
        String hql = "from CompanySubPartition where companyId =:companyId";
        return sessionFactory.getCurrentSession().createQuery(hql).setInteger("companyId", companyId).list();
    }

    @Override
    @Transactional
    public List<CompanySubPartition> findCompanySubpartitionByCompanyId(List<Integer> companyIds) {
        String hql = "from CompanySubPartition where companyId in (:companyIds)";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("companyIds", companyIds).list();
    }

    @Override
    @Transactional
    public List<CompanySubPartition> findCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        String hql = "from CompanySubPartition where subPartitionId =:subPartitionId";
        return sessionFactory.getCurrentSession().createQuery(hql).setInteger("subPartitionId", subPartitionId).list();
    }

    @Override
    @Transactional
    public void createCompanySubPartition(CompanySubPartition companySubPartition) {
        sessionFactory.getCurrentSession().save(companySubPartition);
    }

    @Override
    @Transactional
    public void deleteCompanySubpartitionByCompanyId(Integer companyId) {
        String hql = "delete from CompanySubPartition where companyId =:companyId";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("companyId", companyId).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteCompanySubpartitionBySubPartitionId(Integer subPartitionId) {
        String hql = "delete from CompanySubPartition where subPartitionId =:subPartitionId";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("subPartitionId", subPartitionId).executeUpdate();
    }
}
