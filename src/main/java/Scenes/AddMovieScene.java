package Scenes;

import Domain.Movie;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Button;

public class AddMovieScene extends CreateBookmarkScene {

    public AddMovieScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);
        this.title.setText("Adding a new Movie");
        this.submitButton.setText("Add a new movie");
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

        list.add(nimeke);
        list.add(director);
        list.add(julkaisuvuosi);
        list.add(kesto);

        this.fields = list;
    }

    @Override
    protected boolean bookmarkCreation() {
        boolean titleIsOk = true;
        Movie movie = new Movie("");
        
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
            titleIsOk = false;
        }
        
        if (titleIsOk) {
            movie.setTitle(nimeke);
            return vinkkiService.addMovie(movie);
        }

        return false;
    }
    
    @Override
    protected Button setDeleteButton() {
        return null;
    }
}
