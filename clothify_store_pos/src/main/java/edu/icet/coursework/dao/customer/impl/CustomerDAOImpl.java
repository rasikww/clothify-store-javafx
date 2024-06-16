package edu.icet.coursework.dao.customer.impl;

import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.hibernateUtil.HibernateCustomerUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(CustomerEntity entity) {
        boolean isSaved = false;
        Session session = HibernateCustomerUtil.getInstance().getSession();
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
        Session session = HibernateCustomerUtil.getInstance().getSession();
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
        Session session = HibernateCustomerUtil.getInstance().getSession();
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
        Session session = HibernateCustomerUtil.getInstance().getSession();
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
        Session session = HibernateCustomerUtil.getInstance().getSession();
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

}
