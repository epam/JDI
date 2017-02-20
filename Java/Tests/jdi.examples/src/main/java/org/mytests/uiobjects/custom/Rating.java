package org.mytests.uiobjects.custom;

import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.web.matcher.testng.Assert;

/**
 * Created by Roman_Iovlev on 2/19/2017.
 */
public class Rating extends Link {
    public void vote(int amount) {
        if (amount < 0 || amount > 10)
            Assert.fail("Vote should be between 0 and 10 but equal " + amount);
        click();

    }
}
