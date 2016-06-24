package com.epam.jdi.uitests.testing.unittests.pageobjects.w3cSite;

import com.epam.jdi.uitests.win.winium.elements.common.Button;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.Frame;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */

public class FramePage extends WebPage {
    @Frame(id = "iframeResult")
    public FrameSection frame;

    @FindBy(partialLinkText = "Try it Yourself")
    public Button tryIt = new Button() {
        @Override
        public void clickAction() {
            super.clickAction();
            Iterator<String> i = getDriver().getWindowHandles().iterator();
            i.next();
            getDriver().switchTo().window(i.next());
        }
    };
}
