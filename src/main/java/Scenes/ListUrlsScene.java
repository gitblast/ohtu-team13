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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUrlsScene extends ListingScene {

    private EditURLScene editURLScene;

    // UI Style elements
    private String cssLayoutBorder01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: white;\n";

    private String cssLayoutBorderTitle01 = "-fx-border-color: gray;\n"
        + "-fx-border-insets: 0;\n"
        + "-fx-border-width: 1;\n"
        + "-fx-border-style: solid;\n"
        + "-fx-background-color: lightblue;\n";

    public ListUrlsScene(ChooseAddScene chooseAddScene) {
        super(
            chooseAddScene,
            new HashMap<String, String[]>(Map.of(
                "Url", new String[]{"None", "Title"}
            ))
        );
    }

    protected List<Bookmark> getFilteredByString(
        List<Bookmark> allUrls,
        String value,
        String textFilterType
    ) {
        return allUrls.stream().filter(url -> {
            Url u = (Url) url;

            if (textFilterType.equals("Title")) {
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
        String textFilterType,
        String textFilterValue,
        String typeFilterValue
    ) {
        this.getFilterField().setDisable(textFilterType.equals("None"));

        if (textFilterValue.equals("")) {
            this.setShownBookmarks(this.getAllBookmarks());
            return;
        }

        switch (textFilterType) {
            case "None": {
                this.setShownBookmarks(this.getAllBookmarks());

                break;
            }
            case "Title": {
                this.setShownBookmarks(
                    this.getFilteredByString(this.getAllBookmarks(),
                        textFilterValue, "Title")
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
        labelOtsikko.setStyle(cssLayoutBorder01);
        labelOtsikko.setMaxHeight(26);
        labelOtsikko.setMinHeight(26);
        labelOtsikko.setMaxWidth(150);
        labelOtsikko.setMinWidth(150);

        Label labelUrl = new Label(((Url) url).getUrl());
        labelUrl.setStyle(cssLayoutBorder01);
        labelUrl.setMaxHeight(26);
        labelUrl.setMinHeight(26);
        labelUrl.setMaxWidth(350);
        labelUrl.setMinWidth(350);

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
        otsikot.setSpacing(0);

        Label otsikko = new Label("Header");
        otsikko.setStyle(cssLayoutBorderTitle01);
        otsikko.setMaxHeight(26);
        otsikko.setMinHeight(26);
        otsikko.setMaxWidth(150);
        otsikko.setMinWidth(150);

        Label url = new Label("URL");
        url.setStyle(cssLayoutBorderTitle01);
        url.setMaxHeight(26);
        url.setMinHeight(26);
        url.setMaxWidth(350);
        url.setMinWidth(350);

        otsikot.getChildren().addAll(otsikko, url);

        return otsikot;
    }

}
