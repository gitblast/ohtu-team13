package Scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.value.ObservableValue;

import Domain.Bookmark;
import Domain.Movie;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ListMoviesScene extends ListingScene {

    private EditMovieScene editMovieScene;
    // UI Style elements
    private String cssLayoutBorder01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n";

    public ListMoviesScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene, new String[]{"None", "Director", "Title"});
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

    protected List<Bookmark> getFilteredByString(
        List<Bookmark> allMovies,
        String value,
        String filterType) {
        return allMovies.stream().filter(movie -> {
            Movie m = (Movie) movie;

            if (filterType.equals("Director")) {
                return m.getDirector() != null
                    ? m.getDirector().toLowerCase()
                        .contains(value.toLowerCase())
                    : false;
            }

            if (filterType.equals("Title")) {
                return m.getTitle() != null
                    ? m.getTitle().toLowerCase().contains(value.toLowerCase())
                    : false;
            }

            return false;
        })
            .collect(Collectors.toList());
    }

    protected void handleFilterChange(
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
            case "Director": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue,
                        "Director")
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
        }
    }

    public List<Node> getBookmarkNode(Bookmark movie) {
        return createBookmarkContent(movie);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark movie) {
        List<Node> nodes = new ArrayList<>();

        Label nimeke = new Label(((Movie) movie).getTitle());
        nimeke.setStyle(cssLayoutBorder01);
        nimeke.setMaxWidth(200);
        nimeke.setMinWidth(200);

        Label director = new Label(((Movie) movie).getDirector());
        director.setStyle(cssLayoutBorder01);
        director.setMaxWidth(200);
        director.setMinWidth(200);

        String jvuosi = String.valueOf(((Movie) movie).getReleaseYear());
        Label julkaisuvuosi = new Label(jvuosi);
        julkaisuvuosi.setStyle(cssLayoutBorder01);
        julkaisuvuosi.setMaxWidth(50);
        julkaisuvuosi.setMinWidth(50);

        String kestoMin = String.valueOf(((Movie) movie).getLength());
        Label kesto = new Label(kestoMin);
        kesto.setStyle(cssLayoutBorder01);
        kesto.setMaxWidth(50);
        kesto.setMinWidth(50);

        Button editButton = new Button("Edit");
        editButtonFunction(editButton, (Movie) movie);

        nodes.add(nimeke);
        nodes.add(director);
        nodes.add(julkaisuvuosi);
        nodes.add(kesto);
        nodes.add(editButton);

        return nodes;
    }

    private void editButtonFunction(
        Button button, Movie movie
    ) {
        button.setOnAction(e -> {
            try {
                editMovieScene = new EditMovieScene(chooseAddScene, movie);
                chooseAddScene.setScene(editMovieScene.createScene());
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        });
    }

    @Override
    protected HBox otsikot() {
        HBox otsikot = new HBox();
        otsikot.setSpacing(5);

        Label nimeke = new Label("Title");
        nimeke.setStyle(cssLayoutBorder01);
        nimeke.setMaxWidth(200);
        nimeke.setMinWidth(200);

        Label director = new Label("Director");
        director.setStyle(cssLayoutBorder01);
        director.setMaxWidth(200);
        director.setMinWidth(200);

        Label jvuosi = new Label("Published");
        jvuosi.setStyle(cssLayoutBorder01);
        jvuosi.setMaxWidth(90);
        jvuosi.setMinWidth(50);

        Label kesto = new Label("Length");
        kesto.setStyle(cssLayoutBorder01);
        kesto.setMaxWidth(90);
        kesto.setMinWidth(50);

        otsikot.getChildren().addAll(nimeke, director,
            jvuosi, kesto);

        return otsikot;
    }
}
