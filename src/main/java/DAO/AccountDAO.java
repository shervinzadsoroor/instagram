package DAO;

import models.Account;

public interface AccountDAO {

    void signUp(String username, String password);

    Account logIn(String username, String password);

    void changePass(Account account);

    void delete(Long id);

    void search(String username);

    Account follow(Long id, Long followerId);

    Account unFollow(Long id, Long unFollowerId);
}
