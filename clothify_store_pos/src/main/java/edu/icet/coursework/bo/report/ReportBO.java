package edu.icet.coursework.bo.report;

import edu.icet.coursework.bo.SuperBO;
import edu.icet.coursework.dto.Report;

public interface ReportBO extends SuperBO {
    boolean saveReport(Report reportDTO);
    boolean deleteReportById(Integer id);
    Report getReport(Integer reportId);
    boolean updateReport(Report report);
    boolean generateReport(Report report);
}
