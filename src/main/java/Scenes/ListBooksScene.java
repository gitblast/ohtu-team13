package Scenes;

import Domain.Book;
import Domain.Bookmark;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ListBooksScene extends ListingScene {

// UI Style elements
    private String cssLayoutBorder01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n";

    public ListBooksScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene, new String[]{"None", "Author", "Title", "ISBN"});
    }

    private List<Bookmark> getFilteredByString(
        List<Bookmark> allBooks,
        String value,
        String filterType) {
        return allBooks.stream().filter(book -> {
            Book b = (Book) book;

            if (filterType.equals("Author")) {
                return b.getKirjoittaja() != null
                    ? b.getKirjoittaja().contains(value)
                    : false;
            }

            if (filterType.equals("ISBN")) {
                return b.getISBN() != null
                    ? b.getISBN().contains(value)
                    : false;
            }

            if (filterType.equals("Title")) {
                return b.getNimeke() != null
                    ? b.getNimeke().contains(value)
                    : false;
            }

            return false;
        })
            .collect(Collectors.toList());
    }

    @Override
    protected void setChangeListenerForFilterField(TextField tf) {
        tf.textProperty().addListener(
            (ObservableValue<? extends String> ov,
                String old_val,
                String new_val) -> {

                String selectedFilter = this.getChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                handleFilterChange(selectedFilter, new_val);

                this.redrawBookmarkNodes();
            }
        );
    }

    @Override
    protected void setChangeListenerForChoiceBox(ChoiceBox cb) {
        cb.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                handleFilterChange(this.getFilters()[new_val.intValue()],
                    this.getFilterField().getText());

                this.redrawBookmarkNodes();
            }
        );
    }

    private void handleFilterChange(
        String filterType,
        String filterFieldValue
    ) {
        this.getFilterField().setDisable(filterType.equals("None"));

        if (filterFieldValue.equals("")) {
            this.setShownBookmarks(this.getAllBookmarks());

            return;
        }

        switch (filterType) {
            case "None": {
                this.setShownBookmarks(this.getAllBookmarks());

                break;
            }
            case "Author": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue,
                        "Author")
                );

                break;
            }

            case "Title": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue,
                        "Title")
                );

                break;
            }
            case "ISBN": {

                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue,
                        "ISBN")
                );

                break;
            }

        }
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark book) {
        List<Node> nodes = new ArrayList<>();

        Label labelKirjoittaja = new Label(((Book) book).getKirjoittaja());
        labelKirjoittaja.setStyle(cssLayoutBorder01);
        labelKirjoittaja.setMaxWidth(200);
        labelKirjoittaja.setMinWidth(200);

        Label labelNimeke = new Label(((Book) book).getNimeke());
        labelNimeke.setStyle(cssLayoutBorder01);
        labelNimeke.setMaxWidth(200);
        labelNimeke.setMinWidth(200);

        String julkaisuvuosi = String.valueOf(((Book) book).getJulkaisuvuosi());
        Label labelJulkaisuvuosi = new Label(julkaisuvuosi);
        labelJulkaisuvuosi.setStyle(cssLayoutBorder01);
        labelJulkaisuvuosi.setMaxWidth(50);
        labelJulkaisuvuosi.setMinWidth(50);

        String sivumaara = String.valueOf(((Book) book).getSivumaara());
        Label labelSivumaara = new Label(sivumaara);
        labelSivumaara.setStyle(cssLayoutBorder01);
        labelSivumaara.setMaxWidth(50);
        labelSivumaara.setMinWidth(50);

        Label labelISBN = new Label(((Book) book).getISBN());
        labelISBN.setStyle(cssLayoutBorder01);
        labelISBN.setMaxWidth(50);
        labelISBN.setMinWidth(50);

        String relatedCourses = ((Book) book).getRelatedCourses();
        Label labelRelatedCourses = new Label(relatedCourses);

        nodes.add(labelKirjoittaja);
        nodes.add(labelNimeke);
        nodes.add(labelJulkaisuvuosi);
        nodes.add(labelSivumaara);
        nodes.add(labelISBN);
        nodes.add(labelRelatedCourses);

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
        vuosiOtsikko.setMaxWidth(90);
        vuosiOtsikko.setMinWidth(50);

        Label sivumaaraOtsikko = new Label("Page count");
        sivumaaraOtsikko.setStyle(cssLayoutBorder01);

        sivumaaraOtsikko.setMaxWidth(90);
        sivumaaraOtsikko.setMinWidth(50);

        Label ISBNOtsikko = new Label("ISBN");
        ISBNOtsikko.setStyle(cssLayoutBorder01);
        ISBNOtsikko.setMaxWidth(50);
        ISBNOtsikko.setMinWidth(50);

        otsikot.getChildren().addAll(kirjailijaOtsikko, nimiOtsikko,
            vuosiOtsikko, sivumaaraOtsikko, ISBNOtsikko);

        return otsikot;
    }

}
