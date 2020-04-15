package DAO;

import models.Account;

public interface PostDAO {
    void newPost(String content, Account account);

    String getNewPostContent();

    void edit(Long postId, String editItem, String newValue);

    void delete(Long postId);

    boolean showAll(Long accountId);

    boolean isIdExist(Long id);

    void like(Long postIdToLike, Long accountId, Long accountIdWantsToLike);

    void searchTopLikedPosts(Long postQuantity);
}
