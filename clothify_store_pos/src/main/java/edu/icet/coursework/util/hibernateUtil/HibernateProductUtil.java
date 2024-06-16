package edu.icet.coursework.util.hibernateUtil;

import edu.icet.coursework.entity.ProductEntity;

public class HibernateProductUtil extends HibernateUtil{
    private static HibernateProductUtil instance;

    private HibernateProductUtil() {
        super(ProductEntity.class);
    }
    public static HibernateProductUtil getInstance(){
        if (instance == null) {
            return instance = new HibernateProductUtil();
        }
        return instance;
    }
}
