package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.jdi.site.epam.EpamSite.*;

public class RootAnnotationTest extends TestsBase {
    @Test
    public void rootTestOurWorkSection() {
        for (Label l : ourWorkPage.ourWorkSection.ourWork)
            Assert.isTrue(l.getParent() == null);
    }

    @Test
    public void rootTestIdeasSection() {
        for (Image i : insightsPage.insightsSection.photos)
            Assert.isTrue(i.getParent() == null);
    }

    @Test
    public void rootTestContactSection() {
        Assert.isTrue(contactUs.contactUsSection.name.getParent() == null);
    }
}
