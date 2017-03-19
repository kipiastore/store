package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CompanyReminderDAO;
import ru.store.entities.CompanyReminder;
import ru.store.exceptions.NotFoundException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public void updateCompanyReminder(CompanyReminder reminder) {
        CompanyReminder oldReminder = getCompanyReminder(reminder.getId());
        if (oldReminder == null) {
            throw new NotFoundException("Напоминание не найдено.");
        }
        reminder.setCompanyId(oldReminder.getCompanyId());
        reminderDAO.updateCompanyReminder(reminder);
    }

    public List<CompanyReminder> getCompanyReminders() {
        return reminderDAO.getCompanyReminders();
    }

    public List<CompanyReminder> getCompanyReminders(Integer companyId) {
        return reminderDAO.getCompanyReminders(companyId);
    }
    public String getLastCompanyReminderDateHourType(Integer companyId){
        return reminderDAO.getLastCompanyReminderDateHourType(companyId);
    }
    public String getLastCompanyReminderComment(Integer companyId){
        return reminderDAO.getLastCompanyReminderComment(companyId);
    }
    public List<CompanyReminder> getLastCompaniesReminderType(){
        return reminderDAO.getLastCompaniesReminderType();
    }

    public String getCompanyReminderAmount(Integer companyId){
        return reminderDAO.getCompanyReminderAmount(companyId);
    }

    public List<String> getAllCompanyReminderAmount(){
        return reminderDAO.getAllCompanyReminderAmount();
    }

    public CompanyReminder getCompanyReminder(Integer id) {
        return reminderDAO.getCompanyReminder(id);
    }

    public List<CompanyReminder> getCompanyRemindersByFilter(Date dateFrom,Date dateTo) {
        return reminderDAO.getCompanyRemindersByFilter(dateFrom,dateTo);
    }
    public List<CompanyReminder> getCompanyRemindersByFilterFromDate(Date dateFrom) {
        return reminderDAO.getCompanyRemindersByFilterFromDate(dateFrom);
    }
    public List<CompanyReminder> getCompanyRemindersByFilterToDate(Date dateTo) {
        return reminderDAO.getCompanyRemindersByFilterToDate(dateTo);
    }
    //7.02.2017
    public List<CompanyReminder> getCompanyReminderByDate(Date date) { //Получаем дату для поиска и прибавляем к ней три дня(для отображения заметок тукущей даты и плюс три дня)
        Date toDate;
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 3);
        toDate=calendar.getTime();
        return reminderDAO.getCompanyRemindersByFilter(date,toDate);
    }
    //27.02.17
    public List<CompanyReminder> getCompanyReminderByDateToday(Date date) { //Получаем дату для поиска заметок на сегодня
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        Date todayDate=calendar.getTime();
        return reminderDAO.getCompanyRemindersByFilterByDate(todayDate);
    }
    //27.02.17
    public List<CompanyReminder> getCompanyReminderByDateTomorrow(Date date) { //Получаем дату для поиска заметок на завтра
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Date tomorrowDate=calendar.getTime();
        return reminderDAO.getCompanyRemindersByFilterByDate(tomorrowDate);
    }
    //27.02.17
    public List<CompanyReminder> getCompanyReminderByDateWeek(Date date) { //Получаем дату для поиска заметок на неделю
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        Date fromDate=calendar.getTime();
        calendar.add(Calendar.DATE, 7);
        Date toDate=calendar.getTime();
        return reminderDAO.getCompanyRemindersByFilter(fromDate,toDate);
    }
    //21.02.2017
    public List<CompanyReminder> getCompanyReminderByMonth(String month) {
        System.out.println("Выбраный месяц года  в числовом формате"+month);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd" );
        Date dateFrom=new Date();
        Date dateTo=new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH,Integer.valueOf(month));
            calendar.set(Calendar.DAY_OF_MONTH,1);
            dateFrom=calendar.getTime();
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            dateTo=calendar.getTime();
        }
        catch (java.lang.NullPointerException e){
            e.printStackTrace();
        }
        return reminderDAO.getCompanyRemindersByFilter(dateFrom,dateTo);
    }
}
