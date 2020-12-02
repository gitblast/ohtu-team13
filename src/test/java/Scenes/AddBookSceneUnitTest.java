package Scenes;

import Domain.Book;
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

public class AddBookSceneUnitTest extends ApplicationTest {

    private AddBookScene addBookScene;
    private VinkkiService mockService;

    @Before
    public void setUp() {
        addBookScene = new AddBookScene(mock(ChooseAddScene.class));

        // luodaan mockservice, jotta voidaan testata onko sen metodeja kutsuttu
        mockService = mock(VinkkiService.class);

        // asetetaan mockservice scenen vinkkiserviceksi
        addBookScene.vinkkiService = mockService;
    }

    @Test
    public void inputFielditLuodaanOikein() {
        addBookScene.setBookmarkInputFields();

        assertEquals(5, addBookScene.fields.size());

        assertEquals("Author", addBookScene.fields.get(0).getPromptText());

        assertEquals("Title", addBookScene.fields.get(1).getPromptText());

        assertEquals("Published", addBookScene.fields.get(2).getPromptText());

        assertEquals("Page count", addBookScene.fields.get(3).getPromptText());

        assertEquals("ISBN", addBookScene.fields.get(4).getPromptText());
    }

    @Test
    public void kirjaaEiLuodaInvalideillaSyotteilla() {
        // tyhjä kirjoittaja
        asetaSyotteet("", "valid nimeke", "111", "222", "valid isbn");

        assertFalse(addBookScene.bookmarkCreation());

        // tyhjä nimeke
        asetaSyotteet("validi kirjoittaja", "", "111", "222", "valid isbn");

        assertFalse(addBookScene.bookmarkCreation());

        // vuosi ei numero
        asetaSyotteet("validi kirjoittaja",
            "valid nimeke", "ei numero", "222", "valid isbn");

        assertFalse(addBookScene.bookmarkCreation());

        // sivum ei numero
        asetaSyotteet("validi kirjoittaja",
            "valid nimeke", "111", "ei numero", "valid isbn");

        assertFalse(addBookScene.bookmarkCreation());

        // tyhjä isbn
        asetaSyotteet("validi kirjoittaja", "valid nimeke", "111", "222", "");

        assertFalse(addBookScene.bookmarkCreation());

        // testaa, ettei vinkkiService.addBook:ia ole kutsuttu
        verify(mockService, times(0)).addBook(any());
    }

    @Test
    public void kirjaLuodaanValideillaSyotteilla() {
        // asetetaan addBook palauttamaan true kutsuttaessa
        when(mockService.addBook(any())).thenReturn(true);

        String kirjoittaja = "validi kirjoittaja";
        String nimeke = "valid nimeke";
        String vuosi = "111";
        String sivut = "222";
        String ISBN = "valid isbn";

        asetaSyotteet(kirjoittaja, nimeke, vuosi, sivut, ISBN);

        assertTrue(addBookScene.bookmarkCreation());

        Book luotuKirja = new Book(kirjoittaja, nimeke, Integer.valueOf(vuosi),
            Integer.valueOf(sivut), ISBN);

        // testaa, että vinkkiservicen addbookkia kutsutaan luodulla oliolla
        verify(mockService, times(1)).addBook(
            argThat(book -> {
                return book.equals(luotuKirja)
                    && book.getISBN().equals(luotuKirja.getISBN());
            })
        );
    }

    public void asetaSyotteet(String kirjoittaja, String nimeke,
        String julkaisuvuosi, String sivumaara, String ISBN) {
        addBookScene.setBookmarkInputFields();

        addBookScene.fields.get(0).setText(kirjoittaja);
        addBookScene.fields.get(1).setText(nimeke);
        addBookScene.fields.get(2).setText(julkaisuvuosi);
        addBookScene.fields.get(3).setText(sivumaara);
        addBookScene.fields.get(4).setText(ISBN);
    }
}
