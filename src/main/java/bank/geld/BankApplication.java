/**
 * Lab 5 Bank Application with GUI using javaFX
 * @author James Rohr
 * @since 4-18-25
 */
package bank.geld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BankApplication extends Application {
    /**
     * Main launcher for the application to launch stage and scene for the GUI that is controlled by the .fxml file
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankApplication.class.getResource("bank_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Bank Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to start everything
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}