package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.core.interfaces.complex.IMenu;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.JMenuDP;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;

public class JMenuTests extends InitTests {
    @Test(dataProvider = "menuData", dataProviderClass = JMenuDP.class)
    public void jMenuTest(IMenu menu, List list, String options) throws InterruptedException {

        new Check().areEquals(homePage.menu1.getOptionsAsText(), options);
        new Check().areEquals(homePage.menu1.getNames(), list);

        menu.select("CONTACT FORM");
        new Check().isTrue(menu.isSelected("CONTACT FORM"));

        new Check().areEquals(menu .getOptions(), list);



    }
}
