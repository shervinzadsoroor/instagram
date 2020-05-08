package DAOImpl;

import DAO.LogDAO;
import hibernateutil.HibernateUtil;
import models.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LogDAOImpl implements LogDAO {
    private static SessionFactory sessionFactory;
    private static Session session;

    @Override
    public void save(Log log) {
        beginTransaction();
        session.save(log);
        commitTransaction();
    }

    @Override
    public Log findById(Long id) {
        beginTransaction();
        Log log = (Log)session.createQuery("from Log where id=:id")
                .setParameter("id", id)
                .getSingleResult();
        commitTransaction();
        return log;
    }

    @Override
    public List<Log> findAll() {
        beginTransaction();
        List<Log> logs = session.createQuery("from Log").list();
        commitTransaction();
        return logs;
    }

    private static void beginTransaction() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    private static void commitTransaction() {
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        try {
            LogDAO logDAO = new LogDAOImpl();
            System.out.println(logDAO.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
