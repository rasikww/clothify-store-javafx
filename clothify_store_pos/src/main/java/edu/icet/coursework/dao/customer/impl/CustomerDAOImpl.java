package edu.icet.coursework.dao.customer.impl;

import edu.icet.coursework.dao.customer.CustomerDAO;
import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.util.HibernateUtil;
import org.hibernate.Session;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public CustomerEntity get(CustomerEntity entity) {
        return null;
    }
}
