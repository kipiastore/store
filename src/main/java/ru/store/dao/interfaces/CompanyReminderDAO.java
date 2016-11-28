package ru.store.dao.interfaces;

import ru.store.entities.Company;
import ru.store.entities.CompanyReminder;

import java.util.List;

/**
 * Created by Akex on 12.10.2016.
 */
public interface CompanyReminderDAO {

    void createCompanyReminder(CompanyReminder reminder);

    void deleteCompanyReminder(Integer id);

    CompanyReminder getCompanyReminder(Integer id);


    List<Company> getCompaniesByComments(String type);

    List<CompanyReminder> getCompanyReminders();

    List<CompanyReminder> getCompanyReminders(Integer companyId);

    List<CompanyReminder> getCompanyRemindersByFilter(String dateFrom, String dateTo);

    List<CompanyReminder> getCompanyRemindersByFilterFromDate(String dateFrom);

    List<CompanyReminder> getCompanyRemindersByFilterToDate(String dateFrom);

    String  getLastCompanyReminderComment(Integer companyId);

    String  getLastCompanyReminderDate(Integer companyId);

    String  getLastCompanyReminderType(Integer companyId);

    List<String> getLastCompanyReminderDateHourType(Integer companyId);
}
