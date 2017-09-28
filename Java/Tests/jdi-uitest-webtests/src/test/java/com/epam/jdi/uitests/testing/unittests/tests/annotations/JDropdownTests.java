package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.JDropdownDP;
import com.epam.web.matcher.junit.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;


public class JDropdownTests extends InitTests {
    @BeforeMethod
    public void pageLoad() {
        metalsColorsPage.open();
    }

    @Test(dataProvider = "dropdownData", dataProviderClass = JDropdownDP.class)
    public void dropdown(IDropDown dropdown, Boolean selectResult, String text, String options) {
        dropdown.expand();
        new Check().isTrue(dropdown.isDisplayed());
        dropdown.close();
        new Check().isFalse(dropdown.isDisplayed());

        new Check().isTrue(dropdown.getText().equals(text));
        new Check().areEquals(dropdown.getOptions(), options);
        dropdown.select("Blue");
        new Check().areEquals(dropdown.isSelected("Blue"), selectResult);
    }

    @Test(dataProvider = "dropdownData", dataProviderClass = JDropdownDP.class)
    public void dropdownTestgetSelectedIndex(IDropDown dropdown, Boolean selectResult, String text, String options) {
        dropdown.select(2);
        new Check().areEquals(dropdown.getSelectedIndex(), 2);
    }

}
