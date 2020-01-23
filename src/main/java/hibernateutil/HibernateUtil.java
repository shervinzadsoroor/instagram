package hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    static SessionFactory sessionFactory;
    static SessionFactory sessionFactoryH2;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactoryH2 = new Configuration().configure("hibernate.h2.cfg.xml").buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactory getSessionFactoryH2() {
        return sessionFactoryH2;
    }
}
