package org.mytests.textsSite.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Input;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class TextPage extends WebPage {

    public TextPage(String url){
        super(url);
    }

    @FindBy(xpath="//textarea[@id='Description']")
    public TextArea textArea;
    @FindBy(xpath="//input[@id='Name']")
    public Input field1;
    @FindBy(xpath="//input[@id='LastName']")
    public Input field2;

}
