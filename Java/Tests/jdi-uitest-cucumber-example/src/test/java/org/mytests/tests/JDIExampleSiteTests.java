package org.mytests.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;


@Features("Login tests for JDIExampleSite")
@Stories({"Test story", "JDIExample story"})
@CucumberOptions (features ="classpath:org/mytests/features/JDIExampleSite/",
        glue={"org/mytests/tests/steps/JDIExampleSite"})
public class JDIExampleSiteTests extends AbstractTestNGCucumberTests{
}