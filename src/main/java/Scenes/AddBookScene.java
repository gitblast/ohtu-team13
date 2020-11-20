package Scenes;

import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Service.VinkkiService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddBookScene {

    Button returnButton;
    TextField kirjoittaja;
    TextField nimeke;
    TextField julkaisuvuosi;
    TextField sivumaara;
    TextField ISBN;
    TextField tagit;
    Label errorMessage;
    Button submitButton;
    ChooseAddScene chooseAddScene;

    public AddBookScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;

        this.returnButton = new Button("Takaisin");
        this.kirjoittaja = new TextField();
        this.nimeke = new TextField();
        this.julkaisuvuosi = new TextField();
        this.sivumaara = new TextField();
        this.ISBN = new TextField();
        this.tagit = new TextField();
        this.errorMessage = new Label();
        this.submitButton = new Button("Lisää uusi kirja");
    }

    public Scene createScene() {
        VBox addBookVBox = new VBox();
        addBookVBox.setPadding(new Insets(70, 20, 20, 20));

        kirjoittaja.setPromptText("Kirjailija");
        nimeke.setPromptText("Kirjan nimi");
        julkaisuvuosi.setPromptText("Julkaisuvuosi");
        sivumaara.setPromptText("Sivumäärä");
        ISBN.setPromptText("ISBN");
        tagit.setPromptText("Tagit");

        returnButton.setOnAction(e -> {
            try {
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        submitButton.setOnAction(e -> {
            try {
                boolean onnistuu = true;
                int jvuosi = convertToInteger(julkaisuvuosi.getText());
                int smaara = convertToInteger(sivumaara.getText());
                if (jvuosi == -9999 || smaara == -9999) {
                    errorMessage.setText("Syötä julkaisuvuosi "
                                    + "ja sivumäärä oikein");
                    onnistuu = false;
                }
                String kirjailija = checkString(kirjoittaja.getText());
                String nimi = checkString(nimeke.getText());
                if (kirjailija == null || nimi == null) {
                    errorMessage.setText("Syötä kirjailija ja kirjan nimi");
                    onnistuu = false;
                }
                if (onnistuu) {
                    lisaaKirja(kirjailija, nimi, jvuosi, smaara);
                    kirjoittaja.setText("");
                    nimeke.setText("");
                    julkaisuvuosi.setText("");
                    sivumaara.setText("");
                    errorMessage.setText("");
                    chooseAddScene.returnHere();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        addBookVBox.getChildren().addAll(returnButton, kirjoittaja, nimeke,
                        julkaisuvuosi, sivumaara, ISBN,
                        tagit, errorMessage, submitButton);

        Scene addBookScene = new Scene(addBookVBox, 600, 400);

        return addBookScene;
    }

    public void lisaaKirja(String kirjoittaja, String nimeke,
                    int julkaisuvuosi, int sivumaara) throws Exception {
        VinkkiService vinkkiService = new VinkkiService(new SqlDbBookDao(),
                        new SqlDbUrlDao());
        vinkkiService.addBook(kirjoittaja, nimeke, julkaisuvuosi, sivumaara);
    }

    private int convertToInteger(String s) {
        s = s.trim();
        int value;
        if (s.length() == 0) {
            value = -9999;
        } else {
            try{
                value = Integer.valueOf(s);
            } catch(NumberFormatException e) {
                value = -9999;
            }
        }
        return value;
    }

    private String checkString(String s) {
        s.trim();
        if (s.isEmpty()) {
            return null;
        }
        return s;
    }

    public TextField getKirjoittajaText() {
        return this.kirjoittaja;
    }

    public TextField getOtsikkoText() {
        return this.nimeke;
    }

    public TextField getjulkaisuvuosiText() {
        return this.julkaisuvuosi;
    }

    public TextField getISBNText() {
        return this.ISBN;
    }

    public TextField getTagitText() {
        return this.tagit;
    }
}
