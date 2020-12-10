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
    protected void redrawBookmarkNodes() {
        this.nodes.getChildren().clear();

        if (this.shownBookmarks != null) {
            Bookmark prev = null;
            for (Bookmark bookmark : this.shownBookmarks) {
                if (prev == null || prev.getType() != bookmark.getType()) {
                    switch (bookmark.getType()) {
                        case "Book":
                            nodes.getChildren().add(this.listBooksScene.otsikot());
                            break;
                        case "Url":
                            nodes.getChildren().add(this.listUrlsScene.otsikot());
                            break;
                        case "Movie":
                            nodes.getChildren().add(this.listMoviesScene.otsikot());
                            break;
                    }
                }
                nodes.getChildren().add(createBookmarkNode(createBookmarkContent(bookmark)));
                prev = bookmark;
            }
        }
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

                int val = new_val.intValue();
                if (val < 0) {
                    val = 0;
                }

                String type = this.getTypeChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                String searchText = this.getFilterField().getText();

                String textFilterType = this.getFilters(type)[val];

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

                String newType = this.getTypes()[new_val.intValue()];
                this.setType(newType);

                String selectedFilter = this.getChoiceBox()
                    .getSelectionModel()
                    .getSelectedItem()
                    .toString();

                String searchText = this.getFilterField().getText();

                handleFilterChange(selectedFilter, searchText, newType);
                
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

        this.setShownBookmarks(getFilteredByString(
            this.getShownBookmarks(), textFilterValue, textFilterType
        ));        
    }

    private List<Bookmark> getFilteredByString(
        List<Bookmark> allBookmarks,
        String value,
        String filterType
    ) {
        List<Bookmark> bookmarks = allBookmarks;

        switch (this.getSelectedType()) {
            case "Book":
                bookmarks = this.listBooksScene.getFilteredByString(
                    allBookmarks, value, filterType
                );
                break;
            case "Url":
                bookmarks = this.listUrlsScene.getFilteredByString(
                    allBookmarks, value, filterType
                );
                break;
            case "Movie":
                bookmarks = this.listMoviesScene.getFilteredByString(
                    allBookmarks, value, filterType
                );
                break;
            default:
                bookmarks = allBookmarks.stream().filter(bookmark -> {
                    if (filterType.equals("Title")) {
                        return bookmark.getTitle() != null
                            ? bookmark.getTitle().toLowerCase().contains(
                                value.toLowerCase())
                            : false;
                    }
        
                    return false;
                }).collect(Collectors.toList());
        }

        return bookmarks;
    }
    
}
