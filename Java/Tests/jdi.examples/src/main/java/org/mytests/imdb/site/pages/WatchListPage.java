package org.mytests.imdb.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Selector;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.mytests.imdb.entities.Film;
import org.mytests.imdb.entities.FilmsTableRow;
import org.mytests.imdb.enums.FilmsTableHeaders;
import org.mytests.imdb.enums.FilmsViewTypes;
import org.mytests.imdb.enums.Genres;
import org.mytests.imdb.enums.UserOptions;
import org.openqa.selenium.support.FindBy;

import static org.mytests.imdb.enums.FilmsTableHeaders.Rating;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class WatchListPage extends WebPage {
    @JDropdown(
            root = @FindBy(id = "navUserMenu"),
            list = @FindBy(linkText = "%s"),
            expand = @FindBy(className = "downArrow"))
    public Dropdown<UserOptions> userMenu;
    @FindBy(css = "[title='%s']")
    public Selector watchLists;
    @FindBy(linkText = "Edit list")
    public Link editLink;
    @FindBy(css = "[data-view='%s']")
    public Selector<FilmsViewTypes> filmsView;

    @JTable(root = @FindBy(css = ".list.compact table"),
            header = {"Check", "Number", "Title", "Year", "Type",
                    "YourRating", "Rating", "Votes", "Created"},
            rowStartIndex = 2)
    public EntityTable<Film, FilmsTableRow> filmsCompactView =
            new EntityTable<>(Film.class, FilmsTableRow.class);
    @FindBy(css = "[value=%s]")
    public CheckList<Genres> genres;

    public void filterFilmsBy(FilmsTableHeaders tableHeader) {
        filmsCompactView.header(tableHeader).select();
        if (tableHeader.equals(Rating))
            filmsCompactView.columns().removeHeaders("Number");
    }
}
