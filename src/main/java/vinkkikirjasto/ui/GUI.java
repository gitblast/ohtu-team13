package vinkkikirjasto.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Database.db;
import java.util.ArrayList;

public class GUI extends Application {
    ArrayList<String> lista;
    ArrayList<String> toinenLista;
    @Override
    public void init() throws Exception {
        String url = "jdbc:sqlite:lukuvinkit.db";
        db database = new db(url);
        database.createBook();
        database.createURL();
        lista = database.getAllBooks();
        toinenLista = database.getAllURLs();
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        System.out.println(lista.size());
        System.out.println(toinenLista.size());
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            System.out.println(toinenLista.get(i));
        }
        Label label = new Label("Hello world!");
        
        root.setCenter(label);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Vinkit");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
