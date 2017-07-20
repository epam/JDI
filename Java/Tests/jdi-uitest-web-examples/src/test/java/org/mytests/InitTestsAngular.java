package org.mytests;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.mytests.angular.site.AngularSite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class InitTestsAngular extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(AngularSite.class);
    }

}
