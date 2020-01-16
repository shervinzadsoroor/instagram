package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class AccountCrud {

    public void signUp() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("enter your password: ");
        String password = scanner.nextLine();

        Account account = Account.builder()
                .username(userName)
                .password(password)
                .build();

        session.save(account);

        session.getTransaction().commit();
        session.close();
    }

    public Account signIn() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account account = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter username: ");
        String username = scanner.nextLine();

        System.out.println("enter password: ");
        String password = scanner.nextLine();

        List<Account> list = session.createQuery("from Account where username= :username")
                .setParameter("username", username)
                .list();

        if (list.size() > 0) {
            account = list.get(0);
            System.out.println("sign in successful !!!");
        } else {
            System.out.println("account does not exist !!! ");
        }

        session.getTransaction().commit();
        session.close();
        return account;
    }

    public void changePass(Account account) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter new password");
        String firstNewPass = scanner.nextLine();

        System.out.println("enter new password");
        String secondNewPass = scanner.nextLine();

        if (firstNewPass.equals(secondNewPass)) {
            Query query = session.createQuery("update Account set password=:password where username=:username")
                    .setParameter("password", firstNewPass)
                    .setParameter("username", account.getUsername());
            query.executeUpdate();
        }

        session.getTransaction().commit();
        session.close();
    }

    public void delete() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
    }

}
