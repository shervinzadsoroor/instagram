import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import javax.persistence.Query;
import java.io.Serializable;

public class AccountCrudTest {
    private static SessionFactory sessionFactory;
    private static Session session;
    @Mock
    private Account mockitoAccount;

    @BeforeEach
    public void openSession() {
        sessionFactory = HibernateUtil.getSessionFactoryH2();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @Test
    public void loginTest() {
        Account account = new Account("ali", "1111");
        session.persist(account);
        Query query = session.createQuery("from Account where username=:username")
                .setParameter("username", "ali");
        Account loggedInAccount = (Account) query.getSingleResult();
        Assertions.assertNotNull(loggedInAccount);
    }

    @Test
    public void changePasswordTest() {

        Account accountBeforeUpdate = new Account("ali", "1111");
        Account expected = new Account("ali", "2222");
        expected.setId(1L);
        Serializable savedAccount = session.save(accountBeforeUpdate);
//        Account account = session.load(Account.class, savedAccount);
//        Account account2 = session.get(Account.class, savedAccount);

        Query query = session.createQuery("update Account set password=:password where username=:username")
                .setParameter("password", "2222")
                .setParameter("username", "ali");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        Account actual = (Account) session.createQuery("from Account where username=:username")
                .setParameter("username", "ali").getSingleResult();
        Assertions.assertEquals(expected, actual);
    }


    @AfterEach
    public void closeSession() {
        session.getTransaction().commit();
        session.close();
    }

}
