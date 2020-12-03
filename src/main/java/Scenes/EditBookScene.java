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
        this.deleteButton.setOnAction(e -> {
            try {
                boolean poistettu = vinkkiService.deleteBook(book.getId());
                if (poistettu) {
                    chooseAddScene.listBooksScene();
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
            book.setKirjoittaja(kirjoittaja);
            book.setTitle(nimeke);
            book.setJulkaisuvuosi(jvuosi);
            book.setSivumaara(smaara);
            book.setISBN(ISBN);
            return vinkkiService.modifyBook(book);
        }
        
        return false;
    }

    @Override
    protected int destinationIndex() {
        return 1;
    }
}
