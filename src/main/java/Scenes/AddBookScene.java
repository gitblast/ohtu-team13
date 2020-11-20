package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class AddBookScene {


    Button returnButton;
    TextField kirjoittaja;
    TextField otsikko;
    TextField tyyppi;
    TextField ISBN;
    TextField tagit;
    TextField releatedCourses;
    Button submitButton;
    ChooseAddScene chooseAddScene;

    public AddBookScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;
        
        this.returnButton = new Button("Takaisin");
        this.kirjoittaja = new TextField();
        this.otsikko = new TextField();
        this.tyyppi = new TextField();
        this.ISBN = new TextField();
        this.tagit = new TextField();
        this.releatedCourses = new TextField();
        this.submitButton = new Button("Lisää uusi kirja");
    }

    public Scene createScene() {
        VBox addBookVBox = new VBox();
        addBookVBox.setPadding(new Insets(70, 20, 20, 20));

        kirjoittaja.setPromptText("Kirjoittaja");
        otsikko.setPromptText("Otsikko");
        tyyppi.setPromptText("Tyyppi");
        ISBN.setPromptText("ISBN");
        tagit.setPromptText("Tagit");
        releatedCourses.setPromptText("Releated Courses");

        returnButton.setOnAction(e -> {
            chooseAddScene.returnHere();
        });

        submitButton.setOnAction(e -> {
            lisaaKirja();
        });

        addBookVBox.getChildren().addAll(returnButton, kirjoittaja, otsikko, tyyppi, ISBN, tagit, releatedCourses, submitButton);

        Scene addBookScene = new Scene(addBookVBox, 600, 400);

        return addBookScene;
    }

    public void lisaaKirja() {

    }

    public TextField getKirjoittajaText() {
        return this.kirjoittaja;
    }

    public TextField getOtsikkoText() {
        return this.otsikko;
    }

    public TextField getTyyppiText() {
        return this.tyyppi;
    }

    public TextField getISBNText() {
        return this.ISBN;
    }

    public TextField getreleatedCoursesText() {
        return this.releatedCourses;
    }

    public TextField getTagitText() {
        return this.tagit;
    }
}
