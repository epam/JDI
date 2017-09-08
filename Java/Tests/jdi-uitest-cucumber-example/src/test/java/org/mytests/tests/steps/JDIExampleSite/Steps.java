package org.mytests.tests.steps.JDIExampleSite;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.google.common.io.Files;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mytests.uiobjects.example.JDIExampleSite;
import org.mytests.uiobjects.example.entities.User;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.mytests.uiobjects.example.JDIExampleSite.homePage;
import static org.mytests.uiobjects.example.JDIExampleSite.login;

public class Steps {

    @Given("Prepare environment")
    public void setUp() throws Exception {
        WebSettings.initFromProperties();
        WebSite.init(JDIExampleSite.class);
        logger.info("Run JDIExample site tests");
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

    @When("^I login as ([^\\\"]*)/([^\\\"]*)$")
    public void loginExecute(String userName, String userPassword) throws IOException {
        login(new User(userName, userPassword));
    }

    @Then("^Login ([^\\\"]*)$")
    public void checkStatus(String status) throws Throwable {
        Boolean isCorrect = status.equals("succeeded");
        screenshot();
        Assert.assertTrue(homePage.userName.isDisplayed() == isCorrect);
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
