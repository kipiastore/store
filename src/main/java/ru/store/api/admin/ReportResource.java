package ru.store.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.Report;
import ru.store.service.ReportService;

import java.util.List;

/**
 *
 */
@RestController
public class ReportResource {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/api/admin/resource/v1/report/company/{id}", method = RequestMethod.GET)
    public List<Report> getPackage(@PathVariable String id) {
        return reportService.getReportsByCompanyId(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/admin/resource/v1/report/erase/{id}", method = RequestMethod.GET)
    public void eraseReport(@PathVariable String id) {
        reportService.deleteReport(Integer.valueOf(id));
    }

}
