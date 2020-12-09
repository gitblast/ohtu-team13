package Scenes;

import Domain.Book;
import Domain.Bookmark;
import Service.VinkkiService;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.testfx.framework.junit.ApplicationTest;

public class ListBooksSceneUnitTest extends ApplicationTest {

    ListBooksScene scene;
    VinkkiService mockService;

    @Before
    public void setUp() {
        scene = new ListBooksScene(mock(ChooseAddScene.class));
        mockService = mock(VinkkiService.class);
        scene.vinkkiService = mockService;
    }

    @Test
    public void createBookmarkContentToimii() {
        String kirjoittaja = "validi kirjoittaja";
        String nimeke = "valid nimeke";
        int vuosi = 111;
        int sivut = 222;
        String ISBN = "valid isbn";

        Book b = new Book(kirjoittaja, nimeke, vuosi, sivut, ISBN);

        List<Node> nodes = scene.createBookmarkContent(b);

        assertEquals(7, nodes.size());

        for (int i = 0; i < 5; i++) {
            assertTrue(nodes.get(i) instanceof Label);
        }

        assertTrue(nodes.get(5) instanceof Button);
    }

    @Test
    public void otsikotMetodiToimii() {
        HBox otsikot = scene.otsikot();

        assertEquals(5, otsikot.getChildren().size());

        otsikot.getChildren().forEach(
            otsikko -> assertTrue(otsikko instanceof Label)
        );

        assertEquals("Author",
            ((Label) otsikot.getChildren().get(1)).getText()
        );

        assertEquals("Title",
            ((Label) otsikot.getChildren().get(0)).getText()
        );

        assertEquals("Published",
            ((Label) otsikot.getChildren().get(2)).getText()
        );

        assertEquals("Page count",
            ((Label) otsikot.getChildren().get(3)).getText()
        );

        assertEquals("ISBN",
            ((Label) otsikot.getChildren().get(4)).getText()
        );
    }

    @Test
    public void getFilteredByStringToimii() {
        Bookmark a = new Book("a", "a", 1, 1, "a");
        Bookmark b = new Book("b", "b", 2, 2, "b");
        Bookmark c = new Book("c", "c", 3, 3, "c");

        List<Bookmark> books = new ArrayList<>();

        books.add(a);
        books.add(b);
        books.add(c);

        List<Bookmark> unknownFilter = scene
            .getFilteredByString(books, "a", "unknown");

        assertEquals(0, unknownFilter.size());

        List<Bookmark> filteredByAuthor = scene
            .getFilteredByString(books, "a", "Author");

        assertEquals(1, filteredByAuthor.size());
        assertEquals(filteredByAuthor.get(0), a);

        List<Bookmark> filteredByISBN = scene
            .getFilteredByString(books, "b", "ISBN");

        assertEquals(1, filteredByISBN.size());
        assertEquals(filteredByISBN.get(0), b);

        List<Bookmark> filteredByTitle = scene
            .getFilteredByString(books, "c", "Title");

        assertEquals(1, filteredByTitle.size());
        assertEquals(filteredByTitle.get(0), c);

        Bookmark authorNull = new Book(null, "a", 1, 1, "a");
        Bookmark nimekeNull = new Book("b", null, 2, 2, "b");
        Bookmark isbnNull = new Book("c", "c", 2, 2, null);

        books = new ArrayList<>();
        books.add(authorNull);

        List<Bookmark> nullAuthor = scene
            .getFilteredByString(books, "a", "Author");

        assertEquals(0, nullAuthor.size());

        books = new ArrayList<>();
        books.add(isbnNull);

        List<Bookmark> nullISBN = scene
            .getFilteredByString(books, "b", "ISBN");

        assertEquals(0, nullISBN.size());

        books = new ArrayList<>();
        books.add(nimekeNull);

        List<Bookmark> nullTitle = scene
            .getFilteredByString(books, "c", "Title");

        assertEquals(0, nullTitle.size());
    }

    @Test
    public void filterChangeGetsHandled() {
        Bookmark a = new Book("a", "xxx", 1, 1, "xxx");
        Bookmark b = new Book("yyy", "b", 2, 2, "yyy");
        Bookmark c = new Book("zzz", "zzz", 3, 3, "c");

        List<Bookmark> books = new ArrayList<>();

        books.add(a);
        books.add(b);
        books.add(c);

        scene.setAllBookmarks(books);
        scene.setShownBookmarks(null);

        scene.getFilterField().setText("");

        scene.handleFilterChange("any", "");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("Author", "a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), a);

        scene.handleFilterChange("Title", "b");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), b);

        scene.handleFilterChange("ISBN", "c");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), c);

        scene.handleFilterChange("None", "any");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("unknown", "a");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());
    }

    @Test
    public void changeListenerFilterKentalle() {
        List<Bookmark> books = setSampleBooks();

        scene.createScene(books);

        ChoiceBox<String> cb = new ChoiceBox<String>(
            FXCollections.observableArrayList(
                new String[]{"Author"}
            )
        );

        scene.setChoiceBox(cb);

        TextField tf = new TextField();

        scene.setChangeListenerForFilterField(tf);

        scene.getChoiceBox().getSelectionModel().select("Author");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(books.get(0),
            scene.getShownBookmarks().get(0));

    }

    @Test
    public void changeListenerChoiceBoksille() {
        List<Bookmark> books = setSampleBooks();

        scene.createScene(books);

        ChoiceBox<String> cb = new ChoiceBox<String>(
            FXCollections.observableArrayList(
                new String[]{"None", "Title"}
            )
        );

        scene.setChoiceBox(cb);

        scene.setChangeListenerForChoiceBox(cb);

        scene.getChoiceBox().getSelectionModel().select("None");

        assertEquals(3, scene.getShownBookmarks().size());
        assertEquals(books, scene.getShownBookmarks());

        scene.getChoiceBox().getSelectionModel().select("Title");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("b");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(books.get(1),
            scene.getShownBookmarks().get(0));

    }

    public List<Bookmark> setSampleBooks() {
        Bookmark a = new Book("a", "xxx", 1, 1, "xxx");
        Bookmark b = new Book("yyy", "b", 2, 2, "yyy");
        Bookmark c = new Book("zzz", "zzz", 3, 3, "c");

        List<Bookmark> books = new ArrayList<>();

        books.add(a);
        books.add(b);
        books.add(c);

        scene.setAllBookmarks(books);

        return books;
    }
}
