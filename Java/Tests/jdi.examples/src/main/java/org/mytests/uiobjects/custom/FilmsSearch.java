package org.mytests.uiobjects.custom;

import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import org.mytests.entities.Film;

import java.util.List;

import static org.mytests.uiobjects.ImdbSite.editWatchListPage;

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
        editWatchListPage.doneButton.click();
    }
}
