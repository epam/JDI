package org.mytests.epam.site.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.mytests.epam.site.site.pages.JobDescriptionPage;
import org.mytests.epam.site.site.pages.JobsPage;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class EpamSite extends WebSite {

    @JPage(url = "https://www.epam.com/careers/job-listings?query=testD:\\Work\\Projects\\Java\\JDI\\GitHub\\Java\\Tests\\jdi-uitest-web-examples\\src\\test\\resources\\cv.txt" +
            "&department%5B%5D=all&city=St-Petersburg&country=Russia")
    public static JobsPage jobsPage;
    @JPage
    public static JobDescriptionPage jobDescriptionPage;

}
