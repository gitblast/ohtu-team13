package Scenes;

import Domain.Book;
import Domain.Bookmark;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ListBooksScene extends ListingScene {

    private EditBookScene editBookScene;
    // UI Style elements
    private String cssLayoutBorder01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: white;\n";

    private String cssLayoutBorderTitle01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: lightblue;\n";

    public ListBooksScene(ChooseAddScene chooseAddScene) {
        super(
            chooseAddScene,
            new HashMap<String, String[]>(Map.of(
                "Book", new String[]{"None", "Author", "Title", "ISBN"}
            ))
        );
    }

    protected List<Bookmark> getFilteredByString(
        List<Bookmark> allBooks,
        String value,
        String textFilterType) {
        return allBooks.stream().filter(book -> {
            Book b = (Book) book;
            System.out.println(b.getId());
            if (textFilterType.equals("Author")) {
                return b.getKirjoittaja() != null
                    ? b.getKirjoittaja().toLowerCase()
                        .contains(value.toLowerCase())
                    : false;
            }

            if (textFilterType.equals("ISBN")) {
                return b.getISBN() != null
                    ? b.getISBN().toLowerCase().contains(value.toLowerCase())
                    : false;
            }

            if (textFilterType.equals("Title")) {
                return b.getTitle() != null
                    ? b.getTitle().toLowerCase().contains(value.toLowerCase())
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
    protected void setChangeListenerForChoiceBox(ChoiceBox<String> cb) {
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

    protected void handleFilterChange(
        String textFilterType,
        String textFilterValue,
        String typeFilterValue
    ) {
        this.getFilterField().setDisable(textFilterType.equals("None"));

        if (textFilterValue.equals("")) {
            this.setShownBookmarks(this.getAllBookmarks());

            return;
        }

        switch (textFilterType) {
            case "None": {
                this.setShownBookmarks(this.getAllBookmarks());

                break;
            }
            case "Author": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        textFilterValue,
                        "Author")
                );

                break;
            }

            case "Title": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        textFilterValue,
                        "Title")
                );

                break;
            }
            case "ISBN": {

                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        textFilterValue,
                        "ISBN")
                );

                break;
            }

        }
    }

    public List<Node> getBookmarkNode(Bookmark book) {
        return createBookmarkContent(book);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark book) {
        List<Node> nodes = new ArrayList<>();

        Label labelKirjoittaja = new Label(((Book) book).getKirjoittaja());
        labelKirjoittaja.setStyle(cssLayoutBorder01);
        labelKirjoittaja.setMaxHeight(26);
        labelKirjoittaja.setMinHeight(26);
        labelKirjoittaja.setMaxWidth(200);
        labelKirjoittaja.setMinWidth(200);

        Label labelNimeke = new Label(((Book) book).getTitle());
        labelNimeke.setStyle(cssLayoutBorder01);
        labelNimeke.setMaxHeight(26);
        labelNimeke.setMinHeight(26);
        labelNimeke.setMaxWidth(200);
        labelNimeke.setMinWidth(200);

        String julkaisuvuosi = String.valueOf(((Book) book).getJulkaisuvuosi());
        Label labelJulkaisuvuosi = new Label(julkaisuvuosi);
        labelJulkaisuvuosi.setStyle(cssLayoutBorder01);
        labelJulkaisuvuosi.setMaxHeight(26);
        labelJulkaisuvuosi.setMinHeight(26);
        labelJulkaisuvuosi.setMaxWidth(60);
        labelJulkaisuvuosi.setMinWidth(60);

        String sivumaara = String.valueOf(((Book) book).getSivumaara());
        Label labelSivumaara = new Label(sivumaara);
        labelSivumaara.setStyle(cssLayoutBorder01);
        labelSivumaara.setMaxHeight(26);
        labelSivumaara.setMinHeight(26);
        labelSivumaara.setMaxWidth(50);
        labelSivumaara.setMinWidth(50);

        Label labelISBN = new Label(((Book) book).getISBN());
        labelISBN.setStyle(cssLayoutBorder01);
        labelISBN.setMaxHeight(26);
        labelISBN.setMinHeight(26);
        labelISBN.setMaxWidth(90);
        labelISBN.setMinWidth(90);

        Button editButton = new Button("Edit");
        editButtonFunction(editButton, (Book) book);

        nodes.add(labelKirjoittaja);
        nodes.add(labelNimeke);
        nodes.add(labelJulkaisuvuosi);
        nodes.add(labelSivumaara);
        nodes.add(labelISBN);
        nodes.add(editButton);

        return nodes;
    }

    private void editButtonFunction(
        Button button, Book book
    ) {
        button.setOnAction(e -> {
            try {
                editBookScene = new EditBookScene(chooseAddScene, book);
                chooseAddScene.setScene(editBookScene.createScene());
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        });
    }

    @Override
    protected HBox otsikot() {
        HBox otsikot = new HBox();
        otsikot.setSpacing(0);

        Label kirjailijaOtsikko = new Label("Author");
        kirjailijaOtsikko.setStyle(cssLayoutBorderTitle01);
        kirjailijaOtsikko.setMaxHeight(26);
        kirjailijaOtsikko.setMinHeight(26);
        kirjailijaOtsikko.setMaxWidth(200);
        kirjailijaOtsikko.setMinWidth(200);

        Label nimiOtsikko = new Label("Title");
        nimiOtsikko.setStyle(cssLayoutBorderTitle01);
        nimiOtsikko.setMaxHeight(26);
        nimiOtsikko.setMinHeight(26);
        nimiOtsikko.setMaxWidth(200);
        nimiOtsikko.setMinWidth(200);

        Label vuosiOtsikko = new Label("Published");
        vuosiOtsikko.setStyle(cssLayoutBorderTitle01);
        vuosiOtsikko.setMaxHeight(26);
        vuosiOtsikko.setMinHeight(26);
        vuosiOtsikko.setMaxWidth(60);
        vuosiOtsikko.setMinWidth(60);

        Label sivumaaraOtsikko = new Label("Page count");
        sivumaaraOtsikko.setStyle(cssLayoutBorderTitle01);
        sivumaaraOtsikko.setMaxHeight(26);
        sivumaaraOtsikko.setMinHeight(26);
        sivumaaraOtsikko.setMaxWidth(50);
        sivumaaraOtsikko.setMinWidth(50);

        Label ISBNOtsikko = new Label("ISBN");
        ISBNOtsikko.setStyle(cssLayoutBorderTitle01);
        ISBNOtsikko.setMaxHeight(26);
        ISBNOtsikko.setMinHeight(26);
        ISBNOtsikko.setMaxWidth(90);
        ISBNOtsikko.setMinWidth(90);

        otsikot.getChildren().addAll(kirjailijaOtsikko, nimiOtsikko,
            vuosiOtsikko, sivumaaraOtsikko, ISBNOtsikko);

        return otsikot;
    }

}
