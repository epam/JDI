package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.core.interfaces.complex.ISearch;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.JSearchDP;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;

public class JSearchTests extends InitTests {
    @BeforeMethod
    public void pageLoad() {
        homePage.open();
        homePage.openSearch.click();
    }

    @Test(dataProvider = "searchData", dataProviderClass = JSearchDP.class)
    public void findTestForJSearchWithRootInputSearchButton(ISearch search, Boolean option) {
        search.clear();
        search.find("JDI");
        supportPage.checkOpened();
    }


}
