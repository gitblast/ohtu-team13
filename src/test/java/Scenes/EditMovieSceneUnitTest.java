package Scenes;

import Domain.Movie;
import Service.VinkkiService;
import javafx.scene.control.Alert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import org.testfx.framework.junit.ApplicationTest;

public class EditMovieSceneUnitTest extends ApplicationTest {

    private EditMovieScene scene;
    private VinkkiService mockService;

    @Before
    public void setUp() {
        scene = new EditMovieScene(mock(ChooseAddScene.class),
            mock(Movie.class), mock(Alert.class));

        // luodaan mockservice, jotta voidaan testata onko sen metodeja kutsuttu
        mockService = mock(VinkkiService.class);

        // asetetaan mockservice scenen vinkkiserviceksi
        scene.vinkkiService = mockService;
    }

    @Test
    public void urliaEiMuokataInvalideillaSyotteilla() {
        scene.setBookmarkInputFields();

        scene.fields.get(0).setText("");

        assertFalse(scene.bookmarkCreation());

        verify(mockService, times(0)).modifyMovie(any());
    }

    @Test
    public void urliaMuokataanValideillaSyotteilla() {
        when(mockService.modifyMovie(any())).thenReturn(true);

        scene.setBookmarkInputFields();

        scene.fields.get(0).setText("vaihdos");

        assertTrue(scene.bookmarkCreation());

        verify(mockService, times(1)).modifyMovie(any());
    }
}
