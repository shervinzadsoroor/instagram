package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class PostCrud {
    private SessionFactory sessionFactory;
    private Session session;

    public void newPost(String content, Account account) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        String[] contents = content.split(",");
        Post post = new Post(contents[0], contents[1], 0, account);
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

    public boolean showAll(Long accountId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        boolean bool = false;
        Account account = session.load(Account.class, accountId);
        if (account.getPosts().size() > 0) {
            System.out.println(account.getPosts().toString());
            bool = true;
        }

        session.getTransaction().commit();
        session.close();
        return bool;
    }

    public boolean isIdExist(Long id) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        boolean bool = false;
        List<Post> posts = session.createQuery("from Post ")
                .list();
        for (Post post : posts) {
            if (post.getId() == id) {
                bool = true;
            }
        }

        session.getTransaction().commit();
        session.close();
        return bool;
    }

    public void like(Long postIdToLike, Long accountId, Long accountIdWantsToLike) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

//        Account account = session.load(Account.class, accountId);
        Account likerAccount = session.load(Account.class, accountIdWantsToLike);

        Post post = session.load(Post.class, postIdToLike);

        Set<Account> LikerAccounts = post.getLikerAccounts();
        boolean bool = LikerAccounts.add(likerAccount);
        post.setLikerAccounts(LikerAccounts);
        //todo   liker accounts does not been saved in database !!!
        if (bool) {
            post.setNumOfLiked((post.getNumOfLiked()) + 1);
        }

        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public void searchTopLikedPosts(Long postQuantity) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        List<Post> posts = session.createQuery("from Post ")
                .list();

        posts.sort(Post::compareTo);
        posts.stream()
                .limit(postQuantity)
                .forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }

}
