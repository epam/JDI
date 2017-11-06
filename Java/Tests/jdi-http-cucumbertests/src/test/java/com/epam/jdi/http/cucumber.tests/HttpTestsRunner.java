package com.epam.jdi.http.cucumber.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/com/epam/jdi/http/features/",
        glue = {"com/epam/jdi/http/cucumber/tests/steps", "com/epam/jdi/http/cucumber/stepdefs/en"})
public class HttpTestsRunner extends AbstractTestNGCucumberTests {
}
