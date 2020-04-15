package DAO;

import models.Account;

public interface CommentDAO {
    void newComment(Account author, String context, Long postId);
}
