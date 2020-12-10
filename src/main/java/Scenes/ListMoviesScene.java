package Scenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: white;\n";

    private String cssLayoutBorderTitle01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: lightblue;\n";

    public ListMoviesScene(ChooseAddScene chooseAddScene) {
        super(
            chooseAddScene,
            new HashMap<String, String[]>(Map.of(
                "Movie", new String[]{"None", "Director", "Title"}
            ))
        );
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
        String textFilterType) {
        return allMovies.stream().filter(movie -> {
            Movie m = (Movie) movie;

            if (textFilterType.equals("Director")) {
                return m.getDirector() != null
                    ? m.getDirector().toLowerCase()
                        .contains(value.toLowerCase())
                    : false;
            }

            if (textFilterType.equals("Title")) {
                return m.getTitle() != null
                    ? m.getTitle().toLowerCase().contains(value.toLowerCase())
                    : false;
            }

            return false;
        })
            .collect(Collectors.toList());
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

            case "Director": {
                this.setShownBookmarks(
                    getFilteredByString(this.getAllBookmarks(),
                        textFilterValue,
                        "Director")
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
        nimeke.setMaxHeight(26);
        nimeke.setMinHeight(26);
        nimeke.setMaxWidth(200);
        nimeke.setMinWidth(200);

        Label director = new Label(((Movie) movie).getDirector());
        director.setStyle(cssLayoutBorder01);
        director.setMaxHeight(26);
        director.setMinHeight(26);
        director.setMaxWidth(200);
        director.setMinWidth(200);

        int vuosi = ((Movie) movie).getReleaseYear();
        String jvuosi = String.valueOf(vuosi);
        if (jvuosi.equals("-9999")) {
            jvuosi = "";
        }
        Label julkaisuvuosi = new Label(jvuosi);
        julkaisuvuosi.setStyle(cssLayoutBorder01);
        julkaisuvuosi.setMaxHeight(26);
        julkaisuvuosi.setMinHeight(26);
        julkaisuvuosi.setMaxWidth(80);
        julkaisuvuosi.setMinWidth(80);

        String kestoMin = String.valueOf(((Movie) movie).getLength());
        if (kestoMin.equals("-9999")) {
            kestoMin = "";
        }
        Label kesto = new Label(kestoMin);
        kesto.setStyle(cssLayoutBorder01);
        kesto.setMaxHeight(26);
        kesto.setMinHeight(26);
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
        otsikot.setSpacing(0);

        Label nimeke = new Label("Title");
        nimeke.setStyle(cssLayoutBorderTitle01);
        nimeke.setMaxHeight(26);
        nimeke.setMinHeight(26);
        nimeke.setMaxWidth(200);
        nimeke.setMinWidth(200);

        Label director = new Label("Director");
        director.setStyle(cssLayoutBorderTitle01);
        director.setMaxHeight(26);
        director.setMinHeight(26);
        director.setMaxWidth(200);
        director.setMinWidth(200);

        Label jvuosi = new Label("Published");
        jvuosi.setStyle(cssLayoutBorderTitle01);
        jvuosi.setMaxHeight(26);
        jvuosi.setMinHeight(26);
        jvuosi.setMaxWidth(80);
        jvuosi.setMinWidth(80);

        Label kesto = new Label("Length");
        kesto.setStyle(cssLayoutBorderTitle01);
        kesto.setMaxHeight(26);
        kesto.setMinHeight(26);
        kesto.setMaxWidth(50);
        kesto.setMinWidth(50);

        otsikot.getChildren().addAll(nimeke, director,
            jvuosi, kesto);

        return otsikot;
    }
}
