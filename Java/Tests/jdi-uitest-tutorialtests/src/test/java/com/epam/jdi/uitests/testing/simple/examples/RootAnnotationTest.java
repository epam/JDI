package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.epam.EpamSite;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.jdi.site.epam.EpamSite.*;

public class RootAnnotationTest extends TestsBase {
    @Test
    public void rootTestOurWorkSection() {
        ourWorkPage.shouldBeOpened();
        for (Label l : ourWorkPage.ourWorkSection.ourWork) {
//            l.click();
            Assert.isTrue(l.getParent() == null);
        }
    }

    @Test
    public void rootTestIdeasSection() {
        insightsPage.shouldBeOpened();
        for (Image i : insightsPage.insightsSection.photos) {
            Assert.isTrue(i.getParent() == null);
        }
    }

    @Test
    public void rootTestContactSection() {
        EpamSite.shouldBeOpened();
        multipleHeaderMenu.hoverAndClick("ABOUT|Contact Us");
        Assert.isTrue(contactUs.contactUsSection.name.getParent() == null);
    }
}
