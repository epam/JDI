package org.mytests.textsSite.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Input;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class TextPage extends WebPage {
    @FindBy(id="textarea")
    public TextArea textArea;
    @FindBy(id="input")
    public Input field;

}
