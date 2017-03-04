package ru.store.dao.implementation;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanyReminderDAO;
import ru.store.entities.CompanyReminder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Akex on 12.10.2016.
 */
@Repository
public class CompanyReminderDAOimp implements CompanyReminderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createCompanyReminder(CompanyReminder reminder) {
        sessionFactory.getCurrentSession().save(reminder);
    }

    @Override
    @Transactional
    public void updateCompanyReminder(CompanyReminder reminder) {
            sessionFactory.getCurrentSession().update(reminder);
    }

    @Override
    @Transactional
    public CompanyReminder getCompanyReminder(Integer id) {
        String hql = "from CompanyReminder where id =?  ";
        List<CompanyReminder> companyReminders = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (companyReminders != null && companyReminders.size() > 0)
            return companyReminders.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<CompanyReminder> getCompanyReminders(Integer companyId) {
        String hql = "from CompanyReminder where companyId =? order by dateReminder desc,id desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId).list();
    }
    @Override
    @Transactional
    public  String  getLastCompanyReminderComment(Integer companyId) {
        String hql = "select MAX(id),count(id) from CompanyReminder where companyId =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId);
        List<Object[]> listResult = query.list();
        String defaultValue="";
        Integer maxId = 0;
        for (Object[] aRow : listResult) {
            maxId = (Integer) aRow[0];
        }
        if (maxId != null) {
            hql = "select commentReminder from CompanyReminder where id =:maxId";
            List<String> commentReminder = sessionFactory.getCurrentSession().createQuery(hql).setInteger("maxId", maxId).list();
            return commentReminder.get(0);
        }
        else {
            return defaultValue;
        }
    }
    @Override
    @Transactional
    public  String  getLastCompanyReminderDateHourType(Integer companyId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        String hql = "select MAX(dateReminder)from CompanyReminder where companyId =?";
        List<Date> date = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId).list();
        Date tempDate = date.get(0);
        String hour = "";
        String type = "";
        if (tempDate != null) {
            hql = "select hourReminder,typeReminder from CompanyReminder where dateReminder =:tempDate";
            List<Object[]> dateHourType = sessionFactory.getCurrentSession().createQuery(hql).setParameter("tempDate", tempDate).list();
            for (Object[] aRow : dateHourType) {
                hour = (String) aRow[0];
                type = (String) aRow[1];
            }

            return sdf.format(tempDate) + " " + hour + " " + type;
        } else {
          return "";
        }
    }
    @Override
    @Transactional
    public  String  getLastCompanyReminderTypeAndAmount(Integer companyId) {
        String hql = "select MAX(id),count(companyId) from CompanyReminder where companyId =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId);
        List<Object[]> listResult = query.list();
        String defaultValue="";
        Integer maxId = 0;
        Long countId;
        countId = Long.valueOf(0);
        for (Object[] aRow : listResult) {
            maxId = (Integer) aRow[0];
            countId = (Long) aRow[1];
        }
        if (maxId != null) {
            hql = "select typeReminder from CompanyReminder where id =:maxId";
            List<String> typeReminder = sessionFactory.getCurrentSession().createQuery(hql).setInteger("maxId", maxId).list();

            /*12.12.2016*/
            return typeReminder.get(0) +" / "+ countId;
        }
        else {
            return defaultValue;
        }
    }
    @Override
    @Transactional
    public  String  getCompanyReminderAmount(Integer companyId) {
        String hql = "select count(companyId) from CompanyReminder where companyId =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId);
        List<Long> value=query.list();
        if(value.get(0).toString()!="") {
            return value.get(0).toString();
        }
        return "";
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyReminders() {
        String hql = "from CompanyReminder order by companyName desc ,dateReminder desc ";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilter(Date dateFrom, Date dateTo) {
        String hql = "from CompanyReminder where dateReminder between :dateFrom and :dateTo order by dateReminder desc ";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateFrom",dateFrom).setParameter("dateTo",dateTo).list();
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilterFromDate(Date dateFrom) {
        String hql = "from CompanyReminder where dateReminder >=:dateFrom order by dateReminder desc ";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateFrom",dateFrom).list();
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilterByDate(Date date) {
        String hql = "from CompanyReminder where dateReminder =:date order by dateReminder desc ";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("date",date).list();
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilterToDate(Date dateTo) {
        String hql = "from CompanyReminder where dateReminder <=:dateTo order by dateReminder desc ";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateTo",dateTo).list();
    }
}
