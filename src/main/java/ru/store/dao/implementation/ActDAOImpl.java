package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.ActDAO;
import ru.store.entities.Act;

import java.util.List;

/**
 *
 */
@Repository
public class ActDAOImpl implements ActDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createAct(Act report) {
        sessionFactory.getCurrentSession().save(report);
    }

    @Override
    @Transactional
    public void updateAct(Act report) {
        sessionFactory.getCurrentSession().update(report);
    }

    @Override
    @Transactional
    public void deleteAct(Integer id) {
        String hql = "delete from Act where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<Act> getActs() {
        String hql = "from Act order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Act getAct(Integer id) {
        String hql = "from Act where id =?";
        List<Act> reports = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (reports != null && reports.size() > 0)
            return reports.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<Act> getActsByCompanyId(Integer companyId) {
        String hql = "from Act where companyId =:companyId";
        return sessionFactory.getCurrentSession().createQuery(hql).setInteger("companyId", companyId).list();
    }
}
