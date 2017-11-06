package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.ResponseStatusType;
import com.epam.http.requests.RestMethod;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    public void responseUrlIsUrl(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, equalTo(value));
    }

    @When("^I call ([^\"]*) method$")
    public void iCallMethod(String methodName){
        RestMethod restMethod;
        switch(methodName.toUpperCase()){
            case "POST":
                restMethod = service.get().getPostMethod();
                break;
            case "GET":
                restMethod = service.get().getGetMethod();
                break;
            case "PUT":
                restMethod = service.get().getPutMethod();
                break;
            case "DELETE":
                restMethod = service.get().getDelete();
                break;
            case "PATCH":
                restMethod = service.get().getPatch();
                break;
            case "STATUS":
                restMethod = service.get().getStatus();
                break;
            default:
                return;
        }
        restResponse.set(restMethod.call());
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

    @And("^Average response time is lesser than (\\d+)$")
    public void averageResponseTime(long seconds) {
        Assert.assertTrue(performanceResult.get().AverageResponseTime < seconds);
    }
}
