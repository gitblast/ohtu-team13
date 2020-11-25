package Scenes;

import Domain.Book;
import Domain.Bookmark;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ListBooksScene extends ListingScene {

    public ListBooksScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark book) {
        List<Node> nodes = new ArrayList<>();

        Label labelKirjoittaja = new Label(((Book)book).getKirjoittaja());
        Label labelNimeke = new Label(((Book)book).getNimeke());
        Label labelJulkaisuvuosi = new Label(String.valueOf(((Book)book).getJulkaisuvuosi()));
        Label labelSivumaara = new Label(String.valueOf(((Book)book).getSivumaara()));
        Label labelISBN = new Label(((Book)book).getISBN());
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
        
        Label kirjailijaOtsikko = new Label("Kirjailija");
        Label nimiOtsikko = new Label("Nimi");
        Label vuosiOtsikko = new Label("Julkaisuvuosi");
        Label sivumaaraOtsikko = new Label("Sivumäärä");
        otsikot.getChildren().addAll(kirjailijaOtsikko, nimiOtsikko, vuosiOtsikko, sivumaaraOtsikko);

        return otsikot;
    }

}