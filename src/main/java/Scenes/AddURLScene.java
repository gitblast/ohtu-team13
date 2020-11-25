package Scenes;

import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Domain.Url;
import Service.VinkkiService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// Muutetaan AddScene-vanhemman perijäksi myöhemmin
// vähentämään toisteisuutta scenejen välillä
//
// Joitakin attribuutteja kommentoitu pois,
// koska niitä ei voi vielä lisätä tietokantaan
public class AddURLScene {

    Button returnButton;
    TextField otsikko;
    //TextField tyyppi;
    //TextField kommentti;
    TextField URL;
    //TextField releatedCourses;
    Label errorMessage;
    Button submitButton;
    ChooseAddScene chooseAddScene;
    VinkkiService vinkkiService;

    public AddURLScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;

        this.returnButton = new Button("Back");
        this.otsikko = new TextField();
        //this.tyyppi = new TextField();
        this.URL = new TextField();
        //this.kommentti = new TextField();
        //this.releatedCourses = new TextField();
        this.errorMessage = new Label();
        this.submitButton = new Button("Add new URL");

        try {
            this.vinkkiService = new VinkkiService(
                    new SqlDbBookDao(), new SqlDbUrlDao()
            );
        } catch (Exception e) {
            this.vinkkiService = null;

            this.errorMessage.setText(
                    "Error in database connection: " + e.getMessage()
            );
        }
    }

    public Scene createScene() {
        VBox addURLVBox = new VBox();
        addURLVBox.setPadding(new Insets(80, 50, 50, 100));
        addURLVBox.setSpacing(5);

        otsikko.setPromptText("Header");
        URL.setPromptText("URL");
        //releatedCourses.setPromptText("Releated Courses");
        //tyyppi.setPromptText("Tyyppi");
        //kommentti.setPromptText("Kommentti");

        returnButton.setOnAction(e -> {
            try {
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        submitButton.setOnAction(e -> {
            try {
                String otsikkoText = checkString(otsikko.getText());
                String urlText = checkString(URL.getText());
                Url uusiURL = new Url(otsikkoText, urlText);
                if (vinkkiService.addURL(uusiURL)) {
                    otsikko.setText("");
                    URL.setText("");
                    errorMessage.setText("");
                    chooseAddScene.returnHere();
                } else {
                    errorMessage.setText("Problem adding URL to database");
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        addURLVBox.getChildren().addAll(returnButton, otsikko, URL,
                errorMessage, submitButton);

        Scene addURLScene = new Scene(addURLVBox, 600, 400);

        return addURLScene;
    }

    private String checkString(String s) {
        s.trim();
        if (s.isEmpty()) {
            return null;
        }
        return s;
    }

    public TextField getOtsikkoText() {
        return this.otsikko;
    }

    public TextField getURLText() {
        return this.URL;
    }
}
