package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.ColorsList;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.jComboBoxDP;
import com.epam.web.matcher.junit.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;

public class JComboBoxTests extends InitTests {
    @BeforeMethod
    public void pageLoad() {
        metalsColorsPage.open();
    }

    @Test(dataProvider = "comboBoxData", dataProviderClass = jComboBoxDP.class)
    public void comboBoxTest(IComboBox comboBox, Boolean option) {
        new Check().areEquals(comboBox.getText(), "Colors");
        new Check().areEquals(comboBox.getValue(), "Colors");
        new Check().areEquals(comboBox.getOptionsAsText(), "Colors, Red, Green, Blue, Yellow");
        new Check().areEquals(comboBox.getOptions(), "[Colors, Red, Green, Blue, Yellow]");
        new Check().areEquals(comboBox.getValues(), "[Colors, Red, Green, Blue, Yellow]");


        comboBox.expand();
        new Check().isTrue(comboBox.isDisplayed());
        comboBox.close();
        new Check().isFalse(comboBox.isDisplayed());

        comboBox.select("Blue");
        new Check().areEquals(comboBox.getSelected(), "Blue");
        comboBox.select(2);

        new Check().areEquals(comboBox.getSelected(), "Red");

        comboBox.select(ColorsList.Green);
        new Check().areEquals(comboBox.getSelected(), "Green");

    }


    @Test(dataProvider = "comboBoxData", dataProviderClass = jComboBoxDP.class)
    public void comboBoxTestGetSelectedIndex(IComboBox comboBox, Boolean option) {
        comboBox.select(2);
        new Check().areEquals(comboBox.getSelectedIndex(), 2);
    }


}
