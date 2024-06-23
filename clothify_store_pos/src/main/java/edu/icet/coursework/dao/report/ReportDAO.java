package edu.icet.coursework.dao.report;

import edu.icet.coursework.dao.CrudDAO;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.entity.ReportEntity;
import edu.icet.coursework.util.ReportType;

public interface ReportDAO extends CrudDAO<ReportEntity, Report> {
    void setReportToGenerate(ReportType reportType);
    boolean generateReport(Report report);
}
