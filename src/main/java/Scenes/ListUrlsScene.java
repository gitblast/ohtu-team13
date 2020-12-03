package Scenes;

import Domain.Bookmark;
import Domain.Url;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class ListUrlsScene extends ListingScene {

    private EditURLScene editURLScene;

    public ListUrlsScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene, new String[]{"None", "Title"});
    }

    protected List<Bookmark> getFilteredByString(
        List<Bookmark> allUrls,
        String value,
        String filterType
    ) {
        return allUrls.stream().filter(url -> {
            Url u = (Url) url;

            if (filterType.equals("Title")) {
                return u.getTitle() != null
                    ? u.getTitle().toLowerCase()
                        .contains(value.toLowerCase())
                    : false;
            }

            return false;
        }).collect(Collectors.toList());
    }

    @Override
    protected void setChangeListenerForFilterField(TextField tf) {
        tf.textProperty().addListener(
            (ObservableValue<? extends String> ov,
                String old_val,
                String new_val) -> {

                String selectedFilter = this.getChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                handleFilterChange(selectedFilter, new_val);

                this.redrawBookmarkNodes();
            }
        );
    }

    @Override
    protected void setChangeListenerForChoiceBox(ChoiceBox<String> cb) {
        cb.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                this.handleFilterChange(this.getFilters()[new_val.intValue()],
                    this.getFilterField().getText());

                this.redrawBookmarkNodes();
            }
        );
    }

    protected void handleFilterChange(
        String filterType,
        String filterFieldValue
    ) {
        this.getFilterField().setDisable(filterType.equals("None"));

        if (filterFieldValue.equals("")) {
            this.setShownBookmarks(this.getAllBookmarks());
            return;
        }

        switch (filterType) {
            case "None": {
                this.setShownBookmarks(this.getAllBookmarks());

                break;
            }
            case "Title": {
                this.setShownBookmarks(
                    this.getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue, "Title")
                );

                break;
            }
        }
    }

    public List<Node> getBookmarkNode(Bookmark url) {
        return createBookmarkContent(url);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark url) {
        List<Node> nodes = new ArrayList<>();

        Label labelOtsikko = new Label(((Url) url).getTitle() + ": ");
        Label labelUrl = new Label(((Url) url).getUrl());

        // Leikepöydälle kopioimisen toiminnallisuus
        Button copyURL = new Button("Copy");

        copyURL.setOnAction(e -> {
            String copyText = labelUrl.getText();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(copyText);
            clipboard.setContent(content);
            info.setText("URL copied to clipboard!");
        });

        Button editButton = new Button("Edit");
        editButtonFunction(editButton, (Url) url);

        nodes.add(labelOtsikko);
        nodes.add(labelUrl);
        nodes.add(copyURL);
        nodes.add(editButton);

        return nodes;
    }

    private void editButtonFunction(
        Button button, Url url
    ) {
        button.setOnAction(e -> {
            try {
                editURLScene = new EditURLScene(chooseAddScene, url);
                chooseAddScene.setScene(editURLScene.createScene());
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        });
    }

    @Override
    protected HBox otsikot() {
        HBox otsikot = new HBox();
        otsikot.setSpacing(5);

        Label otsikko = new Label("Header");
        Label url = new Label("URL");

        otsikot.getChildren().addAll(otsikko, url);

        return otsikot;
    }

}
