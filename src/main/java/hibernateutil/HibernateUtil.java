package hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
   static SessionFactory sessionFactory;
   static{
       sessionFactory = new Configuration().configure().buildSessionFactory();
   }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
