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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.Node;

import java.util.List;
import javafx.scene.control.ScrollPane;

// Myöhemmin muutetaan ListScene-vanhemman perijäksi
// jotta koodi olisi vähemmän toisteista
public class ListUrlsScene {

    Button returnButton;
    private VBox urlNodes;
    private List<Url> urls;
    ChooseAddScene chooseAddScene;
    Label copiedLabel;
    
    public ListUrlsScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Takaisin");
        copiedLabel = new Label();
    }

    public Node createurlNode(Url url) {
        HBox box = new HBox(0);
        Label labelOtsikko = new Label(url.getOtsikko() + ": ");
        Label labelUrl = new Label(url.getUrl());

        // Leikepöydälle kopioimisen toiminnallisuus
        Button copyURL = new Button("Kopioi");

        copyURL.setOnAction(e -> {
            String copyText = labelUrl.getText();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(copyText);
            clipboard.setContent(content);
            copiedLabel.setText("URL kopioitu leikepöydälle!");
        });

        box.setPadding(new Insets(5, 5, 5, 5));
        box.setSpacing(10);
        box.getChildren().addAll(labelOtsikko, labelUrl, copyURL);

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

    public Scene createScene(List<Url> urls) {

        returnButton.setOnAction(e -> {
            try {
                copiedLabel.setText("");
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        this.urls = urls;
        urlNodes = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        VBox listUrlsVBox = new VBox();
        scrollPane.setContent(listUrlsVBox);
        listUrlsVBox.setPadding(new Insets(10, 20, 20, 20));
        listUrlsVBox.setSpacing(5);
        
        listUrlsVBox.getChildren().addAll(copiedLabel, returnButton, urlNodes);
        
        Scene listUrlsScene = new Scene(scrollPane, 600, 400);
        
        redrawUrlsNodes();
        
        return listUrlsScene;
    }

}