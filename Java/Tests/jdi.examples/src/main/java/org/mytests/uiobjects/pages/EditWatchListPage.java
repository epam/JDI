package org.mytests.uiobjects.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JSearch;
import org.mytests.uiobjects.custom.FilmsSearch;
import org.mytests.uiobjects.sections.FilmCard;
import org.openqa.selenium.support.FindBy;

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

}
