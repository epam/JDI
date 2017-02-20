package org.mytests.entities;

/**
 * Created by Roman_Iovlev on 2/17/2017.
 */
public class Film {
    public String title;
    public String year;
    public String type;

    public Film() {}
    public Film(String title, String year, String type) {
        this.title = title;
        this.year = year;
        this.type = type;
    }
    @Override
    public boolean equals(Object obj) {
        Film film = (Film) obj;
        return  title.equals(film.title) &&
                year.equals(film.year) &&
                type.equals(film.type);
    }
    @Override
    public int hashCode() {
        return (title + year + type).hashCode();
    }
}
