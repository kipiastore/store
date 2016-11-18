package ru.store.dao.interfaces;

import ru.store.entities.Report;

import java.util.List;

/**
 *
 */
public interface ReportDAO {

    void createReport(Report report);

    void updateReport(Report report);

    void deleteReport(Integer id);

    List<Report> getReports();

    Report getReport(Integer id);

    List<Report> getReportsByCompanyId(Integer companyId);

}
