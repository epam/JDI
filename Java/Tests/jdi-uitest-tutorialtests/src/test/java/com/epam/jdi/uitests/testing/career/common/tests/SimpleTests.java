package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.uitests.testing.TestsBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.site.epam.EpamSite.careerPage;
import static com.epam.jdi.site.epam.EpamSite.headerMenu;

public class SimpleTests extends TestsBase {
  @BeforeClass
  public static void testAllureBeforeClass() {

	  Logger logger= LogManager.getLogger();
    System.out.println("SimpleTests.testAllureBeforeClass");
  }

  @Test
  public void simpleTest() {
    headerMenu.select(CAREERS);
    careerPage.checkOpened();
  }
}
