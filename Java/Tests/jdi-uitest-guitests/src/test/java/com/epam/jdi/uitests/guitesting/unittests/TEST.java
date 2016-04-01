package com.epam.jdi.uitests.guitesting.unittests;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.gui.GuiSettings;
import com.epam.jdi.uitests.guitesting.unittests.pageobjects.EpamJDIScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.gui.sikuli.elements.composite.Screen.init;
import static com.epam.jdi.uitests.guitesting.unittests.pageobjects.EpamJDIScreen.homePage;
import static java.lang.System.setProperty;

/**
 * Created by Natalia_Grebenshchik on 1/15/2016.
 */
public class TEST {

    WebDriver driver;

    @BeforeMethod
    public void loadApp(){
        setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://ecse00100176.epam.com");
        js.executeScript("document.body.style.zoom='100%'");


        GuiSettings.init();
        init(EpamJDIScreen.class);

        homePage.login();
    }

    @Test
    public void test1() {

        Timer timer = new Timer(1000);
       // homePage.logoImage.dragAndDropBy(homePage.searchButton);

       // homePage.logoImage.doubleClick();
       // homePage.logoImage.rightClick();

       // homePage.logoImage.waitVanished();

        homePage.searchButton.click();
        //homePage.searchField.input("ddddd");
    }

    @Test
    public void loginFirst(){

    }

    @AfterMethod
    public  void closeApp(){
        driver.quit();
    }
}
