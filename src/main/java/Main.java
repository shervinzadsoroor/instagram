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
                System.out.println("what do you want? ( change pass | delete account | new post | edit post | delete post | log out ):");
                command = scanner.nextLine();
                if(command.equalsIgnoreCase("change pass")) {
                    accountCrud.changePass(account);
                } else if(command.equalsIgnoreCase("delete account")) {
                    System.out.println("are you sure to permanently delete your account?( yes | no )");
                    command = scanner.nextLine();
                    if(command.equals("yes")){
                        accountCrud.delete(account.getId());
                        account = null;
                        System.out.println("account deleted !!!");
                    }
                } else if(command.equalsIgnoreCase("log out")) {
                    account = null;
                } else if(command.equalsIgnoreCase("new post")) {
                       postCrud.newPost(postCrud.getNewPostContent(),account);
                } else {
                    System.out.println("wrong command !!!");
                }
            }
        }
    }
}
