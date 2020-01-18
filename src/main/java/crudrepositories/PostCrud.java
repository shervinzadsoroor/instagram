package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class PostCrud {
    public void newPost(String content) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String[] contents = content.split(",");
        Post post = Post.builder()
                .tag(contents[0])
                .title(contents[1])
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
}
