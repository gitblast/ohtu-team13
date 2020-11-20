package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddURLScene {

    Button returnButton;
    TextField otsikko;
    TextField tyyppi;
    TextField kommentti;
    TextField URL;
    TextField releatedCourses;
    Button submitButton;
    ChooseAddScene chooseAddScene;

    public AddURLScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;

        this.returnButton = new Button("Takaisin");
        this.otsikko = new TextField();
        this.tyyppi = new TextField();
        this.URL = new TextField();
        this.kommentti = new TextField();
        this.releatedCourses = new TextField();
        this.submitButton = new Button("Lisää uusi URL");
    }

    public Scene createScene() {
        VBox addURLVBox = new VBox();
        addURLVBox.setPadding(new Insets(70, 20, 20, 20));

        otsikko.setPromptText("Otsikko");
        URL.setPromptText("URL");
        releatedCourses.setPromptText("Releated Courses");
        tyyppi.setPromptText("Tyyppi");
        kommentti.setPromptText("Kommentti");

        returnButton.setOnAction(e -> {
            try {
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        submitButton.setOnAction(e -> {
            lisaaURL();
        });

        addURLVBox.getChildren().addAll(returnButton, otsikko, URL, tyyppi, releatedCourses, kommentti, submitButton);

        Scene addURLScene = new Scene(addURLVBox, 600, 400);

        return addURLScene;
    }

    public void lisaaURL() {
        System.out.println("testi");
    }

    public TextField getKommenttiText() {
        return this.kommentti;
    }

    public TextField getOtsikkoText() {
        return this.otsikko;
    }

    public TextField getTyyppiText() {
        return this.tyyppi;
    }

    public TextField getURLText() {
        return this.URL;
    }

    public TextField getreleatedCoursesText() {
        return this.releatedCourses;
    }
}
