import crudrepositories.AccountCrud;
import crudrepositories.CommentCrud;
import crudrepositories.PostCrud;
import models.Account;
import models.Comment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = null;
        AccountCrud accountCrud = new AccountCrud();
        PostCrud postCrud = new PostCrud();
        CommentCrud commentCrud = new CommentCrud();
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
                    accountCrud.signUp(userName, password);
                } else if (command.equalsIgnoreCase("login")) {
                    System.out.println("enter username: ");
                    String username = scanner.nextLine();

                    System.out.println("enter password: ");
                    String password = scanner.nextLine();
                    account = accountCrud.logIn(username, password);
                } else {
                    System.out.println("wrong command!!!");
                }
            }

            if (account != null) {
                System.out.println("what do you want? ( show followers | show followings | unFollow | change pass |\n" +
                        " delete account | new post | edit post | delete post | show posts | " +
                        "search account | top posts | log out ):");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("change pass")) {
                    accountCrud.changePass(account);

                } else if (command.equalsIgnoreCase("unFollow")) {
                    System.out.println("enter account id to un follow: ");
                    Long unFollowId = Long.parseLong(scanner.nextLine());
                    account = accountCrud.unFollow(unFollowId, account.getId());
                } else if (command.equalsIgnoreCase("show followers")) {
                    System.out.println(account.getFollowers().toString());

                } else if (command.equalsIgnoreCase("show followings")) {
                    System.out.println(account.getFollowings().toString());

                } else if (command.equalsIgnoreCase("delete account")) {
                    System.out.println("are you sure to permanently delete your account?( yes | no )");
                    command = scanner.nextLine();
                    if (command.equals("yes")) {
                        accountCrud.delete(account.getId());
                        account = null;
                        System.out.println("account deleted !!!");
                    }
                } else if (command.equalsIgnoreCase("log out")) {
                    account = null;

                } else if (command.equalsIgnoreCase("new post")) {
                    postCrud.newPost(postCrud.getNewPostContent(), account);

                } else if (command.equalsIgnoreCase("edit post")) {
                    boolean isPostExist = postCrud.showAll(account.getId());

                    if (isPostExist) {
                        System.out.println("choose an id of a post:");
                        Long postId = Long.parseLong(scanner.nextLine());
                        if (postCrud.isIdExist(postId)) {

                            System.out.println("choose item to edit:( tag | title )");
                            String editItem = scanner.nextLine();

                            if (editItem.equalsIgnoreCase("tag") || editItem.equalsIgnoreCase("title")) {

                                System.out.println("enter the new value: ");
                                String newValue = scanner.nextLine();
                                postCrud.edit(postId, editItem, newValue);
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
                    postCrud.showAll(account.getId());
                    System.out.println("choose id of a post to delete:");
                    Long postId = Long.parseLong(scanner.nextLine());
                    if (postCrud.isIdExist(postId)) {
                        postCrud.delete(postId);
                        System.out.println("POST DELETED !!!");
                    }
                } else if (command.equalsIgnoreCase("show posts")) {
                    postCrud.showAll(account.getId());

                } else if (command.equalsIgnoreCase("top posts")) {
                    System.out.println("enter max quantity of posts: ");
                    Long max = Long.parseLong(scanner.nextLine());
                    postCrud.searchTopLikedPosts(max);
                } else if (command.equalsIgnoreCase("search account")) {
                    System.out.println("enter an username to search: ");
                    String username = scanner.nextLine();
                    accountCrud.search(username);
                    System.out.println("what do you want?( follow | show posts | comment | exit )");
                    command = scanner.nextLine();
                    System.out.println("enter account id: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    if (command.equalsIgnoreCase("follow")) {
                        account = accountCrud.follow(id, account.getId());

                    } else if (command.equalsIgnoreCase("show posts")) {
                        postCrud.showAll(id);
                        System.out.println("what do you want - press 0 to exit( like | comment ): ");
                        command = scanner.nextLine();
                        System.out.println("enter post id: ");
                        Long postId = Long.parseLong(scanner.nextLine());
                        if (command.equalsIgnoreCase("like")) {
                            postCrud.like(postId, id, account.getId());
                        } else if (command.equalsIgnoreCase("comment")) {
                            System.out.println("context: ");
                            String context = scanner.nextLine();
                            commentCrud.newComment(account, context, postId);
                        }

                    } else if (command.equalsIgnoreCase("exit")) {

                    } else {
                        System.out.println("WRONG COMMAND !!!");
                    }

                } else {
                    System.out.println("wrong command !!!");
                }
            }
        }
    }

}
