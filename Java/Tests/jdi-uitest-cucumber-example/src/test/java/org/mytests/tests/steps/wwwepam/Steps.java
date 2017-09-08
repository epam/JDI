package org.mytests.tests.steps.wwwepam;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.web.matcher.testng.Assert;
import com.google.common.io.Files;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mytests.uiobjects.wwwepam.EpamSite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.mytests.uiobjects.wwwepam.EpamSite.*;
import static org.mytests.uiobjects.wwwepam.enums.HeaderMenu.CAREERS;

public class Steps {

    @Given("Prepare environment")
    public void setUp() throws Exception {
        WebSettings.initFromProperties();
        WebSite.init(EpamSite.class);
        logger.info("Run EPAM site tests");
    }

    @Given("^I am on ([^\\\"]*) page$")
    public void preconditionExecute(String pageName) throws Exception {
        if (pageName.equals("Home")) {
            homePage.shouldBeOpened();
        } else {
            throw new Exception("Page not specified");
        }
        Assert.assertTrue(homePage.verifyOpened());
    }

    @When("^I select ([^\\\"]*) tab$")
    public void selectExecute(String tab) throws Exception {

        if (tab.equals("CAREERS")) {
            headerMenu.select(CAREERS);
        } else {
            throw new Exception("Trying to select tab for unspecified page.");
        }
    }

    @When("^I input ([^\\\"]*)$")
    public void inputText(String text) throws Throwable {
        careerPage.keywords.newInput(text);
    }

    @Then("^([^\\\"]*) page is opened$")
    public void checkOpenedPage(String pageName) throws Throwable {
        if (pageName.equals("Career")) {
            Assert.assertTrue(careerPage.verifyOpened());
            screenshot();
        } else {
            throw new Exception("Page not specified");
        }
    }

    @Then("^([^\\\"]*) is typed$")
    public void checkForInput(String input) throws Throwable {
        Assert.areEquals(careerPage.keywords.getText(), input);
        screenshot();
    }

    @Then("Closing driver")
    public void closeBrowser() {
        WebSettings.getDriver().quit();
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File scrFile = ((TakesScreenshot) WebSettings.getDriver()).getScreenshotAs(OutputType.FILE);
        return Files.toByteArray(scrFile);
    }

}
