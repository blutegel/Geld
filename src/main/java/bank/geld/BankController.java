package bank.geld;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

public class BankController {
    @FXML private TextField accountInput;
    @FXML private TextField amountInput;
    @FXML private Button createAccountBtn;
    @FXML private Button depositBtn;
    @FXML private Button withdrawBtn;
    @FXML private Button showHistoryBtn;
    @FXML private Button getStatementBtn;
    @FXML private TextArea outputArea;

    private final List<BankAccount> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    @FXML
    private void initialize() {
        outputArea.setEditable(false);
    }

    @FXML
    private void handleCreateAccount() {
        BankAccount account = new BankAccount(nextAccountNumber++);
        accounts.add(account);
        outputArea.appendText("Created account: " + account.getAccountNumber() + "\n");
    }

    @FXML
    private void handleDeposit() {
            int accNum = Integer.parseInt(accountInput.getText());
            double amount = Double.parseDouble(amountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                acc.deposit(amount);
                outputArea.appendText("You entered this amount to be deposited: $" + amount + " to this account " + accNum + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
    }

    @FXML
    private void handleWithdraw() {
            int accNum = Integer.parseInt(accountInput.getText());
            double amount = Double.parseDouble(amountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                acc.withdraw(amount);
                outputArea.appendText("You entered this amount to be withdrawn: $" + amount + " from this account " + accNum + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
    }

    @FXML
    private void handleShowHistory() {
            int accNum = Integer.parseInt(accountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                outputArea.appendText("Transaction history for account " + accNum + ":\n" + acc.getTransactionHistory() + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
    }

    @FXML
    private void handleGetStatement() {
            int accNum = Integer.parseInt(accountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                outputArea.appendText("Account Statement:\n" + acc.getStatement() + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
    }

    private BankAccount findAccountById(int accountId) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountId) {
                return account;
            }
        }
        return null;
    }
}