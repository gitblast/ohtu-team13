package Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AddBookSceneTest extends ApplicationTest {
    private ChooseAddScene cas;
    private AddBookScene addBook;

    @Override
    public void start(Stage stage) throws Exception {
        cas = new ChooseAddScene(stage);
        addBook = new AddBookScene(cas);

        Scene scene = addBook.createScene();

        stage.setScene(scene);
        stage.show();
    }

    // TODO
    // Toistaiseksi luo tämä kyseisen kirjan
    // aina kun suoritetaan gradle test
    // @Test
    // public void kirjanLuominenToimii() {
    //     addBook.fields.get(0).setText("Johanna Sinisalo");
    //     addBook.fields.get(1).setText("Auringon ydin");
    //     addBook.fields.get(2).setText("2013");
    //     addBook.fields.get(3).setText("300");
    //     addBook.fields.get(4).setText("9789518515527");
    //     addBook.submitButton.fire();
    //     assertEquals("", addBook.fields.get(0).getText());
    // }

    // TODO
    // assertEquals ja verifyThat antoivat molemmat virheen:
    // "java.lang.RuntimeException: java.lang.IllegalStateException:
    // Not on FX application thread; currentThread = main"
    // @Test
    // public void kirjaaEiLuodaIlmanKaikkiaKenttiä1() {
    //     addBook.fields.get(0).setText("Johanna Sinisalo");
    //     addBook.fields.get(1).setText("Auringon ydin");
    //     addBook.fields.get(2).setText("2013");
    //     addBook.fields.get(3).setText("300");
    //     addBook.submitButton.fire();
    //     assertEquals("Enter author, title and ISBN",
    //         addBook.errorMessage.getText());
    //     // verifyThat("#errorMessage_label", hasText("Enter author, title and ISBN"));
    // }

    @Test
    public void elementeillaOikeatTekstit() {
        verifyThat("#title_label", hasText("Adding a new Book"));
        verifyThat("#returnButton_btn", hasText("Return"));
        verifyThat("#errorMessage_label", hasText(""));
        verifyThat("#submitButton_btn", hasText("Add a new book"));
    }

    @Test
    public void renderoiOikeatElementit() {
        verifyThat("#elements", hasChild("#title_label"));
        verifyThat("#elements", hasChild("#returnButton_btn"));
        verifyThat("#elements", hasChild("#errorMessage_label"));
        verifyThat("#elements", hasChild("#submitButton_btn"));

        verifyThat("#elements", hasChild("#kirjoittaja_field"));
        verifyThat("#elements", hasChild("#nimeke_field"));
        verifyThat("#elements", hasChild("#julkaisuvuosi_field"));
        verifyThat("#elements", hasChild("#sivumaara_field"));
        verifyThat("#elements", hasChild("#ISBN_field"));
    }

}
