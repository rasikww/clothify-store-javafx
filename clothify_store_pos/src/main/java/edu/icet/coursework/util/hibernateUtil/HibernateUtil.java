package edu.icet.coursework.util.hibernateUtil;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HibernateUtil {
    private final SessionFactory sessionFactory;
    @Getter
    private final Class<?> entityClass;

    protected HibernateUtil(Class<?> entityClass) {
        this.entityClass = entityClass;
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metaData = new MetadataSources(build)
                .addAnnotatedClass(entityClass)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        this.sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
