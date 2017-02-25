package org.mytests.imdb.site.pages;

import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JSearch;
import org.mytests.imdb.entities.Film;
import org.mytests.imdb.site.custom.FilmsSearch;
import org.mytests.imdb.site.sections.FilmCard;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.commons.LinqUtils.first;

/**
 * Created by Roman_Iovlev on 2/13/2017.
 */
public class EditWatchListPage extends WebPage{

    @FindBy(css = ".list.ui-sortable")
    public Elements<FilmCard> filmCards;
    @JSearch(
        input = @FindBy(name = "add"),
        searchButton = @FindBy(xpath = "//button[.='Search']"),
        suggestions = @FindBy(xpath = "(//*[@class='results']//*[.='%s'])[1]")
    )
    public FilmsSearch filmsSearch;

    @JFindBy(xpath = "//*[contains(@class,'large') and .='Done']")
    public Button doneButton;

    public void deleteFilmByName(List<Film> films, String name) {
        filmCards.get(name).delete();
        Film filmToRemove = first(films, film -> film.title.equals(name));
        if (filmToRemove == null)
            throw new RuntimeException("No films to remove");
        films.remove(filmToRemove);
    }
}
