

public class Main {
    public static void main(String[] args) {
        int balance = 5000; // change to 800 to see exception
        int withdrawalAmount = 1000;

        try {
            if(balance < withdrawalAmount) {
                throw new InsufficientBalanceException("Balance is too low for withdrawal!");
            }
            System.out.println("Withdrawal succseful!");
        } catch (InsufficientBalanceException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}