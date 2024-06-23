package edu.icet.coursework.bo.report.impl;

import edu.icet.coursework.bo.report.ReportBO;
import edu.icet.coursework.dao.DAOFactory;
import edu.icet.coursework.dao.report.ReportDAO;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.entity.ReportEntity;
import edu.icet.coursework.util.DAOType;
import org.modelmapper.ModelMapper;

public class ReportBOImpl implements ReportBO {
    ReportDAO reportDAO = DAOFactory.getInstance().getDAO(DAOType.REPORT);

    @Override
    public boolean saveReport(Report reportDTO) {
        return reportDAO.save(reportDTO);
    }

    @Override
    public boolean deleteReportById(Integer reportId) {
        return reportDAO.deleteById(reportId);
    }

    @Override
    public Report getReport(Integer reportId) {
        try {
            ReportEntity reportEntity = reportDAO.getById(reportId);
            return new ModelMapper().map(reportEntity, Report.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateReport(Report reportDTO) {
        return reportDAO.update(reportDTO);
    }

    @Override
    public boolean generateReport(Report report) {
        //setting the strategy to select what kind of report to generate report
        reportDAO.setReportToGenerate(report.getReportType());
        return reportDAO.generateReport(report);
    }

}
