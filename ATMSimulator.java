package com.mycompany.atmsimulator;

/**
 *
 * @author Jitendar
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSimulator {
    private final Map<String, Double> accountBalances;

    public ATMSimulator() {
        accountBalances = new HashMap<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Simulator\n");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> deposit(scanner);
                case 2 -> withdraw(scanner);
                case 3 -> transfer(scanner);
                case 4 -> checkBalance(scanner);
                case 5 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        double balance = accountBalances.getOrDefault(accountNumber, 0.0);
        balance += amount;
        accountBalances.put(accountNumber, balance);

        System.out.println("Deposit successful. New balance: " + balance);
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        double balance = accountBalances.getOrDefault(accountNumber, 0.0);

        if (balance >= amount) {
            balance -= amount;
            accountBalances.put(accountNumber, balance);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter sender account number: ");
        String senderAccountNumber = scanner.next();
        System.out.print("Enter recipient account number: ");
        String recipientAccountNumber = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        double senderBalance = accountBalances.getOrDefault(senderAccountNumber, 0.0);
        double recipientBalance = accountBalances.getOrDefault(recipientAccountNumber, 0.0);

        if (senderBalance >= amount) {
            senderBalance -= amount;
            recipientBalance += amount;
            accountBalances.put(senderAccountNumber, senderBalance);
            accountBalances.put(recipientAccountNumber, recipientBalance);
            System.out.println("Transfer successful. Sender's new balance: " + senderBalance);
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }

    private void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();

        double balance = accountBalances.getOrDefault(accountNumber, 0.0);
        System.out.println("Account balance: " + balance);
    }

    public static void main(String[] args) {
        ATMSimulator atm = new ATMSimulator();
        atm.run();
    }
}