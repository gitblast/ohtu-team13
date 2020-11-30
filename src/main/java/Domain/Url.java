package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Url implements Bookmark {

    private String otsikko;
    private String url;
    private String tyyppi;
    private String kommentti;
    private ArrayList<String> tagit;
    private String relatedCourses;

    public Url(String url) {
        this(null, url, null, null, null, null);
    }

    public Url(String otsikko, String url, String relatedCourses,
            String tyyppi, String kommentti, ArrayList<String> tagit) {
        this.otsikko = otsikko;
        this.url = url;
        this.tyyppi = tyyppi;
        this.kommentti = kommentti;
        this.tagit = tagit;
        this.relatedCourses = relatedCourses;
    }

    public Url(String otsikko, String url) {
        this(otsikko, url, null, null, null, null);
    }

    public String getType() {
        return "Url";
    }

    /**
     * @return the otsikko
     */
    public String getTitle() {
        return otsikko;
    }

    /**
     * @param otsikko the otsikko to set
     */
    public void setTitle(String otsikko) {
        this.otsikko = otsikko;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the tyyppi
     */
    public String getTyyppi() {
        return tyyppi;
    }

    /**
     * @param tyyppi the tyyppi to set
     */
    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    /**
     * @return the kommentti
     */
    public String getKommentti() {
        return kommentti;
    }

    /**
     * @param kommentti the kommentti to set
     */
    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Url other = (Url) obj;
        if (!Objects.equals(this.otsikko, other.otsikko)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Url{" + "otsikko=" + otsikko + ", url=" + url + '}';
    }

    @Override
    public ArrayList<String> getTagit() {
        return tagit;
    }

    @Override
    public void setTagit(ArrayList<String> tagit) {
        this.tagit = tagit;

    }

    @Override
    public String getRelatedCourses() {
        return this.relatedCourses;
    }

    @Override
    public void setRelatedCourses(String relatedCourses) {
        this.relatedCourses = relatedCourses;

    }

}
