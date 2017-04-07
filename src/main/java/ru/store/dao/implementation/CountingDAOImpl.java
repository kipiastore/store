package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CountingDAO;
import ru.store.entities.CountingPortalPage;

import java.util.List;

/**
 * Created by Akex on 27.03.2017.
 */
@Repository
public class CountingDAOImpl implements CountingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addCountPortalPage(CountingPortalPage c) {
            sessionFactory.getCurrentSession().saveOrUpdate(c);
    }

    @Override
    @Transactional
    public CountingPortalPage getCountPortalPage() {
        String hql = "from CountingPortalPage";
        List<CountingPortalPage> count = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (count != null && count.size() > 0)
            return count.get(0);
        else
            return null;
    }
}
