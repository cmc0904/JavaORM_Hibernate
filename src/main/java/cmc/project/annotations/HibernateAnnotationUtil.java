package cmc.project.annotations;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateAnnotationUtil {
    static SessionFactory sessionFactory;
    static ServiceRegistry serviceRegistry;

    static{
        try{
            Configuration configuration = new Configuration().configure("hibernate-annotation.cfg.xml");

            //	예제1
            configuration.addAnnotatedClass(Weather.class);


            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch(HibernateException e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
