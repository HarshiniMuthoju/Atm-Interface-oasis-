import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Adding sample users to the bank for demonstration
        User user1 = new User("12345", "1234");
        user1.deposit(1000);  // Adding initial balance for testing
        bank.addUser(user1);

        User user2 = new User("56789", "5678");
        bank.addUser(user2);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        User currentUser = bank.validateUser(userId, userPin);

        if (currentUser != null) {
            ATM atm = new ATM(currentUser);
            atm.start();
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }

        scanner.close();
    }
}

