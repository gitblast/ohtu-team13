package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Book implements Bookmark {

    private String kirjoittaja;
    private String nimeke;
    private Integer julkaisuvuosi;
    private Integer sivumaara;
    private String ISBN;
    private ArrayList<String> tagit;
    private String relatedCourses;

    public Book(String kirjoittaja, String nimeke, Integer julkaisuvuosi,
            Integer sivumaara, String ISBN, ArrayList<String> tagit,
            String relatedCourses) {
        this.kirjoittaja = kirjoittaja;
        this.nimeke = nimeke;
        this.julkaisuvuosi = julkaisuvuosi;
        this.sivumaara = sivumaara;
        this.ISBN = ISBN;
        this.tagit = tagit;
        this.relatedCourses = relatedCourses;
    }

    public Book(String kirjoittaja, String nimeke,
            Integer julkaisuvuosi, Integer sivumaara) {
        this(kirjoittaja, nimeke, julkaisuvuosi,
                sivumaara, null, new ArrayList<>(), null);
    }

    public Book(String kirjoittaja, String nimeke, Integer julkaisuvuosi,
            Integer sivumaara, String ISBN) {
        this(kirjoittaja, nimeke, julkaisuvuosi,
                sivumaara, ISBN, new ArrayList<>(), null);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.kirjoittaja, other.kirjoittaja)) {
            return false;
        }
        if (!Objects.equals(this.nimeke, other.nimeke)) {
            return false;
        }
        if (!Objects.equals(this.julkaisuvuosi, other.julkaisuvuosi)) {
            return false;
        }
        if (!Objects.equals(this.sivumaara, other.sivumaara)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "kirjoittaja=" + kirjoittaja + ", nimeke=" + nimeke
                + ", julkaisuvuosi=" + julkaisuvuosi + ", sivumaara="
                + sivumaara + ", ISBN=" + ISBN + '}';
    }

    @Override
    public ArrayList<String> getTagit() {
        return this.tagit;
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
