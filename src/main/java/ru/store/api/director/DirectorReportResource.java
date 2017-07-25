package ru.store.api.director;

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
public class DirectorReportResource {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/api/director/resource/v1/report/company/{id}", method = RequestMethod.GET)
    public List<Report> getPackage(@PathVariable String id) {
        return reportService.getReportsByCompanyId(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/director/resource/v1/report/erase/{id}", method = RequestMethod.GET)
    public void eraseReport(@PathVariable String id) {
        reportService.deleteReport(Integer.valueOf(id));
    }

}
