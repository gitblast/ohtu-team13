package Scenes;

import Service.VinkkiService;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public abstract class CreateBookmarkScene {

    Label title;
    Button returnButton;
    List<TextField> fields;
    Label errorMessage;
    Button submitButton;
    ChooseAddScene chooseAddScene;
    VinkkiService vinkkiService;

    public CreateBookmarkScene(ChooseAddScene chooseAddScene) {
        this.chooseAddScene = chooseAddScene;

        this.title = new Label();
        title.setId("title_label");

        this.returnButton = new Button("Return");
        returnButton.setId("returnButton_btn");

        this.fields = new ArrayList<>();

        this.errorMessage = new Label();
        this.errorMessage.setId("errorMessage_label");

        this.submitButton = new Button();
        this.submitButton.setId("submitButton_btn");

        this.vinkkiService = chooseAddScene.vinkkiService;
    }

    public Scene createScene() {
        VBox vbox = new VBox();
        vbox.setId("elements");
        vbox.setPadding(new Insets(80, 50, 50, 100));
        vbox.setSpacing(5);

        vbox.getChildren().add(title);
        vbox.getChildren().add(returnButton);

        setBookmarkInputFields();

        for (TextField field : fields) {
            vbox.getChildren().add(field);
        }

        if (setDeleteButton() != null) {
            vbox.getChildren().add(setDeleteButton());
        }

        vbox.getChildren().add(errorMessage);
        vbox.getChildren().add(submitButton);

        returnButtonFunction();
        submitButtonFunction();

        Scene creationScene = new Scene(vbox, 600, 400);

        return creationScene;
    }

    protected abstract void setBookmarkInputFields();

    protected abstract boolean bookmarkCreation();

    protected abstract Button setDeleteButton();

    protected void submitButtonFunction() {
        submitButton.setOnAction(e -> {
            boolean added = bookmarkCreation();
            if (added) {
                for (TextField field : fields) {
                    field.setText("");
                }
                errorMessage.setText("");
                destination(destinationIndex());
            } else {
                if (errorMessage.getText().equals("")) {
                    errorMessage.setText(
                        "Error adding bookmark to database");
                }
            }
        });
    }

    protected void destination(int sceneIndex) {
        try {
            if (sceneIndex == 0) {
                chooseAddScene.returnHere();
            } else {
                chooseAddScene.listAllScene();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // Always override in Edit-scenes
    protected int destinationIndex() {
        return 0;
    }

    protected void returnButtonFunction() {
        returnButton.setOnAction(e -> {
            destination(destinationIndex());
        });
    }

    protected int convertToInteger(String s) {
        s = s.trim();
        int value;
        if (s.length() == 0) {
            value = -9999;
        } else {
            try {
                value = Integer.valueOf(s);
            } catch (NumberFormatException e) {
                value = -9999;
            }
        }
        return value;
    }

    protected String checkString(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return null;
        }
        return s;
    }

}
