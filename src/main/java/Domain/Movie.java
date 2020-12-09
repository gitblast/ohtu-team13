package Domain;

public class Movie extends Bookmark {

    private String director;
    private Integer releaseYear;
    private Integer length;

    public Movie(
        String title,
        String director,
        Integer releaseYear,
        Integer length
    ) {
        super("Movie", title);

        this.setDirector(director);
        this.setReleaseYear(releaseYear);
        this.setLength(length);
    }

    public Movie(Integer id, String nimeke, String ohjaaja,
            Integer julkaisuvuosi, Integer kesto) {
        this(nimeke, ohjaaja, julkaisuvuosi, kesto);
        this.setId(id);
    }
    
    public Movie(String title) {
        super("Movie", title);
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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
        Movie other = (Movie) obj;
        if (!director.equals(other.director)) {
            return false;
        }
        if (!length.equals(other.length)) {
            return false;
        }
        if (!releaseYear.equals(other.releaseYear)) {
            return false;
        }
        if (!super.getTitle().equals(other.getTitle())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Movie [director=" + director
            + ", nimeke=" + super.getTitle()
            + ", length=" + length
            + ", releaseYear=" + releaseYear + "]";
    }

}
