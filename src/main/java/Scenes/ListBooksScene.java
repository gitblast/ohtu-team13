package Scenes;

import Domain.Book;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.List;
import javafx.scene.control.ScrollPane;

// Myöhemmin muutetaan ListScene-vanhemman perijäksi
// jotta koodi olisi vähemmän toisteista
public class ListBooksScene {

    Button returnButton;
    private VBox bookNodes;
    private List<Book> books;
    ChooseAddScene chooseAddScene;
    
    public ListBooksScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Takaisin");
    }

    public Node createBookNode(Book book) {
        HBox box = new HBox(0);
        Label labelKirjoittaja  = new Label(book.getKirjoittaja());
        Label labelNimeke  = new Label(book.getNimeke());
        Label labelJulkaisuvuosi  = 
            new Label(String.valueOf(book.getJulkaisuvuosi()));
        Label labelSivumaara  = new Label(String.valueOf(book.getSivumaara()));
        Label labelISBN  = new Label(book.getISBN());
        Label labelReleatedCourses  = new Label(book.getReleatedCourses());
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setSpacing(10);
        box.getChildren().addAll(labelKirjoittaja, labelNimeke,
            labelJulkaisuvuosi, labelSivumaara,
            labelISBN, labelReleatedCourses);

        return box;
    }

    public void redrawBooksNodes() {
        bookNodes.getChildren().clear();     
        if (books != null) {
            books.forEach(book -> {
                bookNodes.getChildren().add(createBookNode(book));
            });  
        }
    } 

    public Scene createScene(List books) {

        returnButton.setOnAction(e -> {
            try {
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        this.books = books;
        bookNodes = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        VBox listBooksVBox = new VBox();
        listBooksVBox.setPadding(new Insets(30, 20, 20, 20));
        listBooksVBox.setSpacing(5);

        scrollPane.setContent(listBooksVBox);
        HBox otsikot = new HBox();
        otsikot.setSpacing(5);
        Label kirjailijaOtsikko = new Label("Kirjailija");
        Label nimiOtsikko = new Label("Nimi");
        Label vuosiOtsikko = new Label("Julkaisuvuosi");
        Label sivumaaraOtsikko = new Label("Sivumäärä");
        otsikot.getChildren().addAll(kirjailijaOtsikko,
            nimiOtsikko, vuosiOtsikko, sivumaaraOtsikko);

        listBooksVBox.getChildren().addAll(returnButton, otsikot, bookNodes);
        
        Scene listBooksScene = new Scene(scrollPane, 600, 400);
        
        redrawBooksNodes();
        
        return listBooksScene;
    }

}