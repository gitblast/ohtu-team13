package Scenes;

import java.util.ArrayList;

import Domain.Movie;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditMovieScene extends CreateBookmarkScene {
        
    Movie movie;
    Button deleteButton;
    
    public EditMovieScene(ChooseAddScene chooseAddScene, Movie movie) {
        super(chooseAddScene);
        this.movie = movie;
        this.deleteButton = new Button("Delete movie");
        this.title.setText("Editing movie");
        this.submitButton.setText("Submit changes");
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
        julkaisuvuosi.setText("" + movie.getReleaseYear());
        kesto.setText("" + movie.getLength());

        list.add(nimeke);
        list.add(director);
        list.add(julkaisuvuosi);
        list.add(kesto);

        this.fields = list;
    }
    
    @Override
    protected Button setDeleteButton() {
        this.deleteButton.setOnAction(e -> {
            try {
                boolean poistettu = vinkkiService.deleteMovie(movie.getId());
                if (poistettu) {
                    chooseAddScene.listMoviesScene();
                }
            } catch (Exception error) {
                System.out.println(error.getMessage());
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
        int kesto = convertToInteger(kestoMin);
        if (jvuosi == -9999 || kesto == -9999) {
            errorMessage.setText("Enter valid release year "
                    + "and length");
            inputsOK = false;
        }

        director = checkString(director);
        nimeke = checkString(nimeke);
        if (director == null || nimeke == null) {
            errorMessage.setText("Enter title and director");
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
