package Scenes;

import Database.SqlDbBookDao;
import Database.SqlDbMovieDao;
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
    EditBookScene editBookScene;
    AddMovieScene addMovieScene;

    Stage primaryStage;
    ListBooksScene listBooksScene;
    ListUrlsScene listUrlsScene;
    ListMoviesScene listMoviesScene;
    Label errorMsg;
    VinkkiService vinkkiService;

    public ChooseAddScene(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            vinkkiService = new VinkkiService(new SqlDbBookDao(),
                new SqlDbUrlDao(), new SqlDbMovieDao());
        } catch (Exception e) {
            errorMsg.setText("Error in database connection: " + e.getMessage());
        }

        addBookScene = new AddBookScene(this);
        addURLScene = new AddURLScene(this);
        addMovieScene = new AddMovieScene(this);
        listBooksScene = new ListBooksScene(this);
        listUrlsScene = new ListUrlsScene(this);
        editBookScene = new EditBookScene(this, null);
        listMoviesScene = new ListMoviesScene(this);

        errorMsg = new Label();

    }

    public Scene createScene() {
        Label label = new Label();
        int kirjojenmaara = vinkkiService.listBooks().size();
        int urlienmaara = vinkkiService.listURLs().size();
        int elokuvienmaara = vinkkiService.listMovies().size();
        label.setText("Books in database: " + kirjojenmaara
            + "\nUrls in database: " + urlienmaara
            + "\nMovies in database: " + elokuvienmaara);

        label.setId("maara_label");

        Button switchToAddBookScene = new Button("Add Book");
        switchToAddBookScene.setId("lisaa_kirja_btn");
        Button switchToAddURLScene = new Button("Add URL");
        switchToAddURLScene.setId("lisaa_url_btn");
        Button switchToAddMovieScene = new Button("Add Movie");
        switchToAddMovieScene.setId("lisaa_elokuva_btn");
        Button switchToListBooksScene = new Button("List all books");
        switchToListBooksScene.setId("listaa_kirjat_btn");
        Button switchToListUrlsScene = new Button("List all URLs");
        switchToListUrlsScene.setId("listaa_urlit_btn");
        Button switchToListMoviesScene = new Button("List all movies");
        switchToListMoviesScene.setId("listaa_elokuvat_btn");

        VBox elements = new VBox(10);
        elements.setId("chooseAdd_elements");
        elements.setPadding(new Insets(100, 0, 50, 200));
        elements.setSpacing(5);

        VBox.setVgrow(switchToAddBookScene, Priority.ALWAYS);
        VBox.setVgrow(switchToAddURLScene, Priority.ALWAYS);
        VBox.setVgrow(switchToAddMovieScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListBooksScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListUrlsScene, Priority.ALWAYS);
        VBox.setVgrow(switchToListMoviesScene, Priority.ALWAYS);

        switchToAddBookScene.setOnAction(e -> {
            primaryStage.setScene(addBookScene.createScene());
        });

        switchToAddURLScene.setOnAction(e -> {
            primaryStage.setScene(addURLScene.createScene());
        });

        switchToAddMovieScene.setOnAction(e -> {
            primaryStage.setScene(addMovieScene.createScene());
        });

        switchToListBooksScene.setOnAction(e -> {
            primaryStage.setScene(listBooksScene.createScene(
                new ArrayList<>(vinkkiService.listBooks())));
        });

        switchToListUrlsScene.setOnAction(e -> {
            primaryStage.setScene(listUrlsScene.createScene(
                new ArrayList<>(vinkkiService.listURLs())));
        });

        switchToListMoviesScene.setOnAction(e -> {
            primaryStage.setScene(listMoviesScene.createScene(
                new ArrayList<>(vinkkiService.listMovies())));
        });

        elements.getChildren().addAll(label, switchToAddBookScene,
            switchToAddURLScene, switchToAddMovieScene,
            switchToListBooksScene, switchToListUrlsScene,
            switchToListMoviesScene, errorMsg);

        Scene chooseAddScene = new Scene(elements, 600, 400);
        return chooseAddScene;
    }

    public void returnHere() throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    public void setScene(Scene scene) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
