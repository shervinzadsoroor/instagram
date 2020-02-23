import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountCrudTest {

//    @Test
//    public void signUp() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Account expectedAccount = new Account(1L, "ali", "1111", null, null, null, null);
//        session.save(expectedAccount);
//
//        Account actualAccount = session.load(Account.class, 1L);
//
//        assertEquals(expectedAccount, actualAccount);
//
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    @Test
//    public void changePass() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Account account = new Account(1L,"ali","1111",null,null,null,null);
//        session.save(account);
//
//        String username = "ali";
//        String password = "2222";
//        Query query = session.createQuery("update Account set password=:password where username=:username")
//                .setParameter("password", password)
//                .setParameter("username", username);
//        query.executeUpdate();
//
//        Account expected = new Account(1L,"ali","2222",null,null,null,null);
//        Account actual = session.load(Account.class, 1L);
//
//        assertEquals(expected, actual);
//
//        session.getTransaction().commit();
//        session.close();
//    }
}
