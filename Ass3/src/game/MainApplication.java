package game;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Main Application class which starts the Block World Program
 *
 */
public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    /**
     * This is where the JavaFX windows starts and stage is passed as argument
     *
     * @param primaryStage
     *          The primary stage of the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Block World");

        View view = new View();
        new Controller(view);
        new SaveLoadHandle(view);
        primaryStage.setScene(view.getScene());
        primaryStage.show();


    }

}

