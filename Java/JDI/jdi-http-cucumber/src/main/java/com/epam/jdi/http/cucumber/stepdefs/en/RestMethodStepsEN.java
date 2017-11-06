package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.RestMethod;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import static com.epam.jdi.http.cucumber.Utils.getRestMethod;
import static com.epam.jdi.http.cucumber.Utils.service;

public class RestMethodStepsEN {

    @Then("^I verify that ([^\"]*) method is alive$")
    public void theGetMethodIsAlive(String methodName){
        RestMethod getMethod = getRestMethod(methodName);
        Assert.assertTrue(getMethod.isAlive());
    }
}
