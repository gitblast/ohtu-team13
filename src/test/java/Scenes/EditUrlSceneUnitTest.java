package Scenes;

import Domain.Url;
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

public class EditUrlSceneUnitTest extends ApplicationTest {

    private EditURLScene scene;
    private VinkkiService mockService;

    @Before
    public void setUp() {
        scene = new EditURLScene(mock(ChooseAddScene.class),
            mock(Url.class), mock(Alert.class));

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

        verify(mockService, times(0)).modifyUrl(any());
    }

    @Test
    public void urliaMuokataanValideillaSyotteilla() {
        when(mockService.modifyUrl(any())).thenReturn(true);

        scene.setBookmarkInputFields();

        scene.fields.get(0).setText("vaihdos");
        scene.fields.get(1).setText("vaihdos2");

        assertTrue(scene.bookmarkCreation());

        verify(mockService, times(1)).modifyUrl(any());
    }
}
