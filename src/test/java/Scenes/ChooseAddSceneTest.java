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
    
    @Test
    public void lisaaKirjaButtoninKlikkaaminenRenderoiOikeatElementit() {
        clickOn("#lisaa_kirja_btn");
        verifyThat("#elements", hasChild("#kirjoittaja_field"));
        verifyThat("#elements", hasChild("#nimeke_field"));
        verifyThat("#elements", hasChild("#julkaisuvuosi_field"));
        verifyThat("#elements", hasChild("#sivumaara_field"));
        verifyThat("#elements", hasChild("#ISBN_field"));
    }
    
    @Test
    public void lisaaElokuvaButtoninKlikkaaminenRenderoiOikeatElementit() {
        clickOn("#lisaa_elokuva_btn");
        verifyThat("#elements", hasChild("#nimeke_field"));
        verifyThat("#elements", hasChild("#director_field"));
        verifyThat("#elements", hasChild("#julkaisuvuosi_field"));
        verifyThat("#elements", hasChild("#kesto_field"));
    }
    
    @Test
    public void lisaaURLButtoninKlikkaaminenRenderoiOikeatElementit() {
        clickOn("#lisaa_url_btn");
        verifyThat("#elements", hasChild("#otsikko_field"));
        verifyThat("#elements", hasChild("#URL_field"));
    }
}
