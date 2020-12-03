package Scenes;

import java.util.List;
import java.util.stream.Collectors;

import Domain.Bookmark;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ListAllScene extends ListingScene {

    private ListBooksScene listBooksScene;
    private ListUrlsScene listUrlsScene;
    private ListMoviesScene listMoviesScene;

    public ListAllScene(ChooseAddScene chooseAddScene) {
        super(chooseAddScene, new String[]{"None", "Title"});
        this.listBooksScene = new ListBooksScene(chooseAddScene);
        this.listUrlsScene = new ListUrlsScene(chooseAddScene);
        this.listMoviesScene = new ListMoviesScene(chooseAddScene);
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
    protected List<Node> createBookmarkContent(Bookmark bookmark) {
        if (bookmark.getType().equals("Book")) {
            return listBooksScene.getBookmarkNode(bookmark);
        }
        if (bookmark.getType().equals("Url")) {
            return listUrlsScene.getBookmarkNode(bookmark);
        }
        if (bookmark.getType().equals("Movie")) {
            return listMoviesScene.getBookmarkNode(bookmark);
        }
        return null;
    }

    @Override
    protected HBox otsikot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setChangeListenerForChoiceBox(ChoiceBox<String> cb) {
        cb.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                handleFilterChange(this.getFilters()[new_val.intValue()],
                    this.getFilterField().getText());

                this.redrawBookmarkNodes();
            }
        );
    }
    
    private void handleFilterChange(
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
                    getFilteredByString(this.getAllBookmarks(),
                        filterFieldValue,
                        "Title")
                );

                break;
            }
        }
    }

    private List<Bookmark> getFilteredByString(
        List<Bookmark> allBookmarks,
        String value,
        String filterType) {
        return allBookmarks.stream().filter(bookmark -> {
            // System.out.println(bookmark.getId());
            if (filterType.equals("Title")) {
                return bookmark.getTitle() != null
                    ? bookmark.getTitle().toLowerCase().contains(
                        value.toLowerCase())
                    : false;
            }

            return false;
        })
            .collect(Collectors.toList());
    }
    
}
