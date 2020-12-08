package Scenes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        super(chooseAddScene,
            new HashMap<String, String[]>(Map.of(
                    "All", new String[]{"None", "Title"},
                    "Book", new String[]{"None", "Author", "Title", "ISBN"},
                    "Url", new String[]{"None", "Title"},
                    "Movie", new String[]{"None", "Director", "Title"}
                )
            ),
            new String[]{"All", "Book", "Url", "Movie"}
        );
        
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

                String type = this.getTypeChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                handleFilterChange(selectedFilter, new_val, type);

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
        return new HBox();
    }

    @Override
    protected void setChangeListenerForChoiceBox(ChoiceBox<String> cb) {
        cb.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                String type = this.getTypeChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                String searchText = this.getFilterField().getText();

                String textFilterType = this.getFilters()[new_val.intValue()];

                handleFilterChange(textFilterType, searchText, type);

                this.redrawBookmarkNodes();
            }
        );
    }

    @Override
    protected void setChangeListenerForTypeChoice(ChoiceBox<String> tc) {
        tc.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov,
                Number old_val,
                Number new_val) -> {

                String selectedFilter = this.getChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                String searchText = this.getFilterField().getText();

                handleFilterChange(selectedFilter, searchText,
                    this.getTypes()[new_val.intValue()]);
                
                this.redrawBookmarkNodes();
            }
        );
    }
    
    @Override
    protected void handleFilterChange(
        String textFilterType,
        String textFilterValue,
        String typeFilterValue
    ) {
        this.getFilterField().setDisable(textFilterType.equals("None"));

        if (typeFilterValue == "All") {
            this.setShownBookmarks(this.getAllBookmarks());
        } else {
            this.setShownBookmarks(
                this.getAllBookmarks().stream().filter(b -> {
                    return b.getType().equals(typeFilterValue);
                }).collect(Collectors.toList())
            );
        }

        if (textFilterValue.equals("")) {
            return;
        }

        switch (textFilterType) {
            case "None": {
                break;
            }
            case "Title": {
                this.setShownBookmarks(
                    getFilteredByString(this.getShownBookmarks(),
                        textFilterValue,
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
