package Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseAddScene {

    AddBookScene addBookScene;
    AddURLScene addURLScene;
    Stage primaryStage;
    
    public ChooseAddScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        addBookScene = new AddBookScene(this);
        addURLScene = new AddURLScene(this);
    }

    public Scene createScene() {

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
        Scene chooseAddScene = new Scene(root, 600, 400);
        return chooseAddScene;
    }

    public void returnHere() {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }
}
