package org.mytests.tests;

import com.epam.web.matcher.testng.Assert;
import org.mytests.InitTestsAngular;
import org.mytests.epam.entities.Attendee;
import org.mytests.epam.entities.Job;
import org.mytests.epam.selenide.PageJobs;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static org.mytests.angular.site.AngularSite.angularPage;
import static org.mytests.epam.selenide.PageJobDescription.pageJobDescription;
import static org.mytests.epam.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.EpamSite.jobsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class AngularTests extends InitTestsAngular {

    @Test
    public void checkBoxSliderTest() {
        angularPage.open();
        angularPage.acceptTerms.check();
        Assert.isTrue(angularPage.acceptTerms.isChecked());

        angularPage.specialOptions.check();
        Assert.isTrue(angularPage.specialOptions.isChecked());
    }


}
