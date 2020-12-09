package vinkkikirjasto.ui;

import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Scenes.ChooseAddScene;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene defaultScene = new ChooseAddScene(primaryStage).createScene();
        primaryStage.setTitle("Vinkit");
        primaryStage.setScene(defaultScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
