package Domain;

public abstract class Bookmark {
    private String type;
    private String title;

    public Bookmark(
        String type,
        String title
    ) {
        this.type = type;
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
