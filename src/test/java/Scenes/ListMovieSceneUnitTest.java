package Scenes;

import Domain.Bookmark;
import Domain.Movie;
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

@SuppressWarnings("unchecked")
public class ListMovieSceneUnitTest extends ApplicationTest {

    ListMoviesScene scene;
    VinkkiService mockService;

    @Before
    public void setUp() {
        scene = new ListMoviesScene(mock(ChooseAddScene.class));
        mockService = mock(VinkkiService.class);
        scene.vinkkiService = mockService;
    }

    @Test
    public void createBookmarkContentToimii() {
        String title = "validi title";
        String director = "valid director";
        int vuosi = 111;
        int kesto = 222;

        Movie m = new Movie(title, director, vuosi, kesto);

        List<Node> nodes = scene.createBookmarkContent(m);

        assertEquals(5, nodes.size());

        assertTrue(nodes.get(0) instanceof Label);
        assertTrue(nodes.get(1) instanceof Label);
        assertTrue(nodes.get(2) instanceof Label);
        assertTrue(nodes.get(3) instanceof Label);

        assertTrue(nodes.get(4) instanceof Button);
    }

    @Test
    public void otsikotMetodiToimii() {
        HBox otsikot = scene.otsikot();

        assertEquals(4, otsikot.getChildren().size());

        otsikot.getChildren().forEach(
            otsikko -> assertTrue(otsikko instanceof Label)
        );

        assertEquals("Title",
            ((Label) otsikot.getChildren().get(0)).getText()
        );

        assertEquals("Director",
            ((Label) otsikot.getChildren().get(1)).getText()
        );

        assertEquals("Published",
            ((Label) otsikot.getChildren().get(2)).getText()
        );

        assertEquals("Length",
            ((Label) otsikot.getChildren().get(3)).getText()
        );
    }

    @Test
    public void getFilteredByStringToimii() {
        Bookmark a = new Movie("a", "xxx", 111, 111);
        Bookmark b = new Movie("yyy", "b", 222, 222);

        List<Bookmark> movies = new ArrayList<>();

        movies.add(a);
        movies.add(b);

        List<Bookmark> unknownFilter = scene
            .getFilteredByString(movies, "a", "unknown");

        assertEquals(0, unknownFilter.size());

        List<Bookmark> filteredByTitle = scene
            .getFilteredByString(movies, "a", "Title");

        assertEquals(1, filteredByTitle.size());
        assertEquals(filteredByTitle.get(0), a);

        List<Bookmark> filteredByDirector = scene
            .getFilteredByString(movies, "b", "Director");

        assertEquals(1, filteredByDirector.size());
        assertEquals(filteredByDirector.get(0), b);

        Bookmark titleNull = new Movie(null, "valid", 111, 111);

        movies = new ArrayList<>();
        movies.add(titleNull);

        List<Bookmark> nullTitle = scene
            .getFilteredByString(movies, "a", "Title");

        assertEquals(0, nullTitle.size());

        Bookmark directorNull = new Movie("valid", null, 111, 111);

        movies = new ArrayList<>();
        movies.add(directorNull);

        List<Bookmark> nullDirector = scene
            .getFilteredByString(movies, "b", "Director");

        assertEquals(0, nullDirector.size());
    }

    @Test
    public void filterChangeGetsHandled() {
        Bookmark a = new Movie("a", "xxx", 111, 111);
        Bookmark b = new Movie("yyy", "b", 222, 222);

        List<Bookmark> movies = new ArrayList<>();

        movies.add(a);
        movies.add(b);

        scene.setAllBookmarks(movies);
        scene.setShownBookmarks(null);

        scene.getFilterField().setText("");

        scene.handleFilterChange("any", "");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("Title", "a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), a);

        scene.handleFilterChange("Director", "b");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), b);

        scene.handleFilterChange("None", "any");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("unknown", "a");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());
    }

    @Test
    public void changeListenerFilterKentalle() {
        Bookmark a = new Movie("a", "xxx", 111, 111);
        Bookmark b = new Movie("yyy", "b", 222, 222);

        List<Bookmark> movies = new ArrayList<>();

        movies.add(a);
        movies.add(b);

        scene.createScene(movies);

        ChoiceBox cb = new ChoiceBox<String>(FXCollections.observableArrayList(
            new String[]{"Title", "Director"})
        );

        scene.setChoiceBox(cb);

        TextField tf = new TextField();

        scene.setChangeListenerForFilterField(tf);

        scene.getChoiceBox().getSelectionModel().select("Title");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(movies.get(0),
            scene.getShownBookmarks().get(0));

        scene.getChoiceBox().getSelectionModel().select("Director");

        scene.getFilterField().setText("b");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(movies.get(1),
            scene.getShownBookmarks().get(0));

    }

    @Test
    public void changeListenerChoiceBoksille() {
        Bookmark a = new Movie("a", "xxx", 111, 111);
        Bookmark b = new Movie("yyy", "b", 222, 222);

        List<Bookmark> movies = new ArrayList<>();

        movies.add(a);
        movies.add(b);

        scene.createScene(movies);

        ChoiceBox cb = new ChoiceBox<String>(FXCollections.observableArrayList(
            new String[]{"None", "Title", "Director"})
        );

        scene.setChoiceBox(cb);

        scene.setChangeListenerForChoiceBox(cb);

        scene.getChoiceBox().getSelectionModel().select("None");

        assertEquals(2, scene.getShownBookmarks().size());
        assertEquals(movies, scene.getShownBookmarks());

        scene.getChoiceBox().getSelectionModel().select("Title");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(movies.get(0),
            scene.getShownBookmarks().get(0));

    }
}
