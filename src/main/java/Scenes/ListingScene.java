package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import java.util.List;

import Domain.Bookmark;
import javafx.scene.control.ScrollPane;


public abstract class ListingScene {

    private Button returnButton;
    private VBox nodes;
    private List<Bookmark> bookmarks;
    ChooseAddScene chooseAddScene;
    Label info;
    
    public ListingScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Return");
        this.info = new Label();
    }

    protected abstract List<Node> createBookmarkContent(Bookmark bookmark);

    protected Node createBookmarkNode(List<Node> attributes) {
        HBox box = new HBox();
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setSpacing(10);
        box.getChildren().addAll(attributes);
        return box;
    }

    protected void redrawBookmarkNodes() {
        nodes.getChildren().clear();     
        if (bookmarks != null) {
            bookmarks.forEach(bookmark -> {
                nodes.getChildren().add(createBookmarkNode(createBookmarkContent(bookmark)));
            });  
        }
    }

    protected void returnButtonFunction() {
        returnButton.setOnAction(e -> {
            try {
                info.setText("");
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    protected abstract HBox otsikot();

    public Scene createScene(List<Bookmark> bookmarks) {

        returnButtonFunction();
        this.bookmarks = bookmarks;
        nodes = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        VBox listingVBox = new VBox();
        listingVBox.setPadding(new Insets(30, 20, 20, 20));
        listingVBox.setSpacing(5);

        scrollPane.setContent(listingVBox);
        
        listingVBox.getChildren().addAll(info, returnButton, otsikot(), nodes);
        
        Scene listingScene = new Scene(scrollPane, 600, 400);
        
        redrawBookmarkNodes();
        
        return listingScene;
    }

}