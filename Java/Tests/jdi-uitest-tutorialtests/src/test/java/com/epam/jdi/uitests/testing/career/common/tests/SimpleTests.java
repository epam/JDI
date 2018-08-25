package com.epam.jdi.uitests.testing.career.common.tests;


import com.epam.jdi.uitests.testing.TestsBase;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.site.epam.EpamSite.*;

public class SimpleTests extends TestsBase {

    @Test
    public void simpleTest() {
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
    }

}
