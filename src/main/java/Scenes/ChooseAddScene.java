package Scenes;

import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Service.VinkkiService;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseAddScene {

    AddBookScene addBookScene;
    AddURLScene addURLScene;
    Stage primaryStage;
    ListBooksScene listBooksScene;
    ListUrlsScene listUrlsScene;

    public ChooseAddScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        addBookScene = new AddBookScene(this);
        addURLScene = new AddURLScene(this);
        listBooksScene = new ListBooksScene(this);
        listUrlsScene = new ListUrlsScene(this);
    }

    public Scene createScene() throws Exception {
        VinkkiService vinkkiService = new VinkkiService(new SqlDbBookDao(),
                new SqlDbUrlDao());
        int kirjojenmaara = vinkkiService.listBooks().size();
        int urlienmaara = vinkkiService.listURLs().size();
        Label label = new Label("Kirjoja tietokannassa: " + kirjojenmaara
                + "\nUrleja tietokannassa: " + urlienmaara);

        label.setId("maara_label");
        // set id for example test
        Label helloworld = new Label("Hello world!");
        helloworld.setId("hello-world-label");

        Button switchToAddBookScene = new Button("Lisää kirja");
        switchToAddBookScene.setId("lisaa_kirja_btn");
        Button switchToAddURLScene = new Button("Lisää URL");
        switchToAddURLScene.setId("lisaa_url_btn");
        Button switchToListBooksScene = new Button("Tallennetut kirjat");
        switchToListBooksScene.setId("listaa_kirjat_btn");
        Button switchToListUrlsScene = new Button("Tallennetut URLit");
        switchToListUrlsScene.setId("listaa_urlit_btn");

        VBox elements = new VBox(10);
        elements.setId("chooseAdd_elements");
        elements.setPadding(new Insets(100, 0, 50, 200));
        elements.setSpacing(5);

        VBox.setVgrow(switchToAddBookScene, Priority.ALWAYS);
        VBox.setVgrow(switchToAddURLScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListBooksScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListUrlsScene, Priority.ALWAYS);

        switchToAddBookScene.setOnAction(e -> {
            primaryStage.setScene(addBookScene.createScene());
        });

        switchToAddURLScene.setOnAction(e -> {
            primaryStage.setScene(addURLScene.createScene());
        });

        switchToListBooksScene.setOnAction(e -> {
            primaryStage.setScene(listBooksScene.createScene(
                    new ArrayList<>(vinkkiService.listBooks())));
        });

        switchToListUrlsScene.setOnAction(e -> {
            primaryStage.setScene(listUrlsScene.createScene(
                    new ArrayList<>(vinkkiService.listURLs())));
        });

        elements.getChildren().addAll(label, switchToAddBookScene,
                switchToAddURLScene, switchToListBooksScene,
                switchToListUrlsScene, helloworld);

        Scene chooseAddScene = new Scene(elements, 600, 400);
        return chooseAddScene;
    }

    public void returnHere() throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }
}
