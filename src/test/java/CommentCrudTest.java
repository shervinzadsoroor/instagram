import hibernateutil.HibernateUtil;
import models.Account;
import models.Comment;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentCrudTest {

    @Test
    public void newComment() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactoryH2();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account account = new Account();
        session.save(account);
        Post post = new Post(1L, "title", "tag", 1, null, null, null);
        session.save(post);
        Post savedPost = session.load(Post.class, 1L);
        Comment comment = Comment.builder()
                .author(account)
                .context("context")
                .commentedPost(savedPost)
                .build();
        session.save(comment);

        Comment commentTest = session.load(Comment.class, 1L);
        Assertions.assertEquals(comment, commentTest);

        session.getTransaction().commit();
        session.close();
    }
}
