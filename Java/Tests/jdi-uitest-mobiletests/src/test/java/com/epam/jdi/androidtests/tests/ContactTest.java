package com.epam.jdi.androidtests.tests;

import com.epam.jdi.androidtestind.page_objects.pages.ContactDetails;
import org.testng.annotations.Test;
import static com.epam.jdi.androidtestind.page_objects.EpamJDIAndroidApp.*;
/**
 * Created by Natalia_Grebenshchik on 12/25/2015.
 */
public class ContactTest extends TestBase {

    @Test
    public void addContactTest() throws InterruptedException {
        ContactDetails contacts = new ContactDetails("Irina", "758-58-58");

        startPage.addContact.click();
        addContactForm.save(contacts);
    }
}
