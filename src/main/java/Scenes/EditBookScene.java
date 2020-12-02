package Scenes;

import Domain.Book;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class EditBookScene extends CreateBookmarkScene {
    
    Book book;
    Button deleteButton;
    
    public EditBookScene(ChooseAddScene chooseAddScene, Book book) {
        super(chooseAddScene);
        this.book = book;
        this.deleteButton = new Button("Delete book");
        this.title.setText("Editing book");
        this.submitButton.setText("Submit changes");
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

        kirjoittaja.setText(book.getKirjoittaja());
        nimeke.setText(book.getTitle());
        julkaisuvuosi.setText("" + book.getJulkaisuvuosi());
        sivumaara.setText("" + book.getSivumaara());
        ISBN.setText(book.getISBN());

        list.add(kirjoittaja);
        list.add(nimeke);
        list.add(julkaisuvuosi);
        list.add(sivumaara);
        list.add(ISBN);

        this.fields = list;
    }
    
    @Override
    protected Button setDeleteButton() {
        return this.deleteButton;
    }
    
    @Override
    protected boolean bookmarkCreation() {
        
        return false;
    }
}
