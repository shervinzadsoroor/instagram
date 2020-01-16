import hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InitialHibernate {
    public static void start() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("welcome to instagram!");

        session.getTransaction().commit();
        session.close();
    }
}
