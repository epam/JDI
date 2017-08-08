package org.mytests.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;


@Features("Navigation and input tests for EpamTestSite")
@Stories({"Test story", "EpamSite story"})
@CucumberOptions (features ="classpath:org/mytests/features/wwwepam/",
        glue={"org/mytests/tests/steps/wwwepam"})
public class EpamSiteTests extends AbstractTestNGCucumberTests{
}