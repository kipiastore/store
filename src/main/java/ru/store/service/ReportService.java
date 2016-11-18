package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.ReportDAO;
import ru.store.entities.Report;

import java.util.List;

/**
 *
 */
@Service
public class ReportService {

    @Autowired
    private ReportDAO reportDAO;

    void createReport(Report report) {
        reportDAO.createReport(report);
    }

    void updateReport(Report report) {
        reportDAO.updateReport(report);
    }

    void deleteReport(Integer id) {
        reportDAO.deleteReport(id);
    }

    List<Report> getReports() {
        return reportDAO.getReports();
    }

    Report getReport(Integer id) {
        return reportDAO.getReport(id);
    }

    List<Report> getReportsByCompanyId(Integer companyId) {
        return reportDAO.getReportsByCompanyId(companyId);
    }

}
