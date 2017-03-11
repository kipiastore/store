package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.RegionDAO;
import ru.store.entities.Region;

import java.util.List;

/**
 *
 */
@Repository
public class RegionDAOImpl implements RegionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createRegion(Region region) {
        sessionFactory.getCurrentSession().save(region);
    }

    @Override
    @Transactional
    public void deleteRegion(Integer id) {
        String hql = "delete from Region where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<Region> getRegions() {
        String hql = "from Region order by name asc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Region getRegion(String name) {
        String hql = "from Region where name =?";
        List<Region> regions = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).list();
        if (regions != null && regions.size() > 0)
            return regions.get(0);
        else
            return null;
    }
}
