package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.site.epam.EpamSite;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.site.epam.EpamSite.careerPage;
import static com.epam.jdi.site.epam.EpamSite.headerMenu;

public class SimpleTests extends TestsBase {

    @Test
    public void simpleTest() {
        EpamSite.open();
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
        Assert.areEquals("test", "test");
    }

}
