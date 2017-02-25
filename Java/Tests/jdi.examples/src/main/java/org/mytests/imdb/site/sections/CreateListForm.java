package org.mytests.imdb.site.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.web.selenium.elements.complex.CheckList;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.mytests.imdb.entities.ListParams;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

/**
 * Created by Roman_Iovlev on 2/12/2017.
 */
public class CreateListForm extends Form<ListParams> {
    @FindBy(css = "[value=%s]")     public CheckList listKind;
    @FindBy(id = "list-name")       public TextField listName;
    @FindBy(id = "create-desc")     public IButton listDescription;
    @JFindBy(text = "signInSubmit") public IButton startListingButton;


}
