package Domain;

public class Url {
    private String otsikko;
    private String url;
    private String releatedCourses;
    private String tyyppi;
    private String kommentti;
    
    public Url() {
    }
    
    public Url(String url) {
        this.url = url;
    }
    
    public Url(String otsikko, String url, String releatedCourses, String tyyppi, String kommentti) {
        this.otsikko = otsikko;
        this.url = url;
        this.releatedCourses = releatedCourses;
        this.tyyppi = tyyppi;
        this.kommentti = kommentti;
    }

    public Url(String otsikko, String url) {
        this.otsikko = otsikko;
        this.url = url;
    }

    /**
     * @return the otsikko
     */
    public String getOtsikko() {
        return otsikko;
    }

    /**
     * @param otsikko the otsikko to set
     */
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
     * @return the releatedCourses
     */
    public String getReleatedCourses() {
        return releatedCourses;
    }

    /**
     * @param releatedCourses the releatedCourses to set
     */
    public void setReleatedCourses(String releatedCourses) {
        this.releatedCourses = releatedCourses;
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
}
