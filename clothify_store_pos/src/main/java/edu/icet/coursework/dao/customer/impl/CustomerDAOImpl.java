package edu.icet.coursework.dao.customer.impl;

import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.dto.Customer;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(CustomerEntity entity) {
        boolean isSaved = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            session.persist(entity);
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
            CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
            if (customerEntity != null) {
                session.remove(customerEntity);
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
    public CustomerEntity getLast() {
        Session session = HibernateUtil.getInstance().getSession();
        CustomerEntity customerEntity = null;
        try {
            String sql = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1";
            NativeQuery<CustomerEntity> query = session.createNativeQuery(sql, CustomerEntity.class);
            List<CustomerEntity> results = query.list();

            if (!results.isEmpty()) {
                customerEntity = results.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return customerEntity;
    }

    @Override
    public CustomerEntity getById(Integer id) {
        CustomerEntity customerEntity = null;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            customerEntity = session.get(CustomerEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
        return customerEntity;
    }

    @Override
    public boolean update(CustomerEntity newCustomerEntity) {
        boolean isUpdated = false;
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.getTransaction().begin();
            CustomerEntity customerEntity = session.get(CustomerEntity.class, newCustomerEntity.getCustomerId());
            customerEntity.setName(newCustomerEntity.getName());
            customerEntity.setEmail(newCustomerEntity.getEmail());
            customerEntity.setPhoneNumber(newCustomerEntity.getPhoneNumber());
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
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        Session session = HibernateUtil.getInstance().getSession();
        try {
            String sql = "SELECT * FROM customer WHERE deleted=0";
            NativeQuery<CustomerEntity> query = session.createNativeQuery(sql, CustomerEntity.class);
            List<CustomerEntity> results = query.list();
            for (CustomerEntity customerEntity : results) {
                allCustomers.add(new ModelMapper().map(customerEntity, Customer.class));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return allCustomers;
    }

}
