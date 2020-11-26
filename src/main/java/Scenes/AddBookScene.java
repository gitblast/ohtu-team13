package Scenes;

import Domain.Book;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class AddBookScene extends CreateBookmarkScene {

    public AddBookScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);
        this.title.setText("Adding a new Book");
        this.submitButton.setText("Add a new book");
    }

    @Override
    protected void setBookmarkInputFields() {
        ArrayList<TextField> list = new ArrayList<>();

        TextField kirjoittaja = new TextField();
        TextField nimeke = new TextField();
        TextField julkaisuvuosi = new TextField();
        TextField sivumaara = new TextField();
        TextField ISBN = new TextField();

        kirjoittaja.setPromptText("Author");
        nimeke.setPromptText("Title");
        julkaisuvuosi.setPromptText("Published");
        sivumaara.setPromptText("Page count");
        ISBN.setPromptText("ISBN");

        list.add(kirjoittaja);
        list.add(nimeke);
        list.add(julkaisuvuosi);
        list.add(sivumaara);
        list.add(ISBN);

        this.fields = list;
    }

    @Override
    protected boolean bookmarkCreation() {
        boolean inputsOK = true;

        String kirjoittaja = this.fields.get(0).getText();
        String nimeke = this.fields.get(1).getText();
        String julkaisuvuosi = this.fields.get(2).getText();
        String sivumaara = this.fields.get(3).getText();
        String ISBN = this.fields.get(4).getText();

        int jvuosi = convertToInteger(julkaisuvuosi);
        int smaara = convertToInteger(sivumaara);
        if (jvuosi == -9999 || smaara == -9999) {
            errorMessage.setText("Enter valid release year "
                    + "and number of pages");
            inputsOK = false;
        }

        kirjoittaja = checkString(kirjoittaja);
        nimeke = checkString(nimeke);
        ISBN = checkString(ISBN);
        if (kirjoittaja == null || nimeke == null || ISBN == null) {
            errorMessage.setText("Enter author, title and ISBN");
            inputsOK = false;
        }

        if (inputsOK) {
            Book kirja = new Book(kirjoittaja, nimeke, jvuosi, smaara, ISBN);
            boolean added = vinkkiService.addBook(kirja);
            return added;
        }
        return false;
    }
}
