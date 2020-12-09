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
        verifyThat("#lisaa_kirja_btn", hasText("Add Book"));
        verifyThat("#lisaa_url_btn", hasText("Add URL"));
        verifyThat("#lisaa_elokuva_btn", hasText("Add Movie"));
        verifyThat("#listaa_kaikki_btn", hasText("List all bookmarks"));
    }

    @Test
    public void renderoiOikeatElementit() {
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_kirja_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_url_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#lisaa_elokuva_btn"));
        /*verifyThat("#chooseAdd_elements", hasChild("#listaa_kirjat_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#listaa_urlit_btn"));
        verifyThat("#chooseAdd_elements", hasChild("#listaa_elokuvat_btn"));*/
        verifyThat("#chooseAdd_elements", hasChild("#listaa_kaikki_btn"));
    }
}
