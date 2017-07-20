package org.mytests.tests;

import com.epam.web.matcher.testng.Assert;
import org.mytests.InitTestsAngular;
import org.testng.annotations.Test;

import static org.mytests.angular.site.AngularSite.angularPage;

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
