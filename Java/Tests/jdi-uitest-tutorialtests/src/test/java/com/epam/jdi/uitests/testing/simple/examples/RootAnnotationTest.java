package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.epam.EpamSite;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.jdi.site.epam.EpamSite.industryPage;

public class RootAnnotationTest extends TestsBase{
    @Test
    public void rootTest(){
        industryPage.open();

        for (Label l: industryPage.industriesSection.industries) {
            l.click();
            Assert.isTrue(l.getParent() == null);
        }
    }
}
