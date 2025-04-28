/**
 * Description Main controller for adding GUI elements and the methods they use for the Bank Application
 * @author James Rohr
 * @since 4-18-25
 */
package bank.geld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the controller for the GUI items and methods
 *
 */
public class BankController {
    @FXML private TextField accountInput;
    @FXML private TextField amountInput;
    @FXML private Button createAccountBtn;
    @FXML private Button depositBtn;
    @FXML private Button withdrawBtn;
    @FXML private Button showHistoryBtn;
    @FXML private Button getStatementBtn;
    @FXML private TextArea outputArea;
    @FXML public ComboBox<BankAccount> comboBox;
    @FXML private Button showAccountBtn;

    /**
     * Main array list for the keeping of all transaction items
     * and the initial start for bank account numbers and list for drop down menu.
     */
    private final List<BankAccount> accounts = new ArrayList<>();
    private ObservableList<BankAccount> observableAccounts = FXCollections.observableArrayList();
    private int nextAccountNumber = 1001;

    /**
     * Screen display (TextArea) for the methods being used in the application.
     * Added ComboBox for displaying account numbers, also needed a cell factory and a listener to
     * update accountInput with the selected account from the comboBox.
     */
    @FXML
    private void initialize() {
        comboBox.setItems(observableAccounts);

        // Set up a cell factory to display BankAccount objects as strings
        comboBox.setCellFactory(param -> new ListCell<BankAccount>() {
            @Override
            protected void updateItem(BankAccount item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("Account: " + item.getAccountNumber());
                }
            }
        });

        // Set up a converter for the combobox selected item
        comboBox.setConverter(new StringConverter<BankAccount>() {
            @Override
            public String toString(BankAccount account) {
                if (account == null) {
                    return null;
                }
                return "Account: " + account.getAccountNumber();
            }
            @Override
            public BankAccount fromString(String string) {
                // Needed for this converter to run
                return null;
            }
        }
        );
        // Add a listener to update the accountInput text field when an account is selected
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                accountInput.setText(String.valueOf(newValue.getAccountNumber()));
            }
        }
        );
        //Limit the output Screen to be only populated by methods not the end user
        outputArea.setEditable(false);
    }

    /**
     * Method to handle the button action to create a new account,
     * gets account number variable and adds one to make a new account,
     * i.e. 1001 if no other account is present. Displays in TextArea.
     */
    @FXML
    private void handleCreateAccount() {
        BankAccount account = new BankAccount(nextAccountNumber++);
        accounts.add(account);
        observableAccounts.add(account);
        outputArea.appendText("Created account: " + account.getAccountNumber() + "\n");
    }

    /**
     * Method to update the ComboBox with the current list of accounts
     */
    @FXML
    private void handleShowAccountList() {
        observableAccounts.clear();
        observableAccounts.addAll(accounts);
        comboBox.setItems(observableAccounts);
        outputArea.appendText("Account list has been updated.\n");
    }

    /**
     * Method to handle the deposit button action to deposit the
     * amount listed in the amount field and update the transaction history array list. Does error check to be sure amount is positive
     */
    @FXML
    private void handleDeposit() {
        try {
            int accNum = Integer.parseInt(accountInput.getText());
            double amount = Double.parseDouble(amountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                acc.deposit(amount);
                outputArea.appendText("You entered this amount to be deposited: $" + amount + " to this account " + accNum + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("Field cannot be blank. Please enter a value to deposit.\n");
        }
    }

    /**
     * Method to handle the withdraw button actions. Making sure that
     * there is a valid account number to allow the action to work.
     */
    @FXML
    private void handleWithdraw() {
        try {
            int accNum = Integer.parseInt(accountInput.getText());
            double amount = Double.parseDouble(amountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                acc.withdraw(amount);
                outputArea.appendText("You entered this amount to be withdrawn: $" + amount + " from this account " + accNum + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("Field cannot be blank. Please enter a value to withdraw.\n");
        }
    }

    /**
     * Method to handle the show history button action. Pulling text from input field
     * to then run through the account number check to allow the action to proceed.
     */
    @FXML
    private void handleShowHistory() {
        try {
            int accNum = Integer.parseInt(accountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                outputArea.appendText("Transaction history for account " + accNum + ":\n" + acc.getTransactionHistory() + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("This field cannot be blank. Please enter a value for the account number.\n");
        }
    }

    /**
     * Method to handle the get statement button action. Pulling text from the input box
     * and checking the value to be sure the account is present and created. Then the action is allowed to proceed.
     */
    @FXML
    private void handleGetStatement() {
        try {
            int accNum = Integer.parseInt(accountInput.getText());
            BankAccount acc = findAccountById(accNum);
            if (acc != null) {
                outputArea.appendText("Account Statement:\n" + acc.getStatement() + "\n");
            } else {
                outputArea.appendText("Account " + accNum + " not found.\n");
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("This field cannot be blank. Please enter a value for the account number.\n");
        }
    }

    /**
     * Method to look through the array list for the account number
     * to be sure its present and already created.
     * @param accountId
     * @return the account ID
     */
    private BankAccount findAccountById(int accountId) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountId) {
                return account;
            }
        }
        return null;
    }
}