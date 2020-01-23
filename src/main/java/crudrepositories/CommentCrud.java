package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import models.Comment;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CommentCrud {
    SessionFactory sessionFactory;
    Session session;

    public void newComment(Account author, String context, Long postId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Post post = session.load(Post.class, postId);
        Comment comment = Comment.builder()
                .author(author)
                .context(context)
                .commentedPost(post)
                .build();
        session.save(comment);

        session.getTransaction().commit();
        session.close();
    }

    public void likeComment(){

        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();



        session.getTransaction().commit();
        session.close();
    }
}
