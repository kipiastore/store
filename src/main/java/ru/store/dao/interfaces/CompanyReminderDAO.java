package ru.store.dao.interfaces;

import ru.store.entities.CompanyReminder;

import java.util.Date;
import java.util.List;

/**
 * Created by Akex on 12.10.2016.
 */
public interface CompanyReminderDAO {

    void createCompanyReminder(CompanyReminder reminder);

    void updateCompanyReminder(CompanyReminder reminder);

    CompanyReminder getCompanyReminder(Integer id);

    List<CompanyReminder> getCompanyReminders();

    List<CompanyReminder> getCompanyReminders(Integer companyId);

    List<CompanyReminder> getCompanyRemindersByFilter(Date dateFrom, Date dateTo);

    List<CompanyReminder> getCompanyRemindersByFilterByDate(Date date);

    List<CompanyReminder> getCompanyRemindersByFilterFromDate(Date dateFrom);

    List<CompanyReminder> getCompanyRemindersByFilterToDate(Date dateFrom);

    String  getLastCompanyReminderComment(Integer companyId);

    String  getLastCompanyReminderDateHourType(Integer companyId);

    String  getLastCompanyReminderTypeAndAmount(Integer companyId);

    String  getCompanyReminderAmount(Integer companyId);

}
