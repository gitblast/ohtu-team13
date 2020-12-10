package Domain;

import java.util.Objects;

public class Url extends Bookmark {

    private String url;

    public Url(
        String otsikko, 
        String url
    ) {
        super("Url", otsikko);
        this.url = url;
    }

    public Url(Integer id, String otsikko, String url) {
        this(otsikko, url);
        this.setId(id);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!Objects.equals(this.getTitle(), other.getTitle())) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }
}
