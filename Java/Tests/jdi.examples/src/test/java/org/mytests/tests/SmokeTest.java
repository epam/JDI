package org.mytests.tests;

import com.epam.web.matcher.testng.Assert;
import org.mytests.entities.Film;
import org.mytests.entities.User;
import org.mytests.uiobjects.sections.FilmCard;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mytests.entities.User.DefaultUser;
import static org.mytests.enums.FilmsTableHeaders.Rating;
import static org.mytests.enums.FilmsViewTypes.compact;
import static org.mytests.enums.Genres.drama;
import static org.mytests.enums.UserOptions.YourLists;
import static org.mytests.uiobjects.ImdbSite.*;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class SmokeTest extends InitTests {

    @BeforeMethod
    public void before(Method method) {
        homePage.open();
    }
    @AfterMethod
    public void after() {
        for (FilmCard card : editWatchListPage.filmCards)
            card.delete();
    }

    @DataProvider
    public static Object[][] usersAndMovies() {
        return new Object[][] {
            {DefaultUser, asList(
                new Film("The Big Bang Theory", "2007", "TV Series"),
                new Film("Black Mirror", "2011", "TV Series"),
                new Film("Narcos", "2015", "TV Series"))}
        };
    }

    @Test(dataProvider = "usersAndMovies")
    public void removeFilmScenario(User user, List<Film> films) {
        homePage.login(user);
        watchListPage.open();
        watchListPage.watchLists.select("My best TV shows");
        watchListPage.editLink.click();
        editWatchListPage.filmsSearch.addMovies(films);
        //List<FilmsData> films = editWatchListPage.filmCards.asData(FilmsData.class);
        watchListPage.filmsView.select(compact);
        watchListPage.filterFilmsBy(Rating);
        Assert.listEquals(watchListPage.filmsCompactView.entities(), films);
        watchListPage.genres.select(drama);
        watchListPage.editLink.click();
        editWatchListPage.filmCards.get("Narcos").delete();
    }
}
