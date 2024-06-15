package edu.icet.coursework.util.hibernateUtil;

import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.entity.SupplierEntity;

public class HibernateSupplierUtil extends HibernateUtil{
    private static HibernateSupplierUtil instance;

    private HibernateSupplierUtil(Class<?> entityClass) {
        super(SupplierEntity.class);
    }
    public static HibernateSupplierUtil getInstance(){
        if (instance == null) {
            return instance = new HibernateSupplierUtil(SupplierEntity.class);
        }
        return instance;
    }
}
