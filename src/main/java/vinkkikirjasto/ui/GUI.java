package vinkkikirjasto.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Domain.*;
import Database.db;
import java.util.ArrayList;
import Scenes.AddBookScene;
import Scenes.AddURLScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GUI extends Application {

    ArrayList<Book> lista;
    ArrayList<Url> toinenLista;
    AddBookScene addBookScene;
    AddURLScene addURLScene;

    @Override
    public void init() throws Exception {
        String url = "jdbc:sqlite:lukuvinkit.db";
        db database = new db(url);
        database.createBook();
        database.createURL();
        lista = database.getAllBooks();
        toinenLista = database.getAllURLs();
        addBookScene = new AddBookScene();
        addURLScene = new AddURLScene();
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label label = new Label("Hello world!");
        // set id for example test
        label.setId("hello-world-label");

        root.setCenter(label);

        Button switchToAddBookScene = new Button("Lisää uusi kirja");
        Button switchToAddURLScene = new Button("Lisaa URL");

        VBox buttons = new VBox(10);
        VBox.setVgrow(switchToAddBookScene, Priority.ALWAYS);
        VBox.setVgrow(switchToAddURLScene, Priority.ALWAYS);

        switchToAddBookScene.setOnAction(e -> {
            primaryStage.setScene(addBookScene.createScene());
        });

        switchToAddURLScene.setOnAction(e -> {
            primaryStage.setScene(addURLScene.createScene());
        });

        buttons.getChildren().addAll(switchToAddBookScene, switchToAddURLScene);

        root.setLeft(buttons);

        Scene defaultScene = new Scene(root, 600, 400);

        primaryStage.setTitle("Vinkit");
        primaryStage.setScene(defaultScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
