package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Url extends Bookmark {

    private String otsikko;
    private String url;
    private String tyyppi;
    private String kommentti;

    public Url(String url) {
        this(null, url, null, null, null, null);
    }

    public Url(
        String otsikko, 
        String url,
        String relatedCourses,
        String tyyppi,
        String kommentti,
        ArrayList<String> tagit
    ) {
        super("Url", otsikko);
        this.otsikko = otsikko;
        this.url = url;
        this.tyyppi = tyyppi;
        this.kommentti = kommentti;
    }

    public Url(String otsikko, String url) {
        this(otsikko, url, null, null, null, null);
    }

    public Url(Integer id, String otsikko, String osoite) {
        this(otsikko, osoite);
        this.setId(id);
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
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
        if (!Objects.equals(super.getTitle(), super.getTitle())) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Url{" + "otsikko=" + super.getTitle() + ", url=" + url + '}';
    }
}
