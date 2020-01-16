import crudrepositories.AccountCrud;
import models.Account;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = null;
        AccountCrud accountCrud = new AccountCrud();
        Scanner scanner = new Scanner(System.in);
        String command = null;

        InitialHibernate.start();

        while (true) {

            if (account == null) {
                System.out.println("what do you want? ( sign up | sign in ):");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("sign up")) {
                    accountCrud.signUp();
                } else if (command.equalsIgnoreCase("sign in")) {
                    account = accountCrud.signIn();
                } else {
                    System.out.println("wrong command!!!");
                }
            }

            if (account != null) {
                System.out.println("what do you want? ( change pass | delete account | log out ):");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("change pass")) {
                    accountCrud.changePass(account);
                } else if (command.equalsIgnoreCase("delete account")) {
                    accountCrud.delete();
                } else if (command.equalsIgnoreCase("log out")) {
                    account = null;
                } else {
                    System.out.println("wrong command !!!");
                }
            }
        }
    }
}
