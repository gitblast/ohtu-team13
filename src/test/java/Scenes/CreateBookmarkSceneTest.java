package Scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;

public class CreateBookmarkSceneTest extends ApplicationTest{
    
    private ChooseAddScene cas;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        cas = new ChooseAddScene(stage);
        AddBookScene addBook = new AddBookScene(cas);

        scene = addBook.createScene();

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void returnButtonToimii() {
        clickOn("#returnButton_btn");
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_kirja_btn"));
    }

    @Test
    public void tyhjaStringTarkistetaan() {
        clickOn("#kirjoittaja_field");
        write("A");

        clickOn("#nimeke_field");
        write("   ");

        clickOn("#julkaisuvuosi_field");
        write("1");

        clickOn("#sivumaara_field");
        write("1");

        clickOn("#ISBN_field");
        write("a");

        clickOn("#submitButton_btn");
        verifyThat("#elements", hasChild("#kirjoittaja_field"));
    }

    @Test
    public void kokonaislukuTarkistetaan() {
        clickOn("#kirjoittaja_field");
        write("A");

        clickOn("#nimeke_field");
        write("A");

        clickOn("#julkaisuvuosi_field");
        write("a");

        clickOn("#sivumaara_field");
        write("1");

        clickOn("#ISBN_field");
        write("a");

        clickOn("#submitButton_btn");
        verifyThat("#elements", hasChild("#kirjoittaja_field"));
    }
}