package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.web.matcher.junit.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;


public class JDropdownTests extends InitTests {
    @BeforeMethod
    public void pageLoad(){
        metalsColorsPage.open();
    }

    @Test //passed
    public void selectTestForJDropdownWithRootExpandList() throws InterruptedException {
        metalsColorsPage.colorsRootExpandList.select("Blue");
        new Check().isTrue(metalsColorsPage.colorsRootExpandList.getSelected().contains("Blue"));
    }

    @Test //passed
    public void expandTestForJDropdownWithRootExpandList() {
        metalsColorsPage.colorsRootExpandList.expand();
    }

    @Test //passed
    public void getTextTestForJDropdownWithRootExpandList() {
       new Check().isTrue(metalsColorsPage.colorsRootExpandList.getText().contains("Colors"));
    }

    @Test //passed
    public void getOptionsTestForJDropdownWithRootExpandList() {
        new Check().areEquals(metalsColorsPage.colorsRootExpandList.getOptions(), "[Colors, Red, Green, Blue, Yellow]");
    }

    @Test //FAIL
    public void selectTestForJDropdownWithRootList() {
        metalsColorsPage.colorsRootList.select("Blue");
        new Check().isTrue(metalsColorsPage.colorsRootList.getSelected().contains("Blue"));
    }

    @Test //FAIL
    public void expandTestForJDropdownWithRootExpand(){
        metalsColorsPage.colorsRootExpand.expand();
    }

    @Test //FAIL
    public void clickTestForJDropdownWithRoot(){
        metalsColorsPage.colorsRoot.click();
    }

    @Test //FAIL
    public void getTextTestForJDropdownWithRoot(){
        metalsColorsPage.colorsRoot.getText();
    }

    @Test //FAIL
    public void clickTestForJDropdownWithValue(){
        metalsColorsPage.colorsValue.click();
    }

}
