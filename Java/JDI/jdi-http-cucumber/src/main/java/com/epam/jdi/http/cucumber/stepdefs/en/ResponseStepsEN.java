package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.ResponseStatusType;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import static com.epam.jdi.http.cucumber.Utils.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class ResponseStepsEN {

    @Then("^Performance results don't have any fails$")
    public void performanceResultsDonTHaveAnyFails() {
        Assert.assertTrue(performanceResult.get().NoFails());
    }

    @And("^I check number of requests$")
    public void iCheckNumberOfRequests() {
        System.out.println("There were " + performanceResult.get().NumberOfRequests + " requests.");
    }

    @Then("^Response status code equals (\\d+)$")
    public void responseStatusCodeEquals(int statusCode){
        assertEquals(restResponse.get().status().code(), statusCode);
    }

    @And("^Response body is empty")
    public void responseBodyIs() {
        assertEquals(restResponse.get().body(), "");
    }

    @And("^Response status type is ([^\"]*)$")
    public void responseStatusTypeIs(String type) {
        Boolean typeMatches = false;
        for (ResponseStatusType responseStatusType : ResponseStatusType.values()) {
            if(type.equalsIgnoreCase(responseStatusType.name()))
                typeMatches = true;
        }
        Assert.assertTrue(typeMatches);
    }

    @And("^Response \"([^\"]*)\" is \"([^\"]*)\"$")
    public void responseParameterIsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, equalTo(value));
    }

    @Then("^I check if performance results contain any fails$")
    public void iCheckIfPerformanceResultsContainAnyFails() {
        long numberOfFails = performanceResult.get().NumberOfFails;
        if (numberOfFails == 0)
            System.out.println("There were no failures.");
        else if (numberOfFails == 1)
            System.out.println("There was 1 failure.");
        else
            System.out.println("There were " + numberOfFails + " failures.");
    }

    @And("^Average response time is lesser than (\\d+) sec$")
    public void averageResponseTime(long seconds) {
        Assert.assertTrue(performanceResult.get().AverageResponseTime < seconds);
    }

    @And("^Response header \"([^\"]*)\" is \"([^\"]*)\"$")
    public void responseHeaderIs(String parameter, String value) {
        restResponse.get().assertThat().header(parameter, value);
    }

    @And("^Json response \"([^\"]*)\" is \"([^\"]*)\"$")
    public void jsonResponseIs(String parameter, String value){
        assertEquals(restResponse.get().jsonBody(parameter), value);
    }

    @And("^I print response$")
    public void iPrintResponse() {
        restResponse.get().body();
    }
}
