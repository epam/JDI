package org.mytests.imdb.site.sections;

import com.epam.jdi.uitests.core.annotations.Name;
import com.epam.jdi.uitests.core.annotations.Title;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 2/17/2017.
 */
public class FilmCard extends Section {
    @FindBy(css = ".delete")
    public Button deleteButton;
    @FindBy(css = ".image")
    public Image image;
    @FindBy(css = ".number input")
    public TextField number;
    @FindBy(css = ".info") @Title
    public Text title;
    @FindBy(css = ".description textarea")
    public TextArea description;
    @FindBy(css = ".btn.ok")
    public Button okButton;
    @FindBy(css = ".btn.cancel")
    public Button cancelButton;

    public void delete() { deleteButton.click(); }
}
