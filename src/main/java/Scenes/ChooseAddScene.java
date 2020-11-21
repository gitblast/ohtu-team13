package Scenes;

import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Service.VinkkiService;
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
    ListBooksScene listBooksScene;
    
    public ChooseAddScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        addBookScene = new AddBookScene(this);
        addURLScene = new AddURLScene(this);
        listBooksScene = new ListBooksScene(this);
    }

    public Scene createScene() throws Exception {
        VinkkiService vinkkiService = new VinkkiService(new SqlDbBookDao(),
                        new SqlDbUrlDao());
        int kirjojenmaara = vinkkiService.listBooks().size();
        BorderPane root = new BorderPane();
        Label label = new Label("Kirjoja tietokannassa: " + kirjojenmaara);
        
        label.setId("kirjojenmaara_label");
        // set id for example test
        Label helloworld = new Label("Hello world!");
        helloworld.setId("hello-world-label");
        root.setCenter(label);
        root.setBottom(helloworld);

        Button switchToAddBookScene = new Button("Lisää uusi kirja");
        Button switchToAddURLScene = new Button("Lisää URL");
        Button switchToListBooksScene = new Button("Listaa kirjat");

        VBox buttons = new VBox(10);
        VBox.setVgrow(switchToAddBookScene, Priority.ALWAYS);
        VBox.setVgrow(switchToAddURLScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListBooksScene, Priority.ALWAYS);

        switchToAddBookScene.setOnAction(e -> {
            primaryStage.setScene(addBookScene.createScene());
        });

        switchToAddURLScene.setOnAction(e -> {
            primaryStage.setScene(addURLScene.createScene());
        });

        switchToListBooksScene.setOnAction(e -> {
            primaryStage.setScene(listBooksScene.createScene(
                vinkkiService.listBooks()));
        });

        buttons.getChildren().addAll(switchToAddBookScene,
            switchToAddURLScene, switchToListBooksScene);

        root.setLeft(buttons);
        Scene chooseAddScene = new Scene(root, 600, 400);
        return chooseAddScene;
    }

    public void returnHere() throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }
}
