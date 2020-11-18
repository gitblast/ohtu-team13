package Domain;

import java.util.ArrayList;

public class Book {
    private String kirjoittaja;
    private String otsikko;
    private String tyyppi;
    private String ISBN;
    private ArrayList<String> tagit;
    private String releatedCourses;
    
    public Book() {
    }
    
    public Book(String kirjoittaja, String otsikko) {
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
    }
    
    public Book(String kirjoittaja, String otsikko, String tyyppi, String ISBN, ArrayList<String> tagit, String releatedCourses) {
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
        this.tyyppi = tyyppi;
        this.ISBN = ISBN;
        this.tagit = tagit;
        this.releatedCourses = releatedCourses;
    }

    /**
     * @return the kirjoittaja
     */
    public String getKirjoittaja() {
        return kirjoittaja;
    }

    /**
     * @param kirjoittaja the kirjoittaja to set
     */
    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
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
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the tagit
     */
    public ArrayList<String> getTagit() {
        return tagit;
    }

    /**
     * @param tagit the tagit to set
     */
    public void setTagit(ArrayList<String> tagit) {
        this.tagit = tagit;
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
}
