package Scenes;

import Domain.Bookmark;
import Domain.Url;
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
public class ListUrlsSceneUnitTest extends ApplicationTest {

    ListUrlsScene scene;
    VinkkiService mockService;

    @Before
    public void setUp() {
        scene = new ListUrlsScene(mock(ChooseAddScene.class));
        mockService = mock(VinkkiService.class);
        scene.vinkkiService = mockService;
    }

    @Test
    public void createBookmarkContentToimii() {
        String title = "validi title";
        String url = "valid url";

        Url u = new Url(title, url);

        List<Node> nodes = scene.createBookmarkContent(u);

        assertEquals(4, nodes.size());

        assertTrue(nodes.get(0) instanceof Label);
        assertTrue(nodes.get(1) instanceof Label);

        assertTrue(nodes.get(2) instanceof Button);
        assertTrue(nodes.get(3) instanceof Button);
    }

    @Test
    public void otsikotMetodiToimii() {
        HBox otsikot = scene.otsikot();

        assertEquals(2, otsikot.getChildren().size());

        otsikot.getChildren().forEach(
            otsikko -> assertTrue(otsikko instanceof Label)
        );

        assertEquals("Header",
            ((Label) otsikot.getChildren().get(0)).getText()
        );

        assertEquals("URL",
            ((Label) otsikot.getChildren().get(1)).getText()
        );
    }

    @Test
    public void getFilteredByStringToimii() {
        Bookmark a = new Url("a", "xxx");
        Bookmark b = new Url("yyy", "b");

        List<Bookmark> urls = new ArrayList<>();

        urls.add(a);
        urls.add(b);

        List<Bookmark> unknownFilter = scene
            .getFilteredByString(urls, "a", "unknown");

        assertEquals(0, unknownFilter.size());

        List<Bookmark> filteredByAuthor = scene
            .getFilteredByString(urls, "a", "Title");

        assertEquals(1, filteredByAuthor.size());
        assertEquals(filteredByAuthor.get(0), a);

        Bookmark titleNull = new Url(null, "valid");

        urls = new ArrayList<>();
        urls.add(titleNull);

        List<Bookmark> nullTitle = scene
            .getFilteredByString(urls, "a", "Title");

        assertEquals(0, nullTitle.size());
    }

    @Test
    public void filterChangeGetsHandled() {
        Bookmark a = new Url("a", "xxx");
        Bookmark b = new Url("yyy", "b");

        List<Bookmark> urls = new ArrayList<>();

        urls.add(a);
        urls.add(b);

        scene.setAllBookmarks(urls);
        scene.setShownBookmarks(null);

        scene.getFilterField().setText("");

        scene.handleFilterChange("any", "");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("Title", "a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(scene.getShownBookmarks().get(0), a);

        scene.handleFilterChange("None", "any");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());

        scene.handleFilterChange("unknown", "a");

        assertEquals(scene.getAllBookmarks(), scene.getShownBookmarks());
    }

    @Test
    public void changeListenerFilterKentalle() {
        Bookmark a = new Url("a", "xxx");
        Bookmark b = new Url("yyy", "b");

        List<Bookmark> urls = new ArrayList<>();

        urls.add(a);
        urls.add(b);

        scene.createScene(urls);

        ChoiceBox cb = new ChoiceBox<String>(FXCollections.observableArrayList(
            new String[]{"Title"})
        );

        scene.setChoiceBox(cb);

        TextField tf = new TextField();

        scene.setChangeListenerForFilterField(tf);

        scene.getChoiceBox().getSelectionModel().select("Title");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(urls.get(0),
            scene.getShownBookmarks().get(0));

    }

    @Test
    public void changeListenerChoiceBoksille() {
        Bookmark a = new Url("a", "xxx");
        Bookmark b = new Url("yyy", "b");

        List<Bookmark> urls = new ArrayList<>();

        urls.add(a);
        urls.add(b);

        scene.createScene(urls);

        ChoiceBox cb = new ChoiceBox<String>(FXCollections.observableArrayList(
            new String[]{"None", "Title"})
        );

        scene.setChoiceBox(cb);

        scene.setChangeListenerForChoiceBox(cb);

        scene.getChoiceBox().getSelectionModel().select("None");

        assertEquals(2, scene.getShownBookmarks().size());
        assertEquals(urls, scene.getShownBookmarks());

        scene.getChoiceBox().getSelectionModel().select("Title");

        scene.getFilterField().setDisable(false);
        scene.getFilterField().setText("a");

        assertEquals(1, scene.getShownBookmarks().size());
        assertEquals(urls.get(0),
            scene.getShownBookmarks().get(0));

    }
}
