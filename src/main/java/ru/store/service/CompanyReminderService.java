package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanyReminderDAO;
import ru.store.entities.CompanyReminder;

import java.util.List;

/**
 * Created by Akex on 12.10.2016.
 */
@Service
public class CompanyReminderService {

    @Autowired
    private CompanyReminderDAO reminderDAO;

    public void createCompanyReminder(CompanyReminder reminder) {
        reminderDAO.createCompanyReminder(reminder);
    }

    public void deleteCompanyReminder(Integer id) {
        reminderDAO.deleteCompanyReminder(id);
    }

    public List<CompanyReminder> getCompanyReminders() {
        return reminderDAO.getCompanyReminders();
    }

    public List<CompanyReminder> getCompanyReminders(Integer companyId) {
        return reminderDAO.getCompanyReminders(companyId);
    }
    public String getLastCompanyReminderDate(Integer companyId){
        return reminderDAO.getLastCompanyReminderDate(companyId);
    }
    public String getLastCompanyReminderComment(Integer companyId){
        return reminderDAO.getLastCompanyReminderComment(companyId);
    }
    public String getLastCompanyReminderType(Integer companyId){
        return reminderDAO.getLastCompanyReminderType(companyId);
    }

    public CompanyReminder getCompanyReminder(Integer id) {
        return reminderDAO.getCompanyReminder(id);
    }

    public List<CompanyReminder> getCompanyRemindersByFilter(String dateFrom,String dateTo) {
        return reminderDAO.getCompanyRemindersByFilter(dateFrom,dateTo);
    }
    public List<CompanyReminder> getCompanyRemindersByFilterFromDate(String dateFrom) {
        return reminderDAO.getCompanyRemindersByFilterFromDate(dateFrom);
    }
    public List<CompanyReminder> getCompanyRemindersByFilterToDate(String dateTo) {
        return reminderDAO.getCompanyRemindersByFilterToDate(dateTo);
    }
    public List<String> getCompanyReminderDateHourType(Integer companyId) {
        return reminderDAO.getLastCompanyReminderDateHourType(companyId);
    }
}
