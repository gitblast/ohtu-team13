package vinkkikirjasto.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ExampleTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        GUI gui = new GUI();

        Application app = Application.class.cast(gui);
        app.start(stage);
    }

    @Test
    public void labelHasCorrectText() {
        verifyThat("#hello-world-label", hasText("Hello world!"));
    }
}
