package org.mytests.uiobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Selector;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.mytests.entities.Film;
import org.mytests.entities.FilmsTableRow;
import org.mytests.enums.FilmsTableHeaders;
import org.mytests.enums.FilmsViewTypes;
import org.mytests.enums.Genres;
import org.mytests.enums.UserOptions;
import org.mytests.uiobjects.custom.Rating;
import org.openqa.selenium.support.FindBy;

import static org.mytests.enums.FilmsTableHeaders.Rating;

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
    public ILink editLink;
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
