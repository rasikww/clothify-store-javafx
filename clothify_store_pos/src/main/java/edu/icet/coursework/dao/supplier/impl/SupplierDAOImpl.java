package edu.icet.coursework.dao.supplier.impl;

import edu.icet.coursework.dao.supplier.SupplierDAO;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.entity.SupplierEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(SupplierEntity entity) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            isSaved = true;
            System.out.println("in try: "+ entity);
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            System.out.println("in catch");

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
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
            if (supplierEntity != null) {
                session.remove(supplierEntity);
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
    public SupplierEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        SupplierEntity supplierEntity = null;
        try {
            String sql = "SELECT * FROM supplier ORDER BY supplier_id DESC LIMIT 1";
            NativeQuery<SupplierEntity> query = session.createNativeQuery(sql, SupplierEntity.class);
            List<SupplierEntity> results = query.list();

            if (!results.isEmpty()) {
                supplierEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            session.close();
        }
        return supplierEntity;
    }

    @Override
    public SupplierEntity getById(Integer id) {
        SupplierEntity supplierEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            supplierEntity = session.get(SupplierEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return supplierEntity;
    }

    @Override
    public boolean update(SupplierEntity newSupplierEntity) {
        boolean isUpdated = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, newSupplierEntity.getSupplierId());
            supplierEntity.setName(newSupplierEntity.getName());
            supplierEntity.setCompany(newSupplierEntity.getCompany());
            supplierEntity.setEmail(newSupplierEntity.getEmail());
            supplierEntity.setPhoneNumber(newSupplierEntity.getPhoneNumber());
            session.flush();
            session.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return isUpdated;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<Supplier> allSuppliers = FXCollections.observableArrayList();
        Session session = HibernateUtil.getInstance().getSession();
        try {
            String sql = "SELECT * FROM supplier WHERE deleted=0";
            NativeQuery<SupplierEntity> query = session.createNativeQuery(sql, SupplierEntity.class);
            List<SupplierEntity> results = query.list();
            for (SupplierEntity supplierEntity : results) {
                allSuppliers.add(new ModelMapper().map(supplierEntity, Supplier.class));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return allSuppliers;
    }
}
