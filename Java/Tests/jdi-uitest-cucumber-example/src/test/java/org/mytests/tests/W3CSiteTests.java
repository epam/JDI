package org.mytests.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;


@Features("Dropdown tests for W3C site")
@Stories({"Test story", "W3C story"})
@CucumberOptions (features ="classpath:org/mytests/features/w3c/",
        glue={"org/mytests/tests/steps/w3c/"})
public class W3CSiteTests extends AbstractTestNGCucumberTests{
}