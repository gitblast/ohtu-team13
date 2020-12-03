package Scenes;

import java.util.ArrayList;

import Domain.Url;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditURLScene extends CreateBookmarkScene {
       
    Url url;
    Button deleteButton;
    
    public EditURLScene(ChooseAddScene chooseAddScene, Url url) {
        super(chooseAddScene);
        this.url = url;
        this.deleteButton = new Button("Delete URL");
        this.title.setText("Editing URL");
        this.submitButton.setText("Submit changes");
    }
    
    @Override
    protected void setBookmarkInputFields() {

        ArrayList<TextField> list = new ArrayList<>();

        TextField otsikko = new TextField();
        TextField URL = new TextField();

        otsikko.setId("otsikko_field");
        URL.setId("URL_field");

        otsikko.setText(url.getOtsikko());
        URL.setText(url.getUrl());

        otsikko.setPromptText("Header");
        URL.setPromptText("URL");

        list.add(otsikko);
        list.add(URL);

        this.fields = list;
    }
    
    @Override
    protected Button setDeleteButton() {
        this.deleteButton.setOnAction(e -> {
            try {
                boolean poistettu = vinkkiService.deleteUrl(url.getId());
                if (poistettu) {
                    chooseAddScene.listUrlsScene();
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        });
        return this.deleteButton;
    }
    
    @Override
    protected boolean bookmarkCreation() {
        boolean inputsOK = true;

        String otsikko = this.fields.get(0).getText();
        String urlText = this.fields.get(1).getText();

        otsikko = checkString(otsikko);
        urlText = checkString(urlText);

        if (otsikko == null || urlText == null) {
            errorMessage.setText("Enter header and URL");
            inputsOK = false;
        }

        if (inputsOK) {
            url.setOtsikko(otsikko);
            url.setUrl(urlText);
            return vinkkiService.modifyUrl(url);
        }
        
        return false;
    }

    @Override
    protected int destinationIndex() {
        return 2;
    }
}
