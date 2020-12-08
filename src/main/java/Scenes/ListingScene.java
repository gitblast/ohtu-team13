package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import java.util.List;
import java.util.stream.Collectors;

import Domain.Bookmark;
import Service.VinkkiService;
import javafx.beans.value.ObservableValue;
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
    private ChoiceBox<String> searchChoice;

    private ChoiceBox<String> typeChoice;
    private String[] types;

    VinkkiService vinkkiService;
    ChooseAddScene chooseAddScene;
    Label info;

    public ListingScene(ChooseAddScene chooseAddScene) {
        this(chooseAddScene, new String[]{"None"}, new String[]{"None"});
    }

    public ListingScene(ChooseAddScene chooseAddScene, String[] filters) {
        this(chooseAddScene, filters, new String[]{"None"});
    }

    public ListingScene(
        ChooseAddScene chooseAddScene,
        String[] filters,
        String[] types
    ) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Return");
        this.info = new Label();
        this.filters = filters;
        this.types = types;
        this.filterField = createFilterField();
        this.searchChoice = null;
        this.allBookmarks = null;
        this.shownBookmarks = null;
        this.vinkkiService = chooseAddScene.vinkkiService;
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
        box.setPadding(new Insets(0));
        box.setSpacing(0);
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

        if (filters.length > 1 || types.length > 1) {
            listingVBox.getChildren().add(getFilterElements());
        }

        listingVBox.getChildren().addAll(otsikot(), nodes);

        Scene listingScene = new Scene(scrollPane, 700, 400);

        redrawBookmarkNodes();

        return listingScene;
    }

    protected abstract void setChangeListenerForChoiceBox(ChoiceBox<String> cb);

    protected void setChangeListenerForTypeChoice(ChoiceBox<String> tc) {
        tc.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                handleTypeChange(this.types[new_val.intValue()]);
        
                this.redrawBookmarkNodes();
            }
        );
        
    }

    protected void handleTypeChange(String typeName) {
        if (typeName == "All") {
            this.setShownBookmarks(this.getAllBookmarks());
        } else {
            this.setShownBookmarks(
                this.getAllBookmarks().stream().filter(b -> {
                    return b.getType().equals(typeName);
                }).collect(Collectors.toList())
            );
        }
    }

    private HBox getFilterElements() {
        Label filterLabel = new Label("Filter:");
        Label typeLabel = new Label("Type:");

        typeChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
            types
        ));

        typeChoice.getSelectionModel().selectFirst();
        setChangeListenerForTypeChoice(typeChoice);

        searchChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
            filters)
        );

        searchChoice.getSelectionModel().selectFirst();
        setChangeListenerForChoiceBox(searchChoice);


        VBox scWithLabel = new VBox(filterLabel, searchChoice);

        VBox tcWithLabel = new VBox(typeLabel, typeChoice);

        Label searchLabel = new Label("Search:");

        VBox textFielWithLabel = new VBox(searchLabel, filterField);

        HBox filterBox = new HBox();
        if (types.length > 1) {
            filterBox.getChildren().add(tcWithLabel);
        }
        filterBox.getChildren().add(textFielWithLabel);
        if (filters.length > 1) {
            filterBox.getChildren().add(scWithLabel);
        }

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

    public ChoiceBox<String> getChoiceBox() {
        return searchChoice;
    }

    public void setChoiceBox(ChoiceBox<String> searchChoice) {
        this.searchChoice = searchChoice;
    }

    public void setAllBookmarks(List<Bookmark> allBookmarks) {
        this.allBookmarks = allBookmarks;
    }

    public List<Bookmark> getShownBookmarks() {
        return shownBookmarks;
    }

}
