package ru.store.controllers.Quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ru.store.entities.CountingPortalPage;
import ru.store.service.CompanyService;
import ru.store.service.CountingService;
import ru.store.service.PartitionService;
import ru.store.service.SubPartitionService;

public class ScheduledJob {

    @Autowired
    private PartitionService partitionService;

    @Autowired
    private SubPartitionService subPartitionService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CountingService countingService;

    @Scheduled(fixedDelay = 86400000)
    public void demoServiceMethod() {
        CountingPortalPage countingPortalPage = countingService.getCountPortalPage();
        countingPortalPage.setCountPortalToday(0);
        countingService.addCountPortalPage(countingPortalPage);

        partitionService.refreshCountPartitionToday();
        subPartitionService.refreshCountSubPartitionToday();
        companyService.refreshCountCompanyToday();
    }
}
