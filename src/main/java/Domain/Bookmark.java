package Domain;

import java.util.ArrayList;

public abstract class Bookmark {

    private String releatedCourses;
    private ArrayList<String> tagit;

    public Bookmark(String relatedCourses, ArrayList<String> tagit) {
        this.releatedCourses = relatedCourses;
        this.tagit = tagit;
    }

    public ArrayList<String> getTagit() {
        return tagit;
    }

    public void setTagit(ArrayList<String> tagit) {
        this.tagit = tagit;
    }

    public String getReleatedCourses() {
        return releatedCourses;
    }

    public void setReleatedCourses(String relatedCourses) {
        this.releatedCourses = relatedCourses;
    }

}
