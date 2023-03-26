import java.util.*;

class NewUser {

    Scanner scan = new Scanner(System.in);

    public void newUserDetails() {

        System.out.println("New user?\nPress 1 to register: ");
        int newu = scan.nextInt();
        while (newu == 1) {
            System.out.println("enter your user ID: ");
            String newUser = scan.next();
            // scan.nextLine();
            System.out.println(("\ngenerate your User Pin: \nUser Pin must contain 3 to 4 digits."));
            int newUserID = scan.nextInt();

            if (newUserID < 100 || newUserID > 9999) {
                System.out.println("\nUSER PIN MUST CONTATIN 3 TO 4 DIGITS.");
            } else {
                System.out.println("\nWelcome " + newUser);
                BankingDetails bank = new BankingDetails();
                bank.show();
            }
        }
        scan.close();
    }

}

class BankingDetails {
    Scanner scanner = new Scanner(System.in);

    public void show() {
        System.out.println("----------------------------------------------");
        System.out.println("We are here at your service.");
        System.out.println("A. Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Transaction History");
        System.out.println("E. Transfer");
        System.out.println("F. Exit");
        InnerBankingSystem obj = new InnerBankingSystem();
        while (true) {
            System.out.println("\nPress a key of your choice.");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case 'a':
                case 'A':
                    System.out.println("Balance");
                    System.out.println("--------------------------------------");
                    System.out.println("Balance is: " + obj.balance);
                    break;
                case 'c':
                case 'C':
                    System.out.println("Withdraw");
                    System.out.println("--------------------------------------");
                    System.out.println("enter an amount to withdraw: ");
                    int amount = scanner.nextInt();
                    obj.getWithdrawn(amount);
                    break;
                case 'b':
                case 'B':
                    System.out.println("Deposit");
                    System.out.println("--------------------------------------");
                    System.out.println("enter an amount to deposit: ");
                    int amount2 = scanner.nextInt();
                    obj.getDeposited(amount2);
                    break;
                case 'd':
                case 'D':
                    System.out.println("Transaction History");
                    System.out.println("--------------------------------------");
                    System.out.println("Previous Transaction is: ");
                    obj.transHistory();
                    break;
                case 'e':
                case 'E':
                    System.out.println("Transfer");
                    System.out.println("--------------------------------------");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("\nEnter Receipent's Name: ");
                    String receipent = sc.nextLine();
                    System.out.print("\nEnter amount to transfer:");
                    int amount3 = sc.nextInt();
                    obj.transferMoney(receipent, amount3);
                    break;
                case 'f':
                case 'F':
                    System.out.println("Thanks for using our service.");
                    System.out.println("---------------------------------------");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please enter correct key.");
                    break;
            }
        }

    }
}

class ATMInterface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to our ATM service");
        System.out.println("--------------------------------------");
        System.out.println("New user?...press1\nRegistered user?...press 2\n");
        int p = sc.nextInt();
        if (p == 2) {
            System.out.println("Enter your User ID: ");
            String customerName = sc.nextLine();
            sc.nextLine();
            System.out.println("Enter your User Pin(3 to 4 digits): ");
            int customerId = sc.nextInt();
            if (customerId < 100 || customerId > 9999) {
                System.out.println("Invalid User Pin. Please recheck Your id.");
            } else {
                BankingDetails details = new BankingDetails();
                System.out.println("Welcome " + customerName);
                details.show();
            }

        } else if (p == 1) {
            NewUser newuser = new NewUser();
            newuser.newUserDetails();
            BankingDetails details = new BankingDetails();
            details.show();
        }
        sc.close();

    }

}

/**
 * InnerBankingSystem
 */

class InnerBankingSystem {
    int balance = 0;
    String transactionHistory = "";

    void getWithdrawn(int amount) {
        if (balance - amount >= 1000) {
            if (amount != 0) {
                balance = balance - amount;
                System.out.println("Withdrawn: " + amount);
                String str = amount + " Rs Withdrawed \n";
                transactionHistory = transactionHistory.concat(str);
            } else if (amount == 0) {
                System.out.println("Withdrawal amount is zero.");
            }
        } else {

            System.out.println("You have insufficient amount balance. Can't process withdrawal operation.");
        }
    }

    void getDeposited(int amount2) {
        if (amount2 != 0) {
            balance = balance + amount2;
            System.out.println("Deposited: " + amount2);
            String str = amount2 + " Rs Deposited \n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("Deposited amount is zero.");
        }

    }

    public void transHistory() {
        if (transactionHistory == "") {
            System.out.println("\n No transactions");
        } else {
            System.out.println(transactionHistory);
        }
    }

    public void transferMoney(String receipent, int amount3) {

        if (amount3 <= 60000) {
            if (balance - amount3 >= 1000) {
                balance -= amount3;
                System.out.println("\nSuccessfully Transfered money to " + receipent);
                String str = amount3 + " Rs transferred to " + receipent + "\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance!");
            }
        } else {
            System.out.println("\nSorry...Limit is 60000.00");
        }
    }

}
