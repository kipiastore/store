package ru.store.dao.implementation;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanyReminderDAO;
import ru.store.entities.Company;
import ru.store.entities.CompanyReminder;

import java.util.ArrayList;
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
    public void deleteCompanyReminder(Integer id) {
        String hql = "delete from CompanyReminder where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }
    @Override
    @Transactional
    public List<Company> getCompaniesByComments(String type) {
        String hql = "from CompanyReminder where typeReminder =?";
        return  sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, type).list();
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
        String hql = "from CompanyReminder where companyId =? ";
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
    public  String  getLastCompanyReminderDate(Integer companyId) {
        String hql = "select MAX(id),count(id)from CompanyReminder where companyId =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId);
        List<Object[]> listResult = query.list();
        String defaultValue="";
        Integer maxId = 0;
        for (Object[] aRow : listResult) {
            maxId = (Integer) aRow[0];
        }
        if (maxId != null) {
            hql = "select dateReminder from CompanyReminder where id =:maxId";
            List<String> dateReminder = sessionFactory.getCurrentSession().createQuery(hql).setInteger("maxId", maxId).list();
            return dateReminder.get(0);
        }
        else {
            return defaultValue;
        }
    }
    @Override
    @Transactional
    public  String  getLastCompanyReminderType(Integer companyId) {
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
    public  List<String> getLastCompanyReminderDateHourType(Integer companyId) {
        String hql = "select MAX(id),count(companyId) from CompanyReminder where companyId =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId);
        List<Object[]> listResult = query.list();
        List<String>  defaultValue=new ArrayList<>();
        defaultValue.add("");
        Integer maxId = 0;
        Long countId;
        countId = Long.valueOf(0);
        for (Object[] aRow : listResult) {
            maxId = (Integer) aRow[0];
            countId = (Long) aRow[1];
        }
        if (maxId != null) {
            hql = "select dateReminder,hourReminder,typeReminder from CompanyReminder where id =:maxId";
            query = sessionFactory.getCurrentSession().createQuery(hql).setInteger("maxId", maxId);
            List<Object[]> reminderDateHourType = query.list();
            String dateReminder ="";
            String hourReminder ="";
            String typeReminder ="";
            for (Object[] aRow : reminderDateHourType) {
                dateReminder = (String) aRow[0];
                hourReminder = (String) aRow[1];
                typeReminder = (String) aRow[2];
            }
            List<String> strings=new ArrayList<>();
            strings.add(dateReminder);
            strings.add(hourReminder);
            strings.add(typeReminder);
            return  strings;
        }
        else {
            return defaultValue;
        }
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyReminders() {
        String hql = "from CompanyReminder order by companyName,id";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilter(String dateFrom,String dateTo) {
        String hql = "from CompanyReminder where dateReminder between :dateFrom and :dateTo order by dateReminder";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateFrom",dateFrom).setParameter("dateTo",dateTo).list();
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilterFromDate(String dateFrom) {
        String hql = "from CompanyReminder where dateReminder >=:dateFrom order by dateReminder";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateFrom",dateFrom).list();
    }
    @Override
    @Transactional
    public List<CompanyReminder> getCompanyRemindersByFilterToDate(String dateTo) {
        String hql = "from CompanyReminder where dateReminder <=:dateTo order by dateReminder";
        return (List<CompanyReminder>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("dateTo",dateTo).list();
    }
}
