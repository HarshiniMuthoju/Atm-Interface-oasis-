import java.util.Scanner;
import java.util.ArrayList;

public class ATM {
    private User currentUser;
    private Scanner scanner;

    public ATM(User user) {
        this.currentUser = user;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. View Transaction History");
        System.out.println("6. Quit");
        System.out.print("Please choose an option: ");
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", currentUser.getBalance());
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (currentUser.withdraw(amount)) {
            System.out.printf("You have withdrawn $%.2f. Your new balance is: $%.2f%n", amount, currentUser.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.printf("You have deposited $%.2f. Your new balance is: $%.2f%n", amount, currentUser.getBalance());
    }

    private void transferMoney() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        User recipient = Bank.getUser(recipientId);
        if (recipient != null) {
            if (currentUser.transfer(recipient, amount)) {
                System.out.printf("You have transferred $%.2f to user %s. Your new balance is: $%.2f%n", amount, recipientId, currentUser.getBalance());
            } else {
                System.out.println("Transfer failed. Insufficient balance.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }

    private void viewTransactionHistory() {
        ArrayList<Transaction> history = currentUser.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : history) {
                System.out.println(transaction);
            }
        }
    }
}
