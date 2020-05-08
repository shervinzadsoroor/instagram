import DAO.LogDAO;
import DAOImpl.AccountDAOImpl;
import DAOImpl.CommentDAOImpl;
import DAOImpl.LogDAOImpl;
import DAOImpl.PostDAOImpl;
import models.Account;
import models.Log;
import models.LogType;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = null;
        AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
        PostDAOImpl postDAOImpl = new PostDAOImpl();
        CommentDAOImpl commentDAOImpl = new CommentDAOImpl();
        LogDAO logDAO = new LogDAOImpl();
        Scanner scanner = new Scanner(System.in);
        String command;

        InitialHibernate.start();


        while (true) {

            if (account == null) {
                System.out.println("what do you want? ( sign up | login ):");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("sign up")) {
                    System.out.println("enter your username: ");
                    String userName = scanner.nextLine();
                    System.out.println("enter your password: ");
                    String password = scanner.nextLine();
                    accountDAOImpl.signUp(userName, password);
                } else if (command.equalsIgnoreCase("login")) {
                    System.out.println("enter username: ");
                    String username = scanner.nextLine();

                    System.out.println("enter password: ");
                    String password = scanner.nextLine();
                    account = accountDAOImpl.logIn(username, password);
                    if (account != null) {
                        Log log = new Log();
                        log.setType(LogType.INFO.toString());
                        log.setMessage("user: " + username + " logged in at " + LocalDateTime.now());
                        logDAO.save(log);
                    }
                } else {
                    System.out.println("wrong command!!!");
                }
            }

            if (account != null) {
                System.out.println("what do you want? ( show followers | show followings | unFollow | change pass |\n" +
                        " delete account | new post | edit post | delete post | show posts | " +
                        "search account | top posts | log out | exit):");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("change pass")) {
                    accountDAOImpl.changePass(account);

                } else if (command.equalsIgnoreCase("unFollow")) {
                    System.out.println("enter account id to un follow: ");
                    Long unFollowId = Long.parseLong(scanner.nextLine());
                    account = accountDAOImpl.unFollow(unFollowId, account.getId());
                } else if (command.equalsIgnoreCase("show followers")) {
                    System.out.println(account.getFollowers().toString());

                } else if (command.equalsIgnoreCase("show followings")) {
                    System.out.println(account.getFollowings().toString());

                } else if (command.equalsIgnoreCase("delete account")) {
                    System.out.println("are you sure to permanently delete your account?( yes | no )");
                    command = scanner.nextLine();
                    if (command.equals("yes")) {
                        accountDAOImpl.delete(account.getId());
                        account = null;
                        System.out.println("account deleted !!!");
                    }
                } else if (command.equalsIgnoreCase("log out")) {
                    account = null;

                } else if (command.equalsIgnoreCase("new post")) {
                    postDAOImpl.newPost(postDAOImpl.getNewPostContent(), account);

                } else if (command.equalsIgnoreCase("edit post")) {
                    boolean isPostExist = postDAOImpl.showAll(account.getId());

                    if (isPostExist) {
                        System.out.println("choose an id of a post:");
                        Long postId = Long.parseLong(scanner.nextLine());
                        if (postDAOImpl.isIdExist(postId)) {

                            System.out.println("choose item to edit:( tag | title )");
                            String editItem = scanner.nextLine();

                            if (editItem.equalsIgnoreCase("tag") || editItem.equalsIgnoreCase("title")) {

                                System.out.println("enter the new value: ");
                                String newValue = scanner.nextLine();
                                postDAOImpl.edit(postId, editItem, newValue);
                                System.out.println("POST EDITED !!!");
                            } else {
                                System.out.println("ITEM DOES NOT EXIST !!!");
                            }
                        } else {
                            System.out.println("ID NOT FOUND !!!");
                        }
                    } else {
                        System.out.println("account does not have any posts !!!");
                    }

                } else if (command.equalsIgnoreCase("delete post")) {
                    postDAOImpl.showAll(account.getId());
                    System.out.println("choose id of a post to delete:");
                    Long postId = Long.parseLong(scanner.nextLine());
                    if (postDAOImpl.isIdExist(postId)) {
                        postDAOImpl.delete(postId);
                        System.out.println("POST DELETED !!!");
                    }
                } else if (command.equalsIgnoreCase("show posts")) {
                    postDAOImpl.showAll(account.getId());

                } else if (command.equalsIgnoreCase("top posts")) {
                    System.out.println("enter max quantity of posts: ");
                    Long max = Long.parseLong(scanner.nextLine());
                    postDAOImpl.searchTopLikedPosts(max);
                } else if (command.equalsIgnoreCase("search account")) {
                    System.out.println("enter an username to search: ");
                    String username = scanner.nextLine();
                    accountDAOImpl.search(username);
                    System.out.println("what do you want?( follow | show posts | comment | exit )");
                    command = scanner.nextLine();
                    System.out.println("enter account id: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    if (command.equalsIgnoreCase("follow")) {
                        account = accountDAOImpl.follow(id, account.getId());

                    } else if (command.equalsIgnoreCase("show posts")) {
                        postDAOImpl.showAll(id);
                        System.out.println("what do you want - press 0 to exit( like | comment ): ");
                        command = scanner.nextLine();
                        System.out.println("enter post id: ");
                        Long postId = Long.parseLong(scanner.nextLine());
                        if (command.equalsIgnoreCase("like")) {
                            postDAOImpl.like(postId, id, account.getId());
                        } else if (command.equalsIgnoreCase("comment")) {
                            System.out.println("context: ");
                            String context = scanner.nextLine();
                            commentDAOImpl.newComment(account, context, postId);
                        }

                    } else if (command.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        System.out.println("WRONG COMMAND !!!");
                    }

                } else if (command.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    System.out.println("wrong command !!!");
                }
            }
        }
    }

}
