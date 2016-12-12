package com.epam.jdi.uitests.testing.unittests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Metals;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors.NestedMetalsAndColorsView;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.listEquals;
import static java.util.Arrays.asList;

public class ComboBoxTests extends InitTests {
    private static final List<String> METALS = asList("Col", "Gold", "Silver", "Bronze", "Selen");
    private NestedMetalsAndColorsView nestedMetalsAndColorsView;

    @BeforeSuite
    public void initVarialbe() {
        nestedMetalsAndColorsView = mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView;
    }

    @BeforeMethod
    public void before(Method method) {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void selectStringTest() {
        nestedMetalsAndColorsView.metalsComboBox.select("Gold");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Metals: value changed to Gold");
    }

    @Test
    public void selectIndexTest() {
        nestedMetalsAndColorsView.metalsComboBox.select(3);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Metals: value changed to Silver");
    }

    @Test
    public void selectEnumTest() {
        nestedMetalsAndColorsView.metalsComboBox.select(Metals.GOLD);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Metals: value changed to Gold");
    }

    @Test
    public void getOptionsTest() {
        listEquals(nestedMetalsAndColorsView.metalsComboBox.getOptions(), METALS);
    }

    @Test
    public void getNamesTest() {
        listEquals(nestedMetalsAndColorsView.metalsComboBox.getNames(), METALS);
    }

    @Test
    public void getValuesTest() {
        listEquals(nestedMetalsAndColorsView.metalsComboBox.getValues(), METALS);
    }

    @Test
    public void getOptionsAsTextTest() {
        areEquals(nestedMetalsAndColorsView.metalsComboBox.getOptionsAsText(), "Col, Gold, Silver, Bronze, Selen");
    }

    @Test
    public void setValueTest() {
        nestedMetalsAndColorsView.metalsComboBox.setValue("Blue");
        mainWindow.logTextBox.focus();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Metals: value changed to Blue");
    }

    @Test
    public void getSelectedTest() {
        nestedMetalsAndColorsView.metalsComboBox.select("Gold");
        areEquals(nestedMetalsAndColorsView.metalsComboBox.getSelected(), "Gold");
    }

    @Test
    public void isSelectedTest() {
        areEquals(nestedMetalsAndColorsView.metalsComboBox.isSelected("Col"), true);
    }

    @Test
    public void isSelectedEnumTest() {
        areEquals(nestedMetalsAndColorsView.metalsComboBox.isSelected(Metals.COL), true);
    }

    @Test
    public void getValueTest() {
        areEquals(nestedMetalsAndColorsView.metalsComboBox.getValue(), "Col");
    }
}