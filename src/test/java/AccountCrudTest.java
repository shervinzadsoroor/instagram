import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountCrudTest {

    @BeforeEach
    public void createAccounts() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        Account account1 = new Account(1L,"ali", "1111");
        //todo   create some accounts with their posts and ...  needed for test the methods of this class

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void signUp() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account account = Account.builder()
                .id(1L)
                .username("arian")
                .password("1111")
                .build();

        session.save(account);

        Account actualAccount = session.load(Account.class, 1L);

        assertSame(account, actualAccount);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public Account logIn(String username, String password) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account account = null;

        List<Account> list = session.createQuery("from Account where username= :username")
                .setParameter("username", username)
                .list();

        if(list.size() > 0) {
            if(list.get(0).getPassword().equals(password)) {
                account = list.get(0);
                System.out.println("sign in successful !!!");
            } else {
                System.out.println("WRONG PASSWORD !!!");
            }
        } else {
            System.out.println("account does not exist !!! ");
        }

        session.getTransaction().commit();
        session.close();
        return account;
    }
}
