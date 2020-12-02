package Scenes;

import Domain.Url;
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

public class AddURLSceneUnitTest extends ApplicationTest {

    private AddURLScene addUrlScene;
    private VinkkiService mockService;

    @Before
    public void setUp() {
        addUrlScene = new AddURLScene(mock(ChooseAddScene.class));

        // luodaan mockservice, jotta voidaan testata onko sen metodeja kutsuttu
        mockService = mock(VinkkiService.class);

        // asetetaan mockservice scenen vinkkiserviceksi
        addUrlScene.vinkkiService = mockService;
    }

    @Test
    public void inputFielditLuodaanOikein() {
        addUrlScene.setBookmarkInputFields();

        assertEquals(2, addUrlScene.fields.size());

        assertEquals("Header", addUrlScene.fields.get(0).getPromptText());

        assertEquals("URL", addUrlScene.fields.get(1).getPromptText());
    }

    @Test
    public void urliaEiLuodaInvalideillaSyotteilla() {
        // tyhjä otsikko
        asetaSyotteet("", "valid url");

        assertFalse(addUrlScene.bookmarkCreation());

        // tyhjä url
        asetaSyotteet("valid otsikko", "");

        assertFalse(addUrlScene.bookmarkCreation());

        // testaa, ettei vinkkiService.addURL:ia ole kutsuttu
        verify(mockService, times(0)).addURL(any());
    }

    @Test
    public void kirjaLuodaanValideillaSyotteilla() {
        // asetetaan addURL palauttamaan true kutsuttaessa
        when(mockService.addURL(any())).thenReturn(true);

        String otsikko = "validi otsikko";
        String URL = "valid url";

        asetaSyotteet(otsikko, URL);

        assertTrue(addUrlScene.bookmarkCreation());

        Url luotuUrl = new Url(otsikko, URL);

        // testaa, että vinkkiservicen addurlia kutsutaan luodulla url-oliolla
        verify(mockService, times(1)).addURL(
            argThat(url -> url.equals(luotuUrl))
        );
    }

    public void asetaSyotteet(String otsikko, String url) {
        addUrlScene.setBookmarkInputFields();

        addUrlScene.fields.get(0).setText(otsikko);
        addUrlScene.fields.get(1).setText(url);
    }
}
