package Scenes;

import Domain.Bookmark;
import Domain.Url;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ListUrlsScene extends ListingScene {

    public ListUrlsScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene);
    }

    @Override
    protected List<Node> createBookmarkContent(Bookmark url) {
        List<Node> nodes = new ArrayList<>();

        Label labelOtsikko = new Label(((Url)url).getOtsikko() + ": ");
        Label labelUrl = new Label(((Url)url).getUrl());

        // Leikepöydälle kopioimisen toiminnallisuus
        Button copyURL = new Button("Kopioi");

        copyURL.setOnAction(e -> {
            String copyText = labelUrl.getText();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(copyText);
            clipboard.setContent(content);
            info.setText("URL kopioitu leikepöydälle!");
        });

        nodes.add(labelOtsikko);
        nodes.add(labelUrl);
        nodes.add(copyURL);

        return nodes;
    }

    @Override
    protected HBox otsikot() {
        HBox otsikot = new HBox();
        otsikot.setSpacing(5);

        Label otsikko = new Label("Otsikko");
        Label url = new Label("URL");
        otsikot.getChildren().addAll(otsikko, url);

        return otsikot;
    }

}