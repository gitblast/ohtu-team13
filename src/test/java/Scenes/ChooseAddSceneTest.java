package Scenes;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ChooseAddSceneTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        ChooseAddScene c = new ChooseAddScene(stage);

        Scene scene = c.createScene();

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void napeillaOikeatTekstit() {
        verifyThat("#lisaa_kirja_btn", hasText("Lisää kirja"));
        verifyThat("#lisaa_url_btn", hasText("Lisää URL"));
        verifyThat("#listaa_kirjat_btn", hasText("Tallennetut kirjat"));
        verifyThat("#listaa_urlit_btn", hasText("Tallennetut URLit"));
    }

    @Test
    public void renderoiOikeatElementit() {
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_kirja_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_url_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#listaa_kirjat_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#listaa_urlit_btn"));
    }
}
