package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Package;

import java.util.List;

/**
 *
 */
@Repository
public class PackageDAOImpl implements PackageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createPackage(Package aPackage) {
        sessionFactory.getCurrentSession().save(aPackage);
    }

    @Override
    @Transactional
    public void deletePackage(Integer id) {
        String hql = "delete from Package where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<Package> getPackages() {
        String hql = "from Package order by priority desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Package getPackage(String name) {
        String hql = "from Region where name =?";
        List<Package> packages = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).list();
        if (packages != null && packages.size() > 0)
            return packages.get(0);
        else
            return null;
    }
}
