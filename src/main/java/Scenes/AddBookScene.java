package Scenes;

import Domain.Book;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Button;

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

        kirjoittaja.setId("kirjoittaja_field");
        nimeke.setId("nimeke_field");
        julkaisuvuosi.setId("julkaisuvuosi_field");
        sivumaara.setId("sivumaara_field");
        ISBN.setId("ISBN_field");

        kirjoittaja.setPromptText("Author");
        nimeke.setPromptText("Title");
        julkaisuvuosi.setPromptText("Published");
        sivumaara.setPromptText("Page count");
        ISBN.setPromptText("ISBN");

        list.add(nimeke);
        list.add(kirjoittaja);
        list.add(julkaisuvuosi);
        list.add(sivumaara);
        list.add(ISBN);

        this.fields = list;
    }
    
    @Override
    protected Button setDeleteButton() {
        return null;
    }

    @Override
    protected boolean bookmarkCreation() {
        boolean titleIsOk = true;
        Book book = new Book("");
        
        String kirjoittaja = this.fields.get(1).getText();
        String nimeke = this.fields.get(0).getText();
        String julkaisuvuosi = this.fields.get(2).getText();
        String sivumaara = this.fields.get(3).getText();
        String ISBN = this.fields.get(4).getText();

        int jvuosi = convertToInteger(julkaisuvuosi);
        book.setJulkaisuvuosi(jvuosi);
        
        int smaara = convertToInteger(sivumaara);
        book.setSivumaara(smaara);

        kirjoittaja = checkString(kirjoittaja);
        if (kirjoittaja == null) {
            book.setKirjoittaja("");
        } else {
            book.setKirjoittaja(kirjoittaja);
        }
        
        nimeke = checkString(nimeke);
        if (nimeke == null) {
            titleIsOk = false;
            errorMessage.setText("Enter title");
        }
        
        ISBN = checkString(ISBN);
        if (ISBN == null) {
            book.setISBN("");
        } else {
            book.setISBN(ISBN);
        }

        if (titleIsOk) {
            book.setTitle(nimeke);
            boolean added = vinkkiService.addBook(book);
            return added;
        }
        return false;
    }
}
