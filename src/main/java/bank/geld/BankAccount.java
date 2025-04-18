/**
 * Main account class for the Bank Application, stores methods to update account balance values and history.
 * @author James Rohr
 * @since 4-18-25
 */
package bank.geld;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final int accountNumber;
    private double balance;
    private final List<String> transactionHistory = new ArrayList<>();

    /**
     * Override constructor so the private variables can be used for BankAccount.
     * @param accountNumber
     *
     */
    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.00;
        transactionHistory.add("Account created with balance $0.00");
    }

    /**
     * Getter method for the account number
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Main deposit method for updating the balance of the account.  Error check added to be sure amount is not negative.
     * @param amount
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposit attempt of : $" + amount + " has been processed.");
        } else {
            transactionHistory.add("Failed deposit attempt: $" + amount + " Please use a positive value.");
        }
    }

    /**
     * Main withdraw method for updating the balance of the account.  Error check added to be sure that amount is not negative.
     * @param amount
     */
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdraw attempt of : $" + amount + " has been processed.");
        } else {
            transactionHistory.add("Failed withdrawal attempt: $" + amount + "  Please use a positive value.");
        }
    }

    /**
     * Getter method to get the account balance
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Main method to display the transaction history.
     * @return transaction history separated by newlines.
     */
    public String getTransactionHistory() {
        return String.join("\n", transactionHistory);
    }

    /**
     * Main method to get the account statement of the account.
     * Shows the account number and current balance after all
     * other bank operations were completed.
     * @return
     */
    public String getStatement() {

        // Create a new Alert of type INFORMATION for a popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bank Statement");
        alert.setHeaderText("Your Account Information is listed below:");
        alert.setContentText("Account Number: " + accountNumber + "\nAccount Balance: $" + String.format("%.2f", balance));
        alert.showAndWait();
        return "Account Number: " + accountNumber + "\nAccount Balance: $" + String.format("%.2f", balance);
    }
}