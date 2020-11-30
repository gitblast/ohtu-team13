package Domain;

import java.util.ArrayList;

public interface Bookmark {

    public String getType();

    public String getTitle();

    public void setTitle(String newTitle);

    public ArrayList<String> getTagit();

    public void setTagit(ArrayList<String> tagit);

    public String getRelatedCourses();

    public void setRelatedCourses(String relatedCourses);

}
