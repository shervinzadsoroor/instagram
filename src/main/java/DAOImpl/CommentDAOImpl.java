package DAOImpl;

import DAO.CommentDAO;
import hibernateutil.HibernateUtil;
import models.Account;
import models.Comment;
import models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CommentDAOImpl implements CommentDAO {
    SessionFactory sessionFactory;
    Session session;

    @Override
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
