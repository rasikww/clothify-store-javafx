package edu.icet.coursework.dao.report.impl;

import edu.icet.coursework.dao.report.ReportBehaviour;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.util.HibernateUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.sql.Connection;

public class QtyPerProductSoldImpl implements ReportBehaviour {
    @Override
    public boolean generate(Report report) {
        Session session = HibernateUtil.getInstance().getSession();
        boolean isGenerated = false;
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/qty_sold_per_product.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            null,
                            (Connection) session.doReturningWork(connection -> connection)
                    );

            JasperViewer.viewReport(jasperPrint,false);
            isGenerated = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isGenerated;
    }
}
