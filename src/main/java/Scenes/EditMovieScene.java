package Scenes;

import java.util.ArrayList;

import Domain.Movie;
import javafx.scene.control.TextField;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class EditMovieScene extends CreateBookmarkScene {

    Movie movie;
    Button deleteButton;
    Alert alert;

    public EditMovieScene(ChooseAddScene chooseAddScene,
        Movie movie, Alert alert) {
        super(chooseAddScene);
        this.movie = movie;
        this.deleteButton = new Button("Delete movie");
        this.title.setText("Editing movie");
        this.submitButton.setText("Submit changes");
        this.alert = alert;
    }

    @Override
    protected void setBookmarkInputFields() {
        ArrayList<TextField> list = new ArrayList<>();

        TextField nimeke = new TextField();
        TextField director = new TextField();
        TextField julkaisuvuosi = new TextField();
        TextField kesto = new TextField();

        nimeke.setId("nimeke_field");
        director.setId("director_field");
        julkaisuvuosi.setId("julkaisuvuosi_field");
        kesto.setId("kesto_field");

        nimeke.setPromptText("Title");
        director.setPromptText("Director");
        julkaisuvuosi.setPromptText("Published");
        kesto.setPromptText("Length in minutes");

        nimeke.setText(movie.getTitle());
        director.setText(movie.getDirector());
        String releaseYear = String.valueOf(movie.getReleaseYear());
        if (releaseYear.equals("-9999")) {
            releaseYear = "";
        }
        julkaisuvuosi.setText(releaseYear);
        String length = String.valueOf(movie.getLength());
        if (length.equals("-9999")) {
            length = "";
        }
        kesto.setText(length);

        list.add(nimeke);
        list.add(director);
        list.add(julkaisuvuosi);
        list.add(kesto);

        this.fields = list;
    }

    @Override
    protected Button setDeleteButton() {
        String h = "Are you sure you want to delete movie " + movie.getTitle();
        this.deleteButton.setOnAction(e -> {
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setTitle("Delete Movie");
            alert.setHeaderText(h);
            String text = movie.getTitle() + "\n";
            if (movie.getDirector() != null) {
                text += movie.getDirector() + "\n";
            }
            if (movie.getReleaseYear() > 0) {
                text += movie.getReleaseYear() + "\n";
            }
            if (movie.getLength() > 0) {
                text += movie.getLength();
            }
            alert.setContentText(text);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    boolean poisto = vinkkiService.deleteMovie(movie.getId());
                    if (poisto) {
                        destination(destinationIndex());
                    } else {
                        errorMessage.setText("Database error");
                    }
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
        return this.deleteButton;
    }

    @Override
    protected boolean bookmarkCreation() {
        boolean inputsOK = true;

        String nimeke = this.fields.get(0).getText();
        String director = this.fields.get(1).getText();
        String julkaisuvuosi = this.fields.get(2).getText();
        String kestoMin = this.fields.get(3).getText();

        int jvuosi = convertToInteger(julkaisuvuosi);
        movie.setReleaseYear(jvuosi);

        int kesto = convertToInteger(kestoMin);
        movie.setLength(kesto);

        director = checkString(director);
        if (director == null) {
            movie.setDirector("");
        } else {
            movie.setDirector(director);
        }

        nimeke = checkString(nimeke);
        if (nimeke == null) {
            errorMessage.setText("Enter title");
            inputsOK = false;
        }

        if (inputsOK) {
            movie.setTitle(nimeke);
            movie.setDirector(director);
            movie.setReleaseYear(jvuosi);
            movie.setLength(kesto);
            return vinkkiService.modifyMovie(movie);
        }

        return false;
    }

    @Override
    protected int destinationIndex() {
        return 3;
    }
}
