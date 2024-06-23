package edu.icet.coursework.controller.report;

import edu.icet.coursework.bo.BOFactory;
import edu.icet.coursework.bo.report.ReportBO;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.util.BOType;

public class ReportController {
    private static ReportController instance;
    private ReportController(){}
    public static ReportController getInstance(){
        if (instance == null) {
            return instance = new ReportController();
        }
        return instance;
    }
    //--------------------------------------------

    ReportBO reportBO = BOFactory.getInstance().getBO(BOType.REPORT);

//    public String generateNextReportId() {
//        ReportDAO reportDAO = DAOFactory.getInstance().getDAO(DAOType.RECEIPT);
//        ReportEntity lastReport = reportDAO.getLast();
//        if (lastReport == null) {
//            return "1";
//        }else return String.valueOf((lastReport.getReportId()+1));
//    }

    public boolean addReport(Report newReport) {
        return reportBO.saveReport(newReport);
    }

    public Report getReport(String reportId) {
        return reportBO.getReport(Integer.valueOf(reportId));
    }

    public boolean removeReport(String reportId) {
        return reportBO.deleteReportById(Integer.valueOf(reportId));
    }

    public boolean updateReport(Report report) {
        return reportBO.updateReport(report);
    }

    public boolean generateReport(Report report) {
        return reportBO.generateReport(report);
    }
}
