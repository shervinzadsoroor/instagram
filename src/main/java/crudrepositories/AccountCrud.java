package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AccountCrud {

    SessionFactory sessionFactory;
    Session session;

    public void signUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
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

    public Account logIn() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
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

        if(list.size() > 0) {
            if(list.get(0).getPassword().equals(password)){
                account = list.get(0);
                System.out.println("sign in successful !!!");
            }else{
                System.out.println("WRONG PASSWORD !!!");
            }
        } else {
            System.out.println("account does not exist !!! ");
        }

        session.getTransaction().commit();
        session.close();
        return account;
    }

    public void changePass(Account account) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter new password");
        String firstNewPass = scanner.nextLine();

        System.out.println("enter new password");
        String secondNewPass = scanner.nextLine();

        if(firstNewPass.equals(secondNewPass)) {
            Query query = session.createQuery("update Account set password=:password where username=:username")
                    .setParameter("password", firstNewPass)
                    .setParameter("username", account.getUsername());
            query.executeUpdate();
        }

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("delete from Account where id=:id")
                .setParameter("id", id);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }


    public void search(String username) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Account> accounts = session.createQuery("from Account where username like '%" + username + "%'")
                .list();
        if(accounts.size() > 0) {
            for(Account account : accounts) {
                System.out.println(account.toString());
            }
        } else {
            System.out.println("NO RESULT !!!");
        }

        session.getTransaction().commit();
        session.close();
    }

    // account in the argument will follow or un follow another account
    public void follow(Long id, Account follower) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Account followee = session.load(Account.class, id);
        //followee.addFollower(follower);
//        follower.setOwner(followee);
        followee.getFollowers().add(follower);
        session.update(followee);

        session.getTransaction().commit();
        session.close();
    }

    public void unFollow(Long id, Account unFollower) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();


        session.getTransaction().commit();
        session.close();
    }

}
