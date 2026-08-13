import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String username = scanner.nextLine();

        double balance = 1000.00;

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("Welcome, " + username + "!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    System.out.printf("Your current balance is: %.2f%n", balance);
                    break;
                case 2:
                    System.out.print("Enter your amount to deposit. ");
                    double deposit = scanner.nextDouble();

                    if(deposit > 0) {
                        balance += deposit;
                        System.out.println("Amount deposited successfully");
                    } else {
                        System.out.println("Invalid deposit amount! Must be possitive.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = scanner.nextDouble();

                    if(withdraw <= 0) {
                        System.out.println("Invalid withdrawal amount! Must be positive.");
                    } else if(withdraw <= balance) {
                        balance -= withdraw;
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;
                case 4:
            }

        } while (choice != 4);
        scanner.close();
    }
}