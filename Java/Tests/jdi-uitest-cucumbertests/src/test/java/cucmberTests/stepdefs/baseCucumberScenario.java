package cucmberTests.stepdefs;

import com.epam.jdi.uitests.testing.unittests.entities.User;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.initFromProperties;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.login;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriverFactory;

/**
 * Created by Dmitry_Lebedev1 on 1/13/2016.
 */
public class baseCucumberScenario {

    @Before
    public void before(){
        try {
            TestNGBase.jdiSetUp();

            getDriverFactory().setDriverPath("C:/Selenium");
            initFromProperties();
            //Assert.noScreenOnFail();
            WebSite.init(EpamJDISite.class);
            homePage.open();
            login.submit(User.DEFAULT_USER);
            logger.info("Run Tests");
            Verify.getFails();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() throws IOException {
        TestNGBase.jdiTearDown();
        Verify.getFails();
    }
}
