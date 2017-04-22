package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.controllers.manager.Model;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanyReminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akex on 20.04.2017.
 */
@Service
public class LoadMapsService {
    @Autowired
    private RegionService regionService;
    @Autowired
    private PackageService packageService;



    public Model load(Model model, List<CompanyReminder> companyReminders, List<CompanyAddress> companyAddresses,List<Company>companies)
    {
        List<Model.CompanyAddressItem> companyAddressList = new ArrayList<>();
        List<Model.CompanyReminderItem> companyRemindersList = new ArrayList<>();
        Model.CompanyAddressItem companyAddressItem;
        Model.CompanyReminderItem companyReminderItem;
        Map<Integer,List<CompanyReminder>> mapReminders=new HashMap<>();
        Map<Integer,List<CompanyAddress>> mapAddresses=new HashMap<>();
        for (Company company : companies) {
            System.out.println("компании"+companies);
            System.out.println("компания"+company);
            List<CompanyAddress> companyAddressesList;
            List<CompanyReminder> companyReminderList;
            if(!companyAddresses.isEmpty()) {
                for (CompanyAddress companyAddress : companyAddresses) {
                    if (company.getId() == companyAddress.getCompanyId()) {
                        if (mapAddresses.get(company.getId()) == null) {
                            companyAddressesList = new ArrayList<>();
                            companyAddressesList.add(companyAddress);
                            mapAddresses.put(company.getId(), companyAddressesList);
                        } else {
                            mapAddresses.get(company.getId()).add(companyAddress);
                        }
                    }
                }
            }
            if(!companyReminders.isEmpty()) {
                for (CompanyReminder companyReminder : companyReminders) {
                    if (company.getId() == companyReminder.getCompanyId()) {
                        if (mapReminders.get(company.getId()) == null) {
                            companyReminderList = new ArrayList<>();
                            companyReminderList.add(companyReminder);
                            mapReminders.put(company.getId(), companyReminderList);
                        } else {
                            mapReminders.get(company.getId()).add(companyReminder);
                        }
                    }
                }
            }
        }
        for(Integer companyId:mapAddresses.keySet()) {
            companyAddressItem = new Model.CompanyAddressItem();
            companyAddressItem.companyAddresses = mapAddresses.get(companyId);
            companyAddressItem.setCompanyId(companyId);
            companyAddressList.add(companyAddressItem);
        }
        for(Integer companyId:mapReminders.keySet()){
            companyReminderItem = new Model.CompanyReminderItem();
            companyReminderItem.companyReminders = mapReminders.get(companyId);
            companyReminderItem.setCompanyId(companyId);
            companyRemindersList.add(companyReminderItem);
        }

        //model.companiesJson = companies.toString();
        model.companyAddressJson = companyAddressList.toString();
        model.companyReminderJson = companyRemindersList.toString();
        model.regions = regionService.getRegions();
        model.packages = packageService.getPackages();
        model.numOfAddress = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        return model;
    }
}
