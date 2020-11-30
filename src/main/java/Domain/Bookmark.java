package Domain;

import java.util.ArrayList;

public abstract class Bookmark {
    private String type;
    private String title;
    private String relatedCourses;
    private ArrayList<String> tags;

    public Bookmark(
        String type,
        String title,
        String relatedCourses,
        ArrayList<String> tags
    ) {
        this.type = type;
        this.title = title;
        this.relatedCourses = relatedCourses;
        this.tags = tags;
    }

    public Bookmark(String type, String title, String relatedCourses) {
        this(type, title, relatedCourses, new ArrayList<>());
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

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<String> newTags) {
        this.tags = newTags;
    }

    public String getRelatedCourses() {
        return this.relatedCourses;
    }

    public void setRelatedCourses(String newRelatedCourses) {
        this.relatedCourses = newRelatedCourses;
    }
}
