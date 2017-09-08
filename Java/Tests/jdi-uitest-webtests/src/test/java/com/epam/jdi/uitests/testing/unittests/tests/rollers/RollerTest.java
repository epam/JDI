package com.epam.jdi.uitests.testing.unittests.tests.rollers;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.dataproviders.RollerDP;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.ContactForm;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.IContactForm;
import com.epam.web.matcher.testng.Check;
import com.google.common.base.Supplier;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DATES_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.actionsLog;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dates;


/**
 * Created by Konstantin_Pulin on 9/27/2016.
 */
public class RollerTest extends InitTests {

    Supplier<IContactForm> form = () -> dates.contactForm;

    public RollerTest() {

    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(DATES_PAGE, method);
    }


    //@Test(dataProvider = "leftRollerDP", dataProviderClass = RollerDP.class)
    public void leftRollerChangeTest(int rollerPosition) {
        ContactForm contactForm = (ContactForm) form.get();
        contactForm.setLeftRollerPosition(rollerPosition);
        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} Range 2\\(From\\):" + rollerPosition + " link clicked");
    }


    //@Test(dataProvider = "rightRollerDP", dataProviderClass = RollerDP.class)
    public void rightRollerChangeTest(int rollerPosition) {
        ContactForm contactForm = (ContactForm) form.get();
        contactForm.setRightRollerPosition(rollerPosition);
        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} Range 2\\(To\\):" + rollerPosition + " link clicked");
    }


    @Test(dataProvider = "bothRollersDataProvider", dataProviderClass = RollerDP.class)
    public void bothRollersChangeTest(int leftRollerPosition, int rightRollerPosition) {
        ContactForm contactForm = (ContactForm) form.get();
        contactForm.setLeftRollerPosition(leftRollerPosition);
        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} Range 2\\(From\\):" + leftRollerPosition + " link clicked");
        contactForm.setRightRollerPosition(rightRollerPosition);
        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} Range 2\\(To\\):" + rightRollerPosition + " link clicked");
    }

}
