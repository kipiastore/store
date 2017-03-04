package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.store.entities.CompanyReminder;
import ru.store.service.CompanyReminderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Akex on 01.11.2016.
 */
@RestController
public class ManagerRestController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @Autowired
    private CompanyReminderService companyReminderService;
    @RequestMapping(value = "/manager/addreminder", method = RequestMethod.POST)
    public String addReminder(@RequestBody CompanyReminder companyReminder) {
        try {
            companyReminderService.createCompanyReminder(companyReminder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companyReminderService.getCompanyReminders(companyReminder.getCompanyId()).toString();
    }

    //5.02.2017
    @RequestMapping(value = "/manager/getreminder", method = RequestMethod.POST)
    public String getReminder(@RequestBody CompanyReminder companyReminder) {

        return companyReminderService.getCompanyReminder(companyReminder.getId()).toString();
    }
    //5.02.2017
    @RequestMapping(value = "/manager/updatereminder", method = RequestMethod.POST)
    public String updateReminder(@RequestBody CompanyReminder companyReminder) {
        try {
            companyReminderService.updateCompanyReminder(companyReminder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companyReminderService.getCompanyReminders(companyReminder.getCompanyId()).toString();
    }


}
