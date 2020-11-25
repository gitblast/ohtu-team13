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

// UI Style elements

    private String cssLayoutBorder01 = "-fx-border-color: gray;\n" +
                   "-fx-border-insets: 0;\n" +
                   "-fx-border-width: 1;\n" +
                   "-fx-border-style: solid;\n";

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
        labelKirjoittaja.setStyle(cssLayoutBorder01);
        labelKirjoittaja.setMaxWidth(200);
        labelKirjoittaja.setMinWidth(200);

        Label labelNimeke  = new Label(book.getNimeke());
        labelNimeke.setStyle(cssLayoutBorder01);
        labelNimeke.setMaxWidth(200);
        labelNimeke.setMinWidth(200);

        Label labelJulkaisuvuosi  = 
            new Label(String.valueOf(book.getJulkaisuvuosi()));
        labelJulkaisuvuosi.setStyle(cssLayoutBorder01);
        labelJulkaisuvuosi.setMaxWidth(50);
        labelJulkaisuvuosi.setMinWidth(50);

        Label labelSivumaara  = new Label(String.valueOf(book.getSivumaara()));
        labelSivumaara.setStyle(cssLayoutBorder01);
        labelSivumaara.setMaxWidth(50);
        labelSivumaara.setMinWidth(50);

        Label labelISBN  = new Label(book.getISBN());
        labelISBN.setStyle(cssLayoutBorder01);
        labelISBN.setMaxWidth(50);
        labelISBN.setMinWidth(50);

        Label labelReleatedCourses  = new Label(book.getReleatedCourses());
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setSpacing(10);
        box.setPadding(new Insets(0));
        box.setSpacing(0);
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
        otsikot.setSpacing(0);
        Label kirjailijaOtsikko = new Label("Kirjailija");
        kirjailijaOtsikko.setStyle(cssLayoutBorder01);
        kirjailijaOtsikko.setMaxWidth(200);
        kirjailijaOtsikko.setMinWidth(200);

        Label nimiOtsikko = new Label("Nimi");
        nimiOtsikko.setStyle(cssLayoutBorder01);
        nimiOtsikko.setMaxWidth(200);
        nimiOtsikko.setMinWidth(200);

        Label vuosiOtsikko = new Label("Julkaisuvuosi");
        vuosiOtsikko.setStyle(cssLayoutBorder01);
        vuosiOtsikko.setMaxWidth(50);
        vuosiOtsikko.setMinWidth(50);

        Label sivumaaraOtsikko = new Label("Sivumäärä");
        sivumaaraOtsikko.setStyle(cssLayoutBorder01);
        sivumaaraOtsikko.setMaxWidth(50);
        sivumaaraOtsikko.setMinWidth(50);

        otsikot.getChildren().addAll(kirjailijaOtsikko,
            nimiOtsikko, vuosiOtsikko, sivumaaraOtsikko);

        listBooksVBox.getChildren().addAll(returnButton, otsikot, bookNodes);
        
        Scene listBooksScene = new Scene(scrollPane, 600, 400);
        
        redrawBooksNodes();
        
        return listBooksScene;
    }
    
}