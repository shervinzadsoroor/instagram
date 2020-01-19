package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class PostCrud {
    private SessionFactory sessionFactory;
    private Session session;

    public void newPost(String content, Account account) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        String[] contents = content.split(",");
        Post post = Post.builder()
                .tag(contents[0])
                .title(contents[1])
                .account(account)
                .build();
        session.save(post);

        session.getTransaction().commit();
        session.close();
    }

    public String getNewPostContent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter tag: ");
        String tag = scanner.nextLine();
        System.out.println("enter title: ");
        String title = scanner.nextLine();
        return tag + "," + title;
    }

    public void edit(Long postId, String editItem, String newValue) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("update Post set " + editItem + " =:value where id=:id")
                .setParameter("value", newValue)
                .setParameter("id", postId);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Long postId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("delete from Post where id=:id")
                .setParameter("id", postId);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void showAll(Long accountId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Account account = session.load(Account.class, accountId);
        System.out.println(account.getPosts().size() > 0 ? account.getPosts().toString() :
                "account does not have any posts !!!");

        session.getTransaction().commit();
        session.close();
    }

    public boolean isIdExist(Long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        boolean bool = false;
        List<Post> posts = session.createQuery("from Post ")
                .list();
        for(Post post : posts) {
            if(post.getId() == id) {
                bool = true;
            }
        }

        session.getTransaction().commit();
        session.close();
        return bool;
    }
}
