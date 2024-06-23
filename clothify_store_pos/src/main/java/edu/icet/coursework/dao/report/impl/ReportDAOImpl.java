package edu.icet.coursework.dao.report.impl;

import edu.icet.coursework.dao.report.ReportBehaviour;
import edu.icet.coursework.dao.report.ReportDAO;
import edu.icet.coursework.dto.Report;
import edu.icet.coursework.entity.ReportEntity;
import edu.icet.coursework.entity.UserEntity;
import edu.icet.coursework.util.HibernateUtil;
import edu.icet.coursework.util.ReportType;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    ReportBehaviour reportToGenerate;

    @Override
    public boolean save(Report dto) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            ReportEntity reportEntity =
                    new ModelMapper().map(dto, ReportEntity.class);

            session.persist(reportEntity);
            session.getTransaction().commit();
            isSaved = true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }

        }finally {
            session.close();
        }
        return isSaved;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean isDeleted = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            ReportEntity reportEntity = session.get(ReportEntity.class, id);
            if (reportEntity != null) {
                session.remove(reportEntity);
                session.getTransaction().commit();
                isDeleted = true;
            }
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public ReportEntity getLast() {
        return null;
    }

    @Override
    public ReportEntity getById(Integer id) {
        ReportEntity reportEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            reportEntity = session.get(ReportEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return reportEntity;
    }

    @Override
    public boolean update(Report dto) {
        return false;
    }

    @Override
    public boolean generateReport(Report report) {
        boolean isSaved = false;
        boolean isGenerated = false;
//adding the report entry to the db;
        Session session = HibernateUtil.getInstance().getSession();

        ReportEntity reportEntity =
                new ModelMapper().map(report, ReportEntity.class);
        try {
            session.getTransaction().begin();

            reportEntity.setUserEntity(
                    session.get(
                            UserEntity.class,
                            report.getUser().getUserId()));
            session.persist(reportEntity);

            session.getTransaction().commit();
            isSaved = true;

        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }

        }finally {
            session.close();
        }
//report entity is added and hibernate session is closed

        if (isSaved){
            isGenerated = reportToGenerate.generate(report);
            if (!isGenerated){
                session.getTransaction().begin();
                ReportEntity reportEntityLast = null;
                try {
                    String sql = "SELECT report_id FROM report ORDER BY report_id DESC LIMIT 1";
                    NativeQuery<ReportEntity> query = session.createNativeQuery(sql, ReportEntity.class);
                    List<ReportEntity> results = query.list();

                    if (!results.isEmpty()) {
                        reportEntityLast = results.get(0);
                        String sqlDelete = "DELETE FROM report WHERE report_id = :reportId";
                        session.createQuery(sqlDelete)
                                .setParameter("reportId",reportEntityLast.getReportId())
                                .executeUpdate();
                        String reportIdStepDown = "SELECT setval('report_id_generator', currval('report_id_generator') - 1)";
                        session.createNativeQuery(sql).getSingleResult();
                        session.getTransaction().commit();
                    }

                } catch (Exception e) {
                    if (session.getTransaction().isActive()){
                        session.getTransaction().rollback();
                    }
                }finally {
                    session.close();
                }

            }
            return isGenerated;
        }
        return isSaved;
        //return reportToGenerate.generate(report);
    }


    public void setReportToGenerate(ReportType reportType){
        switch (reportType){
            case PRODUCT_CATALOG: this.reportToGenerate = new ProductCatalogImpl();
            break;
            case QTY_SOLD_PER_PRODUCT: this.reportToGenerate = new QtyPerProductSoldImpl();
            break;
            case ALL_USERS_REPORT: this.reportToGenerate = new AllUsersImpl();
            break;
            case ALL_SUPPLIERS_REPORT: this.reportToGenerate = new AllSuppliersImpl();
            break;
        }
    }
}
