package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import Database.db;

public class AddURLScene {
    TextField otsikko;
    TextField tyyppi;
    TextField kommentti;
    TextField URL;
    TextField releatedCourses;
    Button submitButton;
    
    public AddURLScene() {
        this.otsikko = new TextField();
        this.tyyppi = new TextField();
        this.URL = new TextField();
        this.kommentti = new TextField();
        this.releatedCourses = new TextField();
        this.submitButton = new Button("Lisää uusi URL");
    }
    
    public Scene createScene() {
        BorderPane addBookPane = new BorderPane();
        VBox addBookVBox = new VBox();
        addBookVBox.setPadding(new Insets(70,20,20,20));
        
        otsikko.setPromptText("Otsikko");
        URL.setPromptText("URL");
        releatedCourses.setPromptText("Releated Courses");
        tyyppi.setPromptText("Tyyppi");
        kommentti.setPromptText("Kommentti");
        
        submitButton.setOnAction(e -> {
            lisaaURL();
        });
        
        addBookVBox.getChildren().addAll(otsikko, URL, tyyppi, releatedCourses,kommentti, submitButton);
        
        Scene addBookScene = new Scene(addBookVBox, 600, 400);
        
        return addBookScene;
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
