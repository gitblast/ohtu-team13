package Scenes;

import Domain.Book;
import Domain.Bookmark;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ListBooksScene extends ListingScene {

// UI Style elements
    private String cssLayoutBorder01 = "-fx-border-color: gray;\n"
            + "-fx-border-insets: 0;\n"
            + "-fx-border-width: 1;\n"
            + "-fx-border-style: solid;\n";

    public ListBooksScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark book) {
        List<Node> nodes = new ArrayList<>();

        Label labelKirjoittaja = new Label(((Book)book).getKirjoittaja());
        labelKirjoittaja.setStyle(cssLayoutBorder01);
        labelKirjoittaja.setMaxWidth(200);
        labelKirjoittaja.setMinWidth(200);

        Label labelNimeke = new Label(((Book)book).getNimeke());
        labelNimeke.setStyle(cssLayoutBorder01);
        labelNimeke.setMaxWidth(200);
        labelNimeke.setMinWidth(200);

        Label labelJulkaisuvuosi = new Label(String.valueOf(((Book)book).getJulkaisuvuosi()));
        labelJulkaisuvuosi.setStyle(cssLayoutBorder01);
        labelJulkaisuvuosi.setMaxWidth(50);
        labelJulkaisuvuosi.setMinWidth(50);

        Label labelSivumaara = new Label(String.valueOf(((Book)book).getSivumaara()));
        labelSivumaara.setStyle(cssLayoutBorder01);
        labelSivumaara.setMaxWidth(50);
        labelSivumaara.setMinWidth(50);

        Label labelISBN = new Label(((Book)book).getISBN());
        labelISBN.setStyle(cssLayoutBorder01);
        labelISBN.setMaxWidth(50);
        labelISBN.setMinWidth(50);

        Label labelReleatedCourses = new Label(((Book)book).getRelatedCourses());

        nodes.add(labelKirjoittaja);
        nodes.add(labelNimeke);
        nodes.add(labelJulkaisuvuosi);
        nodes.add(labelSivumaara);
        nodes.add(labelISBN);
        nodes.add(labelReleatedCourses);

        return nodes;
    }

    @Override
    protected HBox otsikot() {
        HBox otsikot = new HBox();
        otsikot.setSpacing(5);
        
        Label kirjailijaOtsikko = new Label("Author");
        kirjailijaOtsikko.setStyle(cssLayoutBorder01);
        kirjailijaOtsikko.setMaxWidth(200);
        kirjailijaOtsikko.setMinWidth(200);

        Label nimiOtsikko = new Label("Title");
        nimiOtsikko.setStyle(cssLayoutBorder01);
        nimiOtsikko.setMaxWidth(200);
        nimiOtsikko.setMinWidth(200);

        Label vuosiOtsikko = new Label("Published");
        vuosiOtsikko.setStyle(cssLayoutBorder01);
        vuosiOtsikko.setMaxWidth(50);
        vuosiOtsikko.setMinWidth(50);

        Label sivumaaraOtsikko = new Label("Page count");
        sivumaaraOtsikko.setStyle(cssLayoutBorder01);
        sivumaaraOtsikko.setMaxWidth(50);
        sivumaaraOtsikko.setMinWidth(50);     
           
        otsikot.getChildren().addAll(kirjailijaOtsikko, nimiOtsikko, vuosiOtsikko, sivumaaraOtsikko);

        return otsikot;
    }

}
