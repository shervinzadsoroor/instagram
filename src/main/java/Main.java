import crudrepositories.AccountCrud;
import crudrepositories.PostCrud;
import models.Account;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = null;
        AccountCrud accountCrud = new AccountCrud();
        PostCrud postCrud = new PostCrud();
        Scanner scanner = new Scanner(System.in);
        String command = null;

        InitialHibernate.start();

        while(true) {

            if(account == null) {
                System.out.println("what do you want? ( sign up | login ):");
                command = scanner.nextLine();

                if(command.equalsIgnoreCase("sign up")) {
                    accountCrud.signUp();
                } else if(command.equalsIgnoreCase("login")) {
                    account = accountCrud.logIn();
                } else {
                    System.out.println("wrong command!!!");
                }
            }

            if(account != null) {
                System.out.println("what do you want? ( change pass | delete account | new post | edit post | delete post | show posts | log out ):");
                command = scanner.nextLine();
                if(command.equalsIgnoreCase("change pass")) {
                    accountCrud.changePass(account);
                } else if(command.equalsIgnoreCase("delete account")) {
                    System.out.println("are you sure to permanently delete your account?( yes | no )");
                    command = scanner.nextLine();
                    if(command.equals("yes")) {
                        accountCrud.delete(account.getId());
                        account = null;
                        System.out.println("account deleted !!!");
                    }
                } else if(command.equalsIgnoreCase("log out")) {
                    account = null;
                } else if(command.equalsIgnoreCase("new post")) {
                    postCrud.newPost(postCrud.getNewPostContent(), account);
                } else if(command.equalsIgnoreCase("edit post")) {
                    postCrud.showAll(account.getId());

                    System.out.println("choose an id of a post:");
                    Long postId = Long.parseLong(scanner.nextLine());
                    if(postCrud.isIdExist(postId)) {

                        System.out.println("choose item to edit:( tag | title )");
                        String editItem = scanner.nextLine();

                        if(editItem.equalsIgnoreCase("tag") || editItem.equalsIgnoreCase("title")) {

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
                } else if(command.equalsIgnoreCase("delete post")) {
                    postCrud.showAll(account.getId());
                    System.out.println("choose id of a post to delete:");
                    Long postId = Long.parseLong(scanner.nextLine());
                    if(postCrud.isIdExist(postId)) {
                        postCrud.delete(postId);
                        System.out.println("POST DELETED !!!");
                    }
                } else if(command.equalsIgnoreCase("show posts")) {
                    postCrud.showAll(account.getId());
                } else {
                    System.out.println("wrong command !!!");
                }
            }
        }
    }

}
