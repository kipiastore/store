package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.ReportDAO;
import ru.store.entities.Report;

import java.util.List;

/**
 *
 */
@Repository
public class ReportImpl implements ReportDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createReport(Report report) {
        sessionFactory.getCurrentSession().save(report);
    }

    @Override
    @Transactional
    public void updateReport(Report report) {
        sessionFactory.getCurrentSession().update(report);
    }

    @Override
    @Transactional
    public void deleteReport(Integer id) {
        String hql = "delete from Report where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public List<Report> getReports() {
        String hql = "from Report order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Report getReport(Integer id) {
        String hql = "from Report where id =?";
        List<Report> reports = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (reports != null && reports.size() > 0)
            return reports.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<Report> getReportsByCompanyId(Integer companyId) {
        String hql = "from Report where companyId =:companyId";
        return sessionFactory.getCurrentSession().createQuery(hql).setInteger("companyId", companyId).list();
    }
}
