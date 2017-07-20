package org.mytests;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.mytests.textsSite.site.TextSite;
import org.testng.annotations.BeforeSuite;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class InitTestsText extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(TextSite.class);
    }
}
