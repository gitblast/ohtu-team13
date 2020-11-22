package Scenes;

import Domain.Url;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.List;

// Myöhemmin muutetaan ListScene-vanhemman perijäksi
// jotta koodi olisi vähemmän toisteista
public class ListUrlsScene {

    Button returnButton;
    private VBox urlNodes;
    private List<Url> urls;
    ChooseAddScene chooseAddScene;
    
    public ListUrlsScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Takaisin");
    }

    public Node createurlNode(Url url) {
        HBox box = new HBox(0);
        Label labelOtsikko  = new Label(url.getOtsikko());
        Label labelUrl  = new Label(url.getUrl());
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setSpacing(5);
        box.getChildren().addAll(labelOtsikko, labelUrl);

        return box;
    }

    public void redrawUrlsNodes() {
        urlNodes.getChildren().clear();     
        if (urls != null) {
            urls.forEach(url -> {
                urlNodes.getChildren().add(createurlNode(url));
            });  
        }
    } 

    public Scene createScene(List urls) {

        returnButton.setOnAction(e -> {
            try {
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        this.urls = urls;
        urlNodes = new VBox();
        
        VBox listUrlsVBox = new VBox();
        listUrlsVBox.setPadding(new Insets(70, 20, 20, 20));
        
        listUrlsVBox.getChildren().addAll(returnButton, urlNodes);
        
        Scene listUrlsScene = new Scene(listUrlsVBox, 600, 400);
        
        redrawUrlsNodes();
        
        return listUrlsScene;
    }

}