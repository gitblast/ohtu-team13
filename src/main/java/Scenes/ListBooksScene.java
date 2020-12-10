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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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
            //System.out.println(b.getId());
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

        Label labelNimeke = new Label(((Book) book).getTitle());
        labelNimeke.setStyle(cssLayoutBorder01);
        labelNimeke.setMaxHeight(26);
        labelNimeke.setMinHeight(26);
        labelNimeke.setMaxWidth(200);
        labelNimeke.setMinWidth(200);

        Label labelKirjoittaja = new Label(((Book) book).getKirjoittaja());
        labelKirjoittaja.setStyle(cssLayoutBorder01);
        labelKirjoittaja.setMaxHeight(26);
        labelKirjoittaja.setMinHeight(26);
        labelKirjoittaja.setMaxWidth(200);
        labelKirjoittaja.setMinWidth(200);

        int jvuosi = ((Book) book).getJulkaisuvuosi();
        String julkaisuvuosi = String.valueOf(jvuosi);
        if (julkaisuvuosi.equals("-9999")) {
            julkaisuvuosi = "";
        }
        Label labelJulkaisuvuosi = new Label(julkaisuvuosi);
        labelJulkaisuvuosi.setStyle(cssLayoutBorder01);
        labelJulkaisuvuosi.setMaxHeight(26);
        labelJulkaisuvuosi.setMinHeight(26);
        labelJulkaisuvuosi.setMaxWidth(40);
        labelJulkaisuvuosi.setMinWidth(40);

        int sivut = ((Book) book).getSivumaara();
        String sivumaara = String.valueOf(sivut);
        if (sivumaara.equals("-9999")) {
            sivumaara = "";
        }
        Label labelSivumaara = new Label(sivumaara);
        labelSivumaara.setStyle(cssLayoutBorder01);
        labelSivumaara.setMaxHeight(26);
        labelSivumaara.setMinHeight(26);
        labelSivumaara.setMaxWidth(40);
        labelSivumaara.setMinWidth(40);

        Label labelISBN = new Label(((Book) book).getISBN());
        labelISBN.setStyle(cssLayoutBorder01);
        labelISBN.setMaxHeight(26);
        labelISBN.setMinHeight(26);
        labelISBN.setMaxWidth(50);
        labelISBN.setMinWidth(50);

        Button copyISBN = new Button("Copy");

        copyISBN.setOnAction(e -> {
            String copyText = labelISBN.getText();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(copyText);
            clipboard.setContent(content);
            super.info.setText("ISBN copied to clipboard!");
        });

        Button editButton = new Button("Edit");
        editButtonFunction(editButton, (Book) book);

        nodes.add(labelNimeke);
        nodes.add(labelKirjoittaja);
        nodes.add(labelJulkaisuvuosi);
        nodes.add(labelSivumaara);
        nodes.add(labelISBN);
        nodes.add(copyISBN);
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

        Label nimiOtsikko = new Label("Book Title");
        nimiOtsikko.setStyle(cssLayoutBorderTitle01);
        nimiOtsikko.setMaxHeight(26);
        nimiOtsikko.setMinHeight(26);
        nimiOtsikko.setMaxWidth(200);
        nimiOtsikko.setMinWidth(200);

        Label kirjailijaOtsikko = new Label("Author");
        kirjailijaOtsikko.setStyle(cssLayoutBorderTitle01);
        kirjailijaOtsikko.setMaxHeight(26);
        kirjailijaOtsikko.setMinHeight(26);
        kirjailijaOtsikko.setMaxWidth(200);
        kirjailijaOtsikko.setMinWidth(200);

        Label vuosiOtsikko = new Label("Published");
        vuosiOtsikko.setStyle(cssLayoutBorderTitle01);
        vuosiOtsikko.setMaxHeight(26);
        vuosiOtsikko.setMinHeight(26);
        vuosiOtsikko.setMaxWidth(40);
        vuosiOtsikko.setMinWidth(40);

        Label sivumaaraOtsikko = new Label("Page count");
        sivumaaraOtsikko.setStyle(cssLayoutBorderTitle01);
        sivumaaraOtsikko.setMaxHeight(26);
        sivumaaraOtsikko.setMinHeight(26);
        sivumaaraOtsikko.setMaxWidth(40);
        sivumaaraOtsikko.setMinWidth(40);

        Label ISBNOtsikko = new Label("ISBN");
        ISBNOtsikko.setStyle(cssLayoutBorderTitle01);
        ISBNOtsikko.setMaxHeight(26);
        ISBNOtsikko.setMinHeight(26);
        ISBNOtsikko.setMaxWidth(50);
        ISBNOtsikko.setMinWidth(50);

        otsikot.getChildren().addAll(nimiOtsikko, kirjailijaOtsikko, 
            vuosiOtsikko, sivumaaraOtsikko, ISBNOtsikko);

        return otsikot;
    }

}
