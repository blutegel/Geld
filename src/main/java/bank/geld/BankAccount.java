package bank.geld;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final int accountNumber;
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.00;
        transactionHistory.add("Account created with balance $0.00");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit attempt of : $" + amount + " has been processed.");
        } else {
            transactionHistory.add("Failed deposit attempt: $" + amount + " Please use a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw attempt of : $" + amount + " has been processed.");
        } else {
            transactionHistory.add("Failed withdrawal attempt: $" + amount + "  Please use a positive value.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return String.join("\n", transactionHistory);
    }

    public String getStatement() {
        return "ID: " + accountNumber + "\nAccount Balance: $" + String.format("%.2f", balance);
    }
}