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
    public void pageLoad(){
        metalsColorsPage.open();
    }

    @Test(dataProvider = "dropdownData", dataProviderClass = JDropdownDP.class)
    public void dropdown(IDropDown dropdown, Boolean selectResult, String text, String options){
        new Check().isTrue(dropdown.getText().equals(text));
        dropdown.select("Blue");
        new Check().areEquals(dropdown.isSelected("Blue"), selectResult);
        dropdown.expand();
        new Check().areEquals(dropdown.getOptions(), options);

    }

}
