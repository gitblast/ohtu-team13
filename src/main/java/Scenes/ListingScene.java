package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.List;

import Domain.Bookmark;
import Service.VinkkiService;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public abstract class ListingScene {

    private Button returnButton;
    private VBox nodes = null;
    private List<Bookmark> allBookmarks = null;
    private List<Bookmark> shownBookmarks = null;
    private TextField filterField;
    private ChoiceBox<String> searchChoice = null;
    private ChoiceBox<String> typeChoice = null;
    private String[] types;
    private HashMap<String, String[]> filters;

    VinkkiService vinkkiService;
    ChooseAddScene chooseAddScene;
    Label info;

    public ListingScene(
        ChooseAddScene chooseAddScene,
        HashMap<String, String[]> filters
    ) {
        this.chooseAddScene = chooseAddScene;
        this.returnButton = new Button("Return");
        this.returnButton.setId("returnButton_btn");
        this.info = new Label();
        this.filters = filters;
        this.filterField = createFilterField();
        this.filterField.setId("filterField");
        this.vinkkiService = chooseAddScene.vinkkiService;

        this.types = new String[1];
        this.types = (String[]) filters.keySet().toArray(this.types);
    }

    public ListingScene(
        ChooseAddScene chooseAddScene,
        HashMap<String, String[]> filters,
        String[] types
    ) {
        this(chooseAddScene, filters);
        this.types = types;
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
        listingVBox.setId("listing_elements");
        listingVBox.setPadding(new Insets(30, 20, 20, 20));
        listingVBox.setSpacing(5);

        scrollPane.setContent(listingVBox);

        listingVBox.getChildren().addAll(info, returnButton);

        if (this.getFilters().length > 1 || types.length > 1) {
            listingVBox.getChildren().add(getFilterElements());
        }

        listingVBox.getChildren().addAll(otsikot(), nodes);

        Scene listingScene = new Scene(scrollPane, 700, 400);

        redrawBookmarkNodes();

        return listingScene;
    }

    protected abstract void setChangeListenerForChoiceBox(ChoiceBox<String> cb);

    protected void setChangeListenerForTypeChoice(ChoiceBox<String> tc) {
        
    }

    protected void handleFilterChange(
        String textFilterType,
        String textFilterValue
    ) {
        this.handleFilterChange(textFilterType, textFilterValue, "");
    }

    protected abstract void handleFilterChange(
        String textFilterType,
        String textFilterValue,
        String typeFilterValue
    );

    private HBox getFilterElements() {
        Label filterLabel = new Label("Filter:");
        Label typeLabel = new Label("Type:");

        if (this.typeChoice == null) {
            this.typeChoice = new ChoiceBox<String>(
                FXCollections.observableArrayList(types)
            );

            typeChoice.getSelectionModel().selectFirst();
            setChangeListenerForTypeChoice(typeChoice);
        } else {
            typeChoice.getSelectionModel().selectFirst();
        }

        if (this.searchChoice == null) {
            this.searchChoice = new ChoiceBox<String>(
                FXCollections.observableArrayList(this.getFilters())
            );

            this.searchChoice.getSelectionModel().selectFirst();
            setChangeListenerForChoiceBox(this.searchChoice);
        } else {
            this.setType(this.types[0]);
        }

        VBox scWithLabel = new VBox(filterLabel, this.searchChoice);

        VBox tcWithLabel = new VBox(typeLabel, typeChoice);

        Label searchLabel = new Label("Search:");

        VBox textFielWithLabel = new VBox(searchLabel, filterField);

        HBox filterBox = new HBox();
        if (types.length > 1) {
            filterBox.getChildren().add(tcWithLabel);
        }
        filterBox.getChildren().add(textFielWithLabel);
        if (this.getFilters().length > 1) {
            filterBox.getChildren().add(scWithLabel);
        }

        return filterBox;
    }

    public String[] getFilters() {
        return (String[]) this.filters.values().toArray()[0];
    }

    public String[] getFilters(String type) {
        return this.filters.get(type);
    }

    public String[] getTypes() {
        return this.types;
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

    public ChoiceBox<String> getTypeChoiceBox() {
        return this.typeChoice;
    }

    public void setChoiceBox(ChoiceBox<String> searchChoice) {
        this.setChangeListenerForChoiceBox(searchChoice);
        this.searchChoice = searchChoice;
    }

    public void setAllBookmarks(List<Bookmark> allBookmarks) {
        this.allBookmarks = allBookmarks;
    }

    public void setType(String newType) {
        this.filterField.clear();
        this.searchChoice.setItems(FXCollections.observableArrayList(
            this.filters.get(newType)
        ));
        this.searchChoice.getSelectionModel().selectFirst();
    }

    public List<Bookmark> getShownBookmarks() {
        return shownBookmarks;
    }

    public String getSelectedType() {
        return this.typeChoice.getSelectionModel().getSelectedItem().toString();
    }
}
