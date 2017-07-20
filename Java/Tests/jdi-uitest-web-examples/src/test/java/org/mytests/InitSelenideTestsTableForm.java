package org.mytests;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.mytests.epam.site.site.EpamSite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class InitSelenideTestsTableForm extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(EpamSite.class);
    }

}
