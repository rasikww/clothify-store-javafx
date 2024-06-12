package edu.icet.coursework.dao.customer.impl;

import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(CustomerEntity entity) {
        try {
            Session session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public CustomerEntity getLast() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1";
        NativeQuery<CustomerEntity> query = session.createNativeQuery(sql, CustomerEntity.class);
        List<CustomerEntity> results = query.list();

        if (!results.isEmpty()) {
            CustomerEntity latestCustomer = results.get(0);
            System.out.println(latestCustomer.toString());
            return latestCustomer;
        } else {
            System.out.println("No customers found");
            return null;
        }
    }
}
