package bank.geld;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final int accountNumber;
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        transactionHistory.add("Account created with balance $0.0");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("You entered this amount to be deposited: $" + amount);
        } else {
            transactionHistory.add("Failed deposit attempt: $" + amount + " You need to use a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("You entered this amount to be withdrawn: $" + amount);
        } else {
            transactionHistory.add("Failed withdrawal attempt: $" + amount + " You need to use a positive value.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return String.join("\n", transactionHistory);
    }

    public String getStatement() {
        return "ID: " + accountNumber + "\nAccount Balance: $" + String.format("%.2f", balance) +
                "\nHistory of Transactions:\n" + transactionHistory.toString();
    }
}