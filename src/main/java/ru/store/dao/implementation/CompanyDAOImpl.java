package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    @Transactional
    public void updateCompany(Company company) {
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    @Transactional
    public void deleteCompany(int id) {
        String hql = "delete from Company where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public Company getCompany(int id) {
        String hql = "from Company where id =?";
        List<Company> company = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (company != null && company.size() > 0)
            return company.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public Company getCompany(String name) {
        String hql = "from Company where name =?";
        List<Company> company = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).list();
        if (company != null && company.size() > 0)
            return company.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<Company> getCompanies() {
        String hql = "from Company order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<Company> getCompanies(SubPartition subPartition) {
        return null;
    }
}
