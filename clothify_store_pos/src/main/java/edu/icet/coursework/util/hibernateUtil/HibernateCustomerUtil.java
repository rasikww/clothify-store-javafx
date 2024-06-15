package edu.icet.coursework.util.hibernateUtil;

import edu.icet.coursework.entity.CustomerEntity;

public class HibernateCustomerUtil extends HibernateUtil{
    private static HibernateCustomerUtil instance;

    private HibernateCustomerUtil(Class<?> entityClass) {
        super(CustomerEntity.class);
    }
    public static HibernateCustomerUtil getInstance(){
        if (instance == null) {
            return instance = new HibernateCustomerUtil(CustomerEntity.class);
        }
        return instance;
    }
}
