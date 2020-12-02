package Domain;

import java.util.Objects;

public class Book extends Bookmark {

    private String author;
    private Integer releaseYear;
    private Integer pageCount;
    private String ISBN;

    public Book(
        String author,
        String title,
        Integer releaseYear,
        Integer pageCount,
        String ISBN
    ) {
        super("Book", title);

        this.author = author;
        this.releaseYear = releaseYear;
        this.pageCount = pageCount;
        this.ISBN = ISBN;
    }

    public Book(
        String author,
        String title,
        Integer releaseYear,
        Integer pageCount
    ) {
        this(author, title, releaseYear, pageCount, null);
    }

    public String getKirjoittaja() {
        return this.author;
    }

    public void setKirjoittaja(String author) {
        this.author = author;
    }

    public Integer getJulkaisuvuosi() {
        return releaseYear;
    }

    public void setJulkaisuvuosi(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getSivumaara() {
        return pageCount;
    }

    public void setSivumaara(Integer pageCount) {
        this.pageCount = pageCount;
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
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(super.getTitle(), super.getTitle())) {
            return false;
        }
        if (!Objects.equals(this.releaseYear, other.releaseYear)) {
            return false;
        }
        if (!Objects.equals(this.pageCount, other.pageCount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "kirjoittaja=" + this.author + ", nimeke="
            + super.getTitle() + ", julkaisuvuosi=" + releaseYear
            + ", sivumaara=" + pageCount + ", ISBN=" + ISBN + '}';
    }
}
