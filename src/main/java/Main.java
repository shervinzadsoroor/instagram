import crudrepositories.AccountCrud;
import models.Account;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Account account = null;
        Scanner scanner = new Scanner(System.in);
        String command = null;

        InitialHibernate.start();

        while (true) {

            if (account == null) {
                System.out.println("what do you want? ( sign up | sign in ):");
                command = scanner.nextLine();
                AccountCrud accountCrud = new AccountCrud();
                if (command.equalsIgnoreCase("sign up")) {
                    accountCrud.signUp();
                } else if (command.equalsIgnoreCase("sign in")) {
                    accountCrud.signIn();
                } else {
                    System.out.println("wrong command!!!");
                }
            }

            if (account != null) {

            }
        }
    }
}
