package Domain;

import java.util.ArrayList;
import java.util.Objects;

public class Book extends Bookmark {

    private String kirjoittaja;
    private Integer julkaisuvuosi;
    private Integer sivumaara;
    private String ISBN;

    public Book(
        String kirjoittaja,
        String nimeke,
        Integer julkaisuvuosi,
        Integer sivumaara,
        String ISBN,
        ArrayList<String> tagit,
        String relatedCourses
    ) {
        super("Book", nimeke, relatedCourses);

        this.kirjoittaja = kirjoittaja;
        this.julkaisuvuosi = julkaisuvuosi;
        this.sivumaara = sivumaara;
        this.ISBN = ISBN;
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
        if (!Objects.equals(super.getTitle(), super.getTitle())) {
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
        return "Book{" + "kirjoittaja=" + kirjoittaja + ", nimeke="
                + super.getTitle() + ", julkaisuvuosi=" + julkaisuvuosi
                + ", sivumaara=" + sivumaara + ", ISBN=" + ISBN + '}';
    }
}
