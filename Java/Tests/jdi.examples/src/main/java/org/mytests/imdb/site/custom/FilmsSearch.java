package org.mytests.imdb.site.custom;

import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import org.mytests.imdb.entities.Film;
import org.mytests.imdb.site.ImdbSite;

import java.util.List;

/**
 * Created by Roman_Iovlev on 2/13/2017.
 */
public class FilmsSearch extends Search {

    @Override
    protected void findAction(String text) {
        super.findAction(text);
        getSuggestionsList().getElement(text).click();
    }

    public void addMovies(List<Film> movies) {
        for (Film movie : movies)
            find(movie.title);
        ImdbSite.editWatchListPage.doneButton.click();
    }
}
