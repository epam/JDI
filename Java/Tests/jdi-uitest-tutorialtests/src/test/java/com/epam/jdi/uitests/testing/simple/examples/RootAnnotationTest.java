package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.jdi.site.epam.EpamSite.*;

public class RootAnnotationTest extends TestsBase{
    @Test
    public void rootTestIndustrySection(){
        industryPage.open();

        for (Label l: industryPage.industriesSection.industries) {
            l.click();
            Assert.isTrue(l.getParent() == null);
        }
    }

    @Test
    public void rootTestIdeasSection(){
        ideasPage.open();
        for (Image i: ideasPage.itemsSection.photos) {
            Assert.isTrue(i.getParent() == null);
        }
    }

    @Test
    public void rootTestContactSection(){
        header.contactButton.click();
        Assert.isTrue(contactUs.events.getParent() == null);
    }
}
