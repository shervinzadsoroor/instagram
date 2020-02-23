package crudrepositories;

import hibernateutil.HibernateUtil;
import models.Account;
import models.Comment;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.management.Query;

public class CommentCrud {
    SessionFactory sessionFactory;
    Session session;

    public void newComment(Account author, String context, Long postId) {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Post post = session.load(Post.class, postId);
//        Comment comment1 = Comment.builder()
//                .author(author)
//                .context(context)
//                .commentedPost(post)
//                .build();

        Comment comment = new Comment(author,context,0,post);
        session.save(comment);

        session.getTransaction().commit();
        session.close();
    }

}
