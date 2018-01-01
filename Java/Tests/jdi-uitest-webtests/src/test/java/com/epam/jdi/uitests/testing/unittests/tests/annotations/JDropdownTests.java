package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.JDropdownDP;
import com.epam.web.matcher.junit.Check;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;


public class JDropdownTests extends InitTests {
    @BeforeMethod
    public void pageLoad() {
        metalsColorsPage.open();
    }
    private Boolean dropdownExpanded() {
        return getDriver().findElement(By.xpath("//span[@class='text' and text()='Colors']")).isDisplayed();
    }
    private String dropdownValue() {
        return getDriver().findElement(By.cssSelector("[data-id=colors-dropdown] .filter-option")).getText();
    }

    @Test(dataProvider = "dropdownData", dataProviderClass = JDropdownDP.class)
    public void dropdown(IDropDown dropdown, Boolean selectResult, String text, String options) {
        dropdown.expand();
        new Check().isTrue(dropdownExpanded());
        dropdown.close();
        new Check().isFalse(dropdownExpanded());

        new Check().isTrue(dropdownValue().equals(text));
        new Check().areEquals(dropdown.getOptions(), options);
        dropdown.select("Blue");
        new Check().areEquals(dropdownValue().equals("Blue"), selectResult);
    }

    @Test(dataProvider = "dropdownData", dataProviderClass = JDropdownDP.class)
    public void dropdownTestgetSelectedIndex(IDropDown dropdown, Boolean selectResult, String text, String options) {
        dropdown.select(2);
        new Check().areEquals(dropdown.getSelectedIndex(), 2);
    }

}
