import java.util.Scanner;

public class Main {

    static double balance = 1000.00;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter your name: ");
        String username = scanner.nextLine();

        int choice;

        do {
            showMenu(username);
            choice = scanner.nextInt();
            handleChoice(choice, username);
        } while (choice != 4);

        scanner.close();
    }

    public static void showMenu(String username) {
        System.out.println("\n===== BANK MENU =====");
        System.out.println("Welcome, " + username + "!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void handleChoice(int choice, String username) {

        switch (choice) {

            case 1:
                checkBalance();
                break;

            case 2:
                deposit();
                break;

            case 3:
                withdraw();
                break;

            case 4:
                System.out.println("Thank you for using the Bank App, " + username + "!");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void checkBalance() {
        System.out.printf("Your current balance is: ₹%.2f%n", balance);
    }

    public static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double deposit = scanner.nextDouble();

        if (deposit > 0) {
            balance += deposit;
            System.out.println("Amount deposited successfully!");
        } else {
            System.out.println("Invalid deposit amount! Must be positive.");
        }
    }

    public static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double withdraw = scanner.nextDouble();

        if (withdraw <= 0) {
            System.out.println("Invalid withdrawal amount! Must be positive.");
        } else if (withdraw <= balance) {
            balance -= withdraw;
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}