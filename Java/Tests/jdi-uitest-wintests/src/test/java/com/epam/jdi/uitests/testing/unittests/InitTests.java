package com.epam.jdi.uitests.testing.unittests;

import com.epam.jdi.uitests.testing.unittests.User;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.HomePage;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.HeaderMenu;
import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winium.elements.composite.WebSite;
import com.epam.jdi.uitests.win.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.notepadtests.pageobjects.NotepadApplication.mainWindow;
import static com.epam.jdi.uitests.testing.notepadtests.pageobjects.NotepadApplication.saveAsWindow;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.header;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.homePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.login;
//import static com.epam.jdi.uitests.web.settings.WebSettings.*;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class InitTests extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(EpamJDIApplication.class);
      //  login.submit(User.DEFAULT);
      //  Verify.getFails();
        logger.info("Run Tests");
    }

    @Test
    public static void Test() throws InterruptedException {

//HomePage.log.clickCenter();
      //  logger.info(HomePage.headerMenu.toString());
                //elect(HeaderMenu.CONTACT);

      //  EpamJDIApplication.homePage.log.clickCenter();
     //   List<WebElement> els = WinSettings.getDriver().findElements(By.name("Contact form"));
     //   List<WebElement> els2 = els.stream().filter(el -> el.getAttribute("ClassName").equals("MenuItem")).collect(Collectors.toList());

    //    if (els2.size() == 1)
     //       els2.get(0).click();
     //   else
     //       System.out.println("more than one el found");
      //  WebElement el = els.stream().filter(el -> el.getAttribute("className").equals("MenuItem"));
        //Stream<WebElement> el3 = els.stream().filter(el -> el.getAttribute("ClassName").equals("MenuItem"));
     //   EpamJDIApplication.homePage.log.getWebElement();
        EpamJDIApplication.homePage.headerMenu.clickCenter();
        EpamJDIApplication.homePage.headerMenu.contactForm.click();

      //  EpamJDIApplication.contactFormPage.


        //EpamJDIApplication.homePage.headerMenu.select(CONTACT);
     //   EpamJDIApplication.homePage.headerMenu2.contact.click();


        logger.info("HELLO");
    }

    @AfterMethod
    public void tearDown() {
        Verify.getFails();
    }
}
