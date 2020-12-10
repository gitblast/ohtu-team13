package Scenes;

import Domain.Book;
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

public class EditBookSceneUnitTest extends ApplicationTest {

    private EditBookScene scene;
    private VinkkiService mockService;
    private Book book;

    @Before
    public void setUp() {
        book = new Book("author", "title", 111, 222, "isbn");
        scene = new EditBookScene(mock(ChooseAddScene.class),
            book, mock(Alert.class));

        // luodaan mockservice, jotta voidaan testata onko sen metodeja kutsuttu
        mockService = mock(VinkkiService.class);

        // asetetaan mockservice scenen vinkkiserviceksi
        scene.vinkkiService = mockService;
    }

    @Test
    public void kirjaaEiMuokataInvalideillaSyotteilla() {
        scene.setBookmarkInputFields();

        scene.fields.get(0).setText("");

        assertFalse(scene.bookmarkCreation());

        verify(mockService, times(0)).modifyBook(any());
    }

    @Test
    public void kirjaMuokataanValideillaSyotteilla() {
        when(mockService.modifyBook(any())).thenReturn(true);

        scene.setBookmarkInputFields();

        scene.fields.get(0).setText("vaihdos");

        assertTrue(scene.bookmarkCreation());

        verify(mockService, times(1)).modifyBook(any());
    }
}
