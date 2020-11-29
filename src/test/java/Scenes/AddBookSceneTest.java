package Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
public class AddBookSceneTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        ChooseAddScene cas = new ChooseAddScene(stage);
        AddBookScene c = new AddBookScene(cas);

        Scene scene = c.createScene();

        stage.setScene(scene);
        stage.show();
    }

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
