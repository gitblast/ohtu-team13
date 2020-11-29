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
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public abstract class ListingScene {

    private Button returnButton;
    private VBox nodes;
    private List<Bookmark> allBookmarks;
    private List<Bookmark> shownBookmarks;
    private String[] filters;
    private TextField filterField;
    private ChoiceBox choiceBox;
    ChooseAddScene chooseAddScene;
    Label info;

    public ListingScene(ChooseAddScene chooseAddScene) {
        this(chooseAddScene, new String[]{"None"});
    }

    public ListingScene(ChooseAddScene chooseAddScene, String[] filters) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Return");
        this.info = new Label();
        this.filters = filters;
        this.filterField = createFilterField();
        this.choiceBox = null;
        this.allBookmarks = null;
        this.shownBookmarks = null;
    }

    protected abstract void setChangeListenerForFilterField(TextField tf);

    private TextField createFilterField() {
        TextField field = new TextField();

        field.setDisable(true);

        setChangeListenerForFilterField(field);

        field.setMaxWidth(200);

        return field;
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

        if (shownBookmarks != null) {

            shownBookmarks.forEach(bookmark -> {
                nodes.getChildren().add(
                    createBookmarkNode(createBookmarkContent(bookmark))
                );
            });
        }
    }

    protected void returnButtonFunction() {
        returnButton.setOnAction(e -> {
            try {
                info.setText("");
                this.filterField.setText("");
                this.filterField.setDisable(true);
                chooseAddScene.returnHere();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    protected abstract HBox otsikot();

    public Scene createScene(List<Bookmark> bookmarks) {

        returnButtonFunction();
        this.allBookmarks = bookmarks;
        this.shownBookmarks = bookmarks;
        nodes = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        VBox listingVBox = new VBox();
        listingVBox.setPadding(new Insets(30, 20, 20, 20));
        listingVBox.setSpacing(5);

        scrollPane.setContent(listingVBox);

        listingVBox.getChildren().addAll(info, returnButton);

        if (filters.length > 1) {
            listingVBox.getChildren().add(getFilterElements());
        }

        listingVBox.getChildren().addAll(otsikot(), nodes);

        Scene listingScene = new Scene(scrollPane, 600, 400);

        redrawBookmarkNodes();

        return listingScene;
    }

    protected abstract void setChangeListenerForChoiceBox(ChoiceBox cb);

    private HBox getFilterElements() {
        Label filterLabel = new Label("Filter:");

        choiceBox = new ChoiceBox(FXCollections.observableArrayList(
            filters)
        );

        choiceBox.getSelectionModel().selectFirst();

        setChangeListenerForChoiceBox(choiceBox);

        VBox cbWithLabel = new VBox(filterLabel, choiceBox);

        Label searchLabel = new Label("Search:");

        VBox textFielWithLabel = new VBox(searchLabel, filterField);

        HBox filterBox = new HBox(textFielWithLabel, cbWithLabel);

        return filterBox;
    }

    public String[] getFilters() {
        return filters;
    }

    public List<Bookmark> getAllBookmarks() {
        return allBookmarks;
    }

    public void setShownBookmarks(List<Bookmark> shownBookmarks) {
        this.shownBookmarks = shownBookmarks;
    }

    public TextField getFilterField() {
        return filterField;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

}
