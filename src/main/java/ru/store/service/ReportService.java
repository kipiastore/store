package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.FileDAO;
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
    @Autowired
    private FileDAO fileDAO;

    public void createReport(Report report) {
        reportDAO.createReport(report);
    }

    public void updateReport(Report report) {
        reportDAO.updateReport(report);
    }

    public void deleteReport(Integer id) {
        fileDAO.deleteFile(reportDAO.getReport(id).getFileId());
        reportDAO.deleteReport(id);
    }

    public List<Report> getReports() {
        return reportDAO.getReports();
    }

    public Report getReport(Integer id) {
        return reportDAO.getReport(id);
    }

    public List<Report> getReportsByCompanyId(Integer companyId) {
        return reportDAO.getReportsByCompanyId(companyId);
    }

}
