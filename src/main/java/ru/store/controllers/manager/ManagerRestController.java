package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.CompanyReminder;
import ru.store.service.CompanyReminderService;

/**
 * Created by Akex on 01.11.2016.
 */
@RestController
public class ManagerRestController {
    @Autowired
    private CompanyReminderService companyReminderService;
    @RequestMapping(value = "/manager/addreminder", method = RequestMethod.POST)
    public String addReminder (@RequestBody CompanyReminder companyReminder) {
        try {
            companyReminderService.createCompanyReminder(companyReminder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return   companyReminderService.getCompanyReminders(companyReminder.getCompanyId()).toString();
    }
    @RequestMapping(value = "/manager/deletereminder", method = RequestMethod.POST)
    public String deleteReminder (@RequestBody CompanyReminder companyReminder) {

        try {
            companyReminderService.deleteCompanyReminder(companyReminder.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return   companyReminderService.getCompanyReminders(companyReminder.getCompanyId()).toString();
    }
}
