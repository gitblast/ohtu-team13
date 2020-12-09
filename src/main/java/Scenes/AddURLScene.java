package Scenes;

import Domain.Url;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class AddURLScene extends CreateBookmarkScene {

    public AddURLScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);      
        this.title.setText("Adding a new URL");  
        this.submitButton.setText("Add a new URL");
    }

    @Override
    protected void setBookmarkInputFields() {
        ArrayList<TextField> list = new ArrayList<>();

        TextField otsikko = new TextField();
        TextField URL = new TextField();

        otsikko.setId("otsikko_field");
        URL.setId("URL_field");
        
        otsikko.setPromptText("Header");
        URL.setPromptText("URL");

        list.add(otsikko);
        list.add(URL);

        this.fields = list;
    }

    @Override
    protected boolean bookmarkCreation() {
        boolean inputsOK = true;

        String otsikko = this.fields.get(0).getText();
        String url = this.fields.get(1).getText();

        otsikko = checkString(otsikko);
        url = checkString(url);

        if (otsikko == null || url == null) {
            errorMessage.setText("Enter header and URL");
            inputsOK = false;
        }
        
        if (inputsOK) {
            Url newURL = new Url(otsikko, url);
            boolean added = vinkkiService.addURL(newURL);
            return added;
        }
        return false;
    }
    
    @Override
    protected Button setDeleteButton() {
        return null;
    }
}
