package edu.icet.coursework.dao.receipt.impl;

import edu.icet.coursework.dao.receipt.ReceiptDAO;
import edu.icet.coursework.db.DBConnection;
import edu.icet.coursework.dto.Receipt;
import edu.icet.coursework.entity.ReceiptEntity;
import edu.icet.coursework.util.HibernateUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {

    @Override
    public boolean save(Receipt dto) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();

            ReceiptEntity receiptEntity =
                    new ModelMapper().map(dto, ReceiptEntity.class);

            session.persist(receiptEntity);
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
            ReceiptEntity receiptEntity = session.get(ReceiptEntity.class, id);
            if (receiptEntity != null) {
                session.remove(receiptEntity);
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
    public ReceiptEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        ReceiptEntity receiptEntity = null;
        try {
            String sql = "SELECT * FROM receipt ORDER BY receipt_id DESC LIMIT 1";
            NativeQuery<ReceiptEntity> query = session.createNativeQuery(sql, ReceiptEntity.class);
            List<ReceiptEntity> results = query.list();

            if (!results.isEmpty()) {
                receiptEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return receiptEntity;
    }

    @Override
    public ReceiptEntity getById(Integer id) {
        ReceiptEntity receiptEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            receiptEntity = session.get(ReceiptEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return receiptEntity;
    }

    @Override
    public boolean update(Receipt newReceipt) {
        boolean isUpdated = false;
//        Session session = HibernateUtil.getInstance().getSession();
//        try {
//            session.getTransaction().begin();
//            ReceiptEntity receiptEntity = session.get(ReceiptEntity.class, newReceipt.getReceiptId());
//            receiptEntity.setSupplierEntity(session.get(SupplierEntity.class,newReceipt.getSupplierId()));
//            receiptEntity.setName(newReceipt.getName());
//            receiptEntity.setDescription(newReceipt.getDescription());
//            receiptEntity.setPrice(newReceipt.getPrice());
//            receiptEntity.setStockQuantity(newReceipt.getStockQuantity());
//            receiptEntity.setReceiptImageLink(newReceipt.getReceiptImageLink());
//            receiptEntity.setCategory(newReceipt.getCategory());
//            session.flush();
//            session.getTransaction().commit();
//            isUpdated = true;
//        } catch (Exception e) {
//            if (session.getTransaction().isActive()){
//                session.getTransaction().rollback();
//            }
//        } finally {
//            session.close();
//        }
        return isUpdated;
    }

    @Override
    public boolean viewLastReceipt() {
        boolean isViewable = false;
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/receipt.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            null,
                            DBConnection.getInstance().getConnection()
                    );

            JasperViewer.viewReport(jasperPrint);
            isViewable = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isViewable;
    }

//    @Override
//    public ObservableList<Receipt> getAll() {
//        ObservableList<Receipt> allReceipts = FXCollections.observableArrayList();
//        Session session = HibernateUtil.getInstance().getSession();
//        try {
//            String sql = "SELECT * FROM receipt WHERE deleted=0";
//            NativeQuery<ReceiptEntity> query = session.createNativeQuery(sql, ReceiptEntity.class);
//            List<ReceiptEntity> results = query.list();
//            for (ReceiptEntity receiptEntity : results) {
//                Receipt receipt = new ModelMapper().map(receiptEntity, Receipt.class);
//                receipt.setSupplierId(receiptEntity.getSupplierEntity().getSupplierId());
//                allReceipts.add(receipt);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            session.close();
//        }
//        return allReceipts;
//    }
}
