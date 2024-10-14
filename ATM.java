import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private int pin;

    // Constructor to initialize the ATM with a pin and starting balance
    public ATM(int pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Display the current balance to the user
    public void displayBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    // Handle withdrawal from the account, ensuring the balance doesn't go negative
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. You can't withdraw more than your balance.");
        } else {
            balance -= amount;
            transactionHistory.add("Withdraw: $" + amount);
            System.out.println("You have successfully withdrawn: $" + amount);
        }
    }

    // Deposit money into the account
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
        System.out.println("You have successfully deposited: $" + amount);
    }

    // Transfer money to another ATM account (for simplicity, another ATM object)
    public void transfer(double amount, ATM recipient) {
        if (amount > balance) {
            System.out.println("Insufficient balance to transfer.");
        } else {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred: $" + amount + " to another account");
            System.out.println("You have successfully transferred: $" + amount);
        }
    }

    // Show the transaction history
    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Start the ATM interface
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your PIN to continue: ");
        int enteredPin = scanner.nextInt();

        if (enteredPin == pin) {
            int option;

            do {
                // Display ATM options menu
                System.out.println("\n---- ATM Menu ----");
                System.out.println("1. View Balance");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit Money");
                System.out.println("4. Transfer Money");
                System.out.println("5. View Transaction History");
                System.out.println("6. Quit");
                System.out.print("Select an option (1-6): ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        displayBalance();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        System.out.println("Transferring to another account...");
                        ATM recipient = new ATM(1234, 0); // Example recipient
                        transfer(transferAmount, recipient);
                        break;
                    case 5:
                        showTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid selection, please try again.");
                }
            } while (option != 6); // Exit the loop when user chooses to quit
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }

        scanner.close(); // Close the scanner to prevent resource leak
    }

    public static void main(String[] args) {
        ATM myAtm = new ATM(1234, 500.00); // Initialize the ATM with a $500 balance
        myAtm.start(); // Start the ATM interface
    }
}
