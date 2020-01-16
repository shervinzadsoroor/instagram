package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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



        session.getTransaction().commit();
        session.close();
        return null;
    }
}
