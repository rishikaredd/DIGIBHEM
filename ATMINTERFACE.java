package com.package1;
import java.util.Scanner;

class Account {
    private String userId;
    private String userPin;
    private double balance;

    public Account(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transfer(Account recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
        }
    }
}

public class ATMINTERFACE {
    public static void main(String[] args) {
        // Create user account
        Account userAccount = new Account("123456", "7890", 1000.0);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.next();
            System.out.print("Enter User PIN: ");
            String userPin = scanner.next();

            if (userId.equals(userAccount.getUserId()) && userPin.equals(userAccount.getUserPin())) {
                System.out.println("Login Successful");
                break;
            } else {
                System.out.println("Login Failed. Please try again.");
            }
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Transaction History: Not Implemented");
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (userAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. New balance: " + userAccount.getBalance());
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: " + userAccount.getBalance());
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    if (recipientId.equals(userAccount.getUserId())) {
                        System.out.println("Cannot transfer to yourself.");
                    } else {
                        // In a real system, you would look up the recipient's account
                        // Here, we simulate the recipient account as the same account
                        Account recipientAccount = userAccount;
                        userAccount.transfer(recipientAccount, transferAmount);
                        System.out.println("Transfer successful. New balance: " + userAccount.getBalance());
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}