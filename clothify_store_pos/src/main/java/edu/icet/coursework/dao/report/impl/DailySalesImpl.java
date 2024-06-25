package edu.icet.coursework.dao.report.impl;

import edu.icet.coursework.dao.report.ReportBehaviour;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.util.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailySalesImpl implements ReportBehaviour {
    @Override
    public boolean generate(Report report) {
        Session session = HibernateUtil.getInstance().getSession();
        boolean isGenerated = false;

        try {
            HashMap<String,Object> data= new HashMap<>();
            data.put("reportId",report.getReportId());

            String sqlTotalOrders = "SELECT COUNT(*) FROM _order WHERE DATE(order_datetime) = :selectedDate";
            String sqlTotalRevenue = "SELECT SUM(total_cost) FROM _order WHERE DATE(order_datetime) = :selectedDate";
            String sqlAvgSalesValue = "SELECT AVG(total_cost) FROM _order WHERE DATE(order_datetime) = :selectedDate";
            String sqlTotalCustomers = "SELECT COUNT(DISTINCT customer_id) FROM _order WHERE DATE(order_datetime) = :selectedDate";
            String sqlTopSellingProduct = "SELECT p.name, SUM(od.quantity) AS totalQuantitySold FROM order_detail od " +
                    "JOIN _order o ON od.order_id = o.order_id "+
                    "JOIN product p ON od.product_id = p.product_id "+
                    "WHERE DATE(o.order_datetime) = :selectedDate GROUP BY od.product_id ORDER BY totalQuantitySold DESC LIMIT 1";
            String sqlHighestSale = "SELECT MAX(total_cost) FROM _order WHERE DATE(order_datetime) = :selectedDate";
            String sqlTopSellingCategory = "SELECT p.category, SUM(od.quantity) AS totalQuantitySold FROM order_detail od " +
                    "JOIN _order o ON od.order_id = o.order_id "+
                    "JOIN product p ON od.product_id = p.product_id "+
                    "WHERE DATE(o.order_datetime) = :selectedDate GROUP BY p.category ORDER BY totalQuantitySold DESC LIMIT 1";


            Object totalOrders = session.createNativeQuery(sqlTotalOrders)
                    .setParameter("selectedDate", report.getOther())
                    .getSingleResult();
            Object totalRevenue = session.createNativeQuery(sqlTotalRevenue)
                    .setParameter("selectedDate", report.getOther())
                    .getSingleResult();
            Object avgSalesValue = session.createNativeQuery(sqlAvgSalesValue)
                    .setParameter("selectedDate", report.getOther())
                    .getSingleResult();
            Object totalCustomers = session.createNativeQuery(sqlTotalCustomers)
                    .setParameter("selectedDate", report.getOther())
                    .getSingleResult();
            List<Object[]> topSellingProduct = session.createNativeQuery(sqlTopSellingProduct)
                    .setParameter("selectedDate", report.getOther())
                    .list();
            Object highestSale = session.createNativeQuery(sqlHighestSale)
                    .setParameter("selectedDate", report.getOther())
                    .getSingleResult();
            List<Object[]> topSellingCategory = session.createNativeQuery(sqlTopSellingCategory)
                    .setParameter("selectedDate", report.getOther())
                    .list();


            data.put("totalOrders",totalOrders);
            data.put("totalRevenue",totalRevenue);
            data.put("avgSalesValue",avgSalesValue);
            data.put("numberOfCustomers",totalCustomers);

            for (Object[] row : topSellingProduct) {
                String productName = (String) row[0];
                BigDecimal totalQuantitySold = (BigDecimal) row[1];

                data.put("topSellingProduct",productName);
                data.put("totalQuantitySold",totalQuantitySold);
            }

            data.put("highestSale",highestSale);

            for (Object[] row : topSellingCategory) {
                String productCategory = (String) row[0];
                BigDecimal totalQuantitySoldByCategory = (BigDecimal) row[1];

                data.put("topSellingCategory",productCategory);
                data.put("totalQuantitySoldByCategory",totalQuantitySoldByCategory);
            }

            List<Map<String, ?>> dataList = Collections.singletonList(data);
            JRDataSource dataSource = new JRMapCollectionDataSource(dataList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("selectedDate", report.getOther());//in here, getOther() gives LocalDate
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/daily_sales.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            System.out.println("Selected Date: " + parameters.get("selectedDate"));

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            parameters,
                            dataSource//new JREmptyDataSource()//(Connection) session.doReturningWork(connection -> connection)//
                    );

            JasperViewer.viewReport(jasperPrint,false);
            isGenerated = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isGenerated;
    }
}
