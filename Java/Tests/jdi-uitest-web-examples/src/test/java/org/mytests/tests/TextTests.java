package org.mytests.tests;

import com.epam.web.matcher.testng.Assert;
import org.mytests.InitTestsText;
import org.testng.annotations.Test;

import static org.mytests.textsSite.site.TextSite.textsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class TextTests extends InitTestsText {

    @Test
    public void inputTest() {
        textsPage.open();
        textsPage.textArea.inputLines("String 1", "String 2");
        textsPage.field1.newInput("Some text 1");
        textsPage.field2.newInput("Some text 2");
        Assert.arrayEquals(textsPage.textArea.getLines(),
                new String[] {"String 1", "String 2"});
        Assert.areEquals(textsPage.field1.getText(),"Some text 1");
        Assert.areEquals(textsPage.field2.getText(),"Some text 2");
    }
}
