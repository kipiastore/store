package ru.store.controllers.Quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ru.store.entities.Company;
import ru.store.entities.CountingPortalPage;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.service.CompanyService;
import ru.store.service.CountingService;
import ru.store.service.PartitionService;
import ru.store.service.SubPartitionService;

import java.util.List;

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
    public void demoServiceMethod()
    {
        CountingPortalPage countingPortalPage=countingService.getCountPortalPage();
        countingPortalPage.setCountPortalToday(0);
        countingService.addCountPortalPage(countingPortalPage);
        List<Partition> partition=partitionService.getPartitions();
        List<SubPartition> subPartition=subPartitionService.getSubPartitions();
        List<Company> companies=companyService.getCompanies();
        for(Partition p:partition){
            p.setCountPartitionToday(0);
            partitionService.updatePartition(p);
        }
        for(SubPartition sb:subPartition){
            sb.setCountSubPartitionToday(0);
            subPartitionService.updateSubPartition(sb);
        }
        for(Company c:companies){
            c.setCountCompanyToday(0);
            companyService.updateCompany(c);
        }
    }
}
