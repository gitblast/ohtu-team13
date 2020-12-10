package Scenes;

import Domain.Movie;
import Service.VinkkiService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.when;
import org.testfx.framework.junit.ApplicationTest;

public class AddMovieSceneUnitTest extends ApplicationTest {

    private AddMovieScene addMovieScene;
    private VinkkiService mockService;
    private ChooseAddScene mockChooseAdd;

    @Before
    public void setUp() {
        mockChooseAdd = mock(ChooseAddScene.class);

        addMovieScene = new AddMovieScene(mockChooseAdd);

        // luodaan mockservice, jotta voidaan testata onko sen metodeja kutsuttu
        mockService = mock(VinkkiService.class);

        // asetetaan mockservice scenen vinkkiserviceksi
        addMovieScene.vinkkiService = mockService;
    }

    @Test
    public void inputFielditLuodaanOikein() {
        addMovieScene.setBookmarkInputFields();

        assertEquals(4, addMovieScene.fields.size());

        assertEquals("Title", addMovieScene.fields.get(0).getPromptText());

        assertEquals("Director", addMovieScene.fields.get(1).getPromptText());

        assertEquals("Published", addMovieScene.fields.get(2).getPromptText());

        assertEquals("Length in minutes", addMovieScene.fields
            .get(3).getPromptText());
    }

    @Test
    public void elokuvaaEiLuodaTyhjallaNimekkeella() {
        // tyhjä nimeke
        asetaSyotteet("", "valid ohjaaja", "111", "222");

        assertFalse(addMovieScene.bookmarkCreation());

        // testaa, ettei vinkkiService.addMovieta ole kutsuttu
        verify(mockService, times(0)).addMovie(any());
    }

    @Test
    public void elokuvaaLuodaanValideillaSyotteilla() {
        // asetetaan addMovie palauttamaan true kutsuttaessa
        when(mockService.addMovie(any())).thenReturn(true);

        String nimeke = "validi nimeke";
        String director = "valid ohjaaja";
        String vuosi = "111";
        String kesto = "222";

        asetaSyotteet(nimeke, director, vuosi, kesto);

        assertTrue(addMovieScene.bookmarkCreation());

        Movie luotuMovie = new Movie(nimeke, director,
            Integer.valueOf(vuosi), Integer.valueOf(kesto));

        // testaa, että vinkkiservicen addmovie kutsutaan luodulla movie-oliolla
        verify(mockService, times(1)).addMovie(
            argThat(movie -> movie.equals(luotuMovie))
        );
    }

    @Test
    public void nimenSyottaminenRiittaaLuomaanElokuvan() {
        when(mockService.addMovie(any())).thenReturn(true);
        asetaSyotteet("validi nimeke", "", "", "");

        assertTrue(addMovieScene.bookmarkCreation());

        Movie luotuMovie = new Movie("validi nimeke", "",
            Integer.valueOf("-9999"), Integer.valueOf("-9999"));
        verify(mockService, times(1)).addMovie(
            argThat(movie -> movie.equals(luotuMovie))
        );
    }

    private void asetaSyotteet(String nimeke, String director,
        String julkaisuvuosi, String kesto) {
        addMovieScene.setBookmarkInputFields();

        addMovieScene.fields.get(0).setText(nimeke);
        addMovieScene.fields.get(1).setText(director);
        addMovieScene.fields.get(2).setText(julkaisuvuosi);
        addMovieScene.fields.get(3).setText(kesto);
    }
}
