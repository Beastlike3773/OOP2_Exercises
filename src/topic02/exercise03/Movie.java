package topic02.exercise03;

public class Movie {

    private String id;
    private String title;
    private String country;
    private int year;

    /**
     * Constructs a Movie object.
     */
    public Movie() {
        id = "xx";
        title = "No name";
        country = "unknown";
        year = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return this.id + " (" + this.title + " ; " + this.country + " ; " + this.year + ")";
    }
}