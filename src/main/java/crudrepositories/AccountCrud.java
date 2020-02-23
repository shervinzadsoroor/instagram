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


    public void signUp(String username, String password) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

//        Account account1 = Account.builder()
//                .username(username)
//                .password(password)
//                .build();

        Account account = new Account(username,password);
        session.save(account);

        session.getTransaction().commit();
        session.close();
    }

    public Account logIn(String username, String password) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
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

        session.flush();
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

        session.flush();
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

    // second argument will follow or un follow the first argument
    public Account follow(Long id, Long followerId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Account followee = session.load(Account.class, id);
        Account follower = session.load(Account.class, followerId);
        followee.getFollowers().add(follower);

        Set<Account>followings = follower.getFollowings();
        followings.add(followee);
        follower.setFollowings(followings);

        session.flush();
        session.getTransaction().commit();
        session.close();
        return follower;
    }

    public Account unFollow(Long id, Long unFollowerId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        boolean isFollowerExist = false;
        Account followee = session.load(Account.class, id);
        Account follower = session.load(Account.class, unFollowerId);
        Set<Account> followers = followee.getFollowers();

        for(Account f : followers) {
            if(f.getId() == unFollowerId) {
                isFollowerExist = true;
            }
        }
        if(isFollowerExist) {
            followers.remove(follower);
        }
        followee.setFollowers(followers);

        Set<Account>followings = follower.getFollowings();
        followings.remove(followee);
        follower.setFollowings(followings);

        session.flush();
        session.getTransaction().commit();
        session.close();
        return follower;
    }

}
