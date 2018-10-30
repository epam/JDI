package com.epam.jdi.uitests.testing;

import com.epam.jdi.site.epam.EpamSite;
import com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static com.epam.jdi.uitests.core.logger.LogLevels.STEP;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;

/** Created by Roman_Iovlev on 7/13/2015. */
public abstract class TestsBase extends TestNGBase {

  @BeforeSuite(alwaysRun = true)
  public static void setUp() throws IOException {
    logger.setLogLevel(STEP);
    init(EpamSite.class);
    EpamSite.open();
    logger.info("Run Tests");
  }

  @AfterSuite(alwaysRun = true)
  public static void tearDown() throws IOException {

    if (WebSettings.killBrowser.toLowerCase().contains("after")) {
      WebDriverUtils.killWebBrowserByName(WebSettings.getDriverFactory().currentDriverName());
      // killAllRunWebBrowsers();
    }
  }
}
