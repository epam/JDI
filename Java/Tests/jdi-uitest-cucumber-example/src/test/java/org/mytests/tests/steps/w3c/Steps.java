package org.mytests.tests.steps.w3c;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.web.matcher.testng.Assert;
import com.google.common.io.Files;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mytests.uiobjects.w3c.W3CSite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static org.mytests.uiobjects.w3c.W3CSite.dropDownPage;

public class Steps {

    @Given("Prepare environment")
    public void setUp() throws Exception {
        WebSettings.initFromProperties();
        WebSite.init(W3CSite.class);
        logger.info("Run W3C site tests");
    }

    @Given("^I am on ([^\\\"]*) page$")
    public void preconditionExecute(String pageName) throws Exception {
        if (pageName.equals("DropDown")) {
            dropDownPage.shouldBeOpened();
        } else {
            throw new Exception("Page not specified");
        }
        Assert.assertTrue(dropDownPage.verifyOpened());
    }

    @When("^I select ([^\\\"]*) item$")
    public void selectExecute(String item) {
        dropDownPage.frame.cars.select(item);
    }

    @Then("^([^\\\"]*) item is selected$")
    public void checkSelected(String selectedItem) throws Throwable {
        Assert.areEquals(dropDownPage.frame.cars.getSelected(), selectedItem);
    }

    @When("^I select ([^\\\"]*) item using simple dropdown$")
    public void selectExecuteSimple(String item) {
        dropDownPage.frame.carsSimple.select(item);
    }

    @Then("^([^\\\"]*) item is selected using simple dropdown$")
    public void checkSelectedSimple(String selectedItem) throws Throwable {
        Assert.areEquals(dropDownPage.frame.carsSimple.getSelected(), selectedItem);
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
