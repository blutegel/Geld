module bank.geld {
    requires javafx.controls;
    requires javafx.fxml;


    opens bank.geld to javafx.fxml;
    exports bank.geld;
}