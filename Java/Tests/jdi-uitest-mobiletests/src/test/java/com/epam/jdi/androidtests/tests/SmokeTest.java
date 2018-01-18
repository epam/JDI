package com.epam.jdi.androidtests.tests;
/**
 * Created by Natalia_Grebenshchikova on 12/25/2015.
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

public class SmokeTest {
    private AppiumDriver<AndroidElement> driver;

    @Before
    public void before() throws Exception {
        // set up appium
        File app = new File("src\\test\\resources\\ContactManager.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addContact() {
        WebElement el = driver.findElement(By.id("com.example.android.contactmanager:id/addContactButton"));
        el.click();
        List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(1).sendKeys("Some@example.com");
        driver.findElement(By.id("com.example.android.contactmanager:id/contactSaveButton")).click();
    }

    @Test
    public void addContact2() {
        WebElement el = driver.findElement(By.id("com.example.android.contactmanager:id/addContactButton"));
        el.click();
        List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(1).sendKeys("Some@example.com");
        driver.findElement(By.id("com.example.android.contactmanager:id/contactSaveButton")).click();
    }

}
