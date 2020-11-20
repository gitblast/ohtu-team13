package Domain;

import java.util.ArrayList;

public class Book {
    private String kirjoittaja;
    private String nimeke;
    private Integer julkaisuvuosi;
    private Integer sivumaara;
    private String ISBN;
    private ArrayList<String> tagit;
    private String releatedCourses;
    
    public Book() {
    }

    public Book(String kirjoittaja, String nimeke, Integer julkaisuvuosi, Integer sivumaara, String ISBN, ArrayList<String> tagit, String releatedCourses) {
        this.kirjoittaja = kirjoittaja;
        this.nimeke = nimeke;
        this.julkaisuvuosi = julkaisuvuosi;
        this.sivumaara = sivumaara;
        this.ISBN = ISBN;
        this.tagit = tagit;
        this.releatedCourses = releatedCourses;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public String getNimeke() {
        return nimeke;
    }

    public void setNimeke(String nimeke) {
        this.nimeke = nimeke;
    }

    public Integer getJulkaisuvuosi() {
        return julkaisuvuosi;
    }

    public void setJulkaisuvuosi(Integer julkaisuvuosi) {
        this.julkaisuvuosi = julkaisuvuosi;
    }

    public Integer getSivumaara() {
        return sivumaara;
    }

    public void setSivumaara(Integer sivumaara) {
        this.sivumaara = sivumaara;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public void setReleatedCourses(String releatedCourses) {
        this.releatedCourses = releatedCourses;
    }
   
}