package Scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;

import Domain.Book;

import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class EditBookmarkSceneTest extends ApplicationTest {
    
    private ChooseAddScene cas;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        cas = new ChooseAddScene(stage);
        Book book = new Book(999, "Kirjailija", "KirjanNimi", 2000,
                200, "12345");
        EditBookScene editBook = new EditBookScene(cas, book);

        scene = editBook.createScene();

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void returnButtonToimii() {
        clickOn("#returnButton_btn");
        verifyThat("#listing_elements", hasChild("#filterField"));
    }

    // @Test
    // public void deleteButtonToimii() {
    //     clickOn("#deleteButton_btn");
    //     TODO
    // }

    @Test
    public void renderoiOikeatElementit() {
        verifyThat("#elements", hasChild("#deleteButton_btn"));
    }

    @Test
    public void elementeillaOikeatTekstit() {
        verifyThat("#deleteButton_btn", hasText("Delete book"));
        verifyThat("#title_label", hasText("Editing book"));
        verifyThat("#returnButton_btn", hasText("Return"));
        verifyThat("#errorMessage_label", hasText(""));
        verifyThat("#submitButton_btn", hasText("Submit changes"));
    }
}
