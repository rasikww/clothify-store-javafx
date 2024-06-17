package edu.icet.coursework.util;

import edu.icet.coursework.entity.CustomerEntity;
import edu.icet.coursework.entity.OrderEntity;
import edu.icet.coursework.entity.ProductEntity;
import edu.icet.coursework.entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static HibernateUtil instance;
    private final SessionFactory sessionFactory;

    private HibernateUtil() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metaData = new MetadataSources(build)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        this.sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    public static HibernateUtil getInstance(){
        if (instance == null) {
            return instance = new HibernateUtil();
        }
        return instance;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
