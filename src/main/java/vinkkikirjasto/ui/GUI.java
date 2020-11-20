package vinkkikirjasto.ui;

import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Service.VinkkiService;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Domain.Url;
import Domain.Book;
import java.util.ArrayList;
import Scenes.ChooseAddScene;

public class GUI extends Application {

    ArrayList<Book> lista;
    ArrayList<Url> toinenLista;

    ChooseAddScene chooseAddScene;

    @Override
    public void init() throws Exception {
        VinkkiService vinkkiService = new VinkkiService(new SqlDbBookDao(),
                                                        new SqlDbUrlDao());
        lista = vinkkiService.listBooks();
        toinenLista = vinkkiService.listURLs();
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        chooseAddScene = new ChooseAddScene(primaryStage);
        Scene defaultScene = chooseAddScene.createScene();

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
