package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.RestMethod;
import cucumber.api.java.en.When;

import static com.epam.http.RestLoad.loadService;
import static com.epam.jdi.http.cucumber.Utils.*;

public class ServiceStepsEN {

    @When("^I load service for (\\d+) sec with get requests$")
    public void loadServiceForSecWithGetRequests(int seconds) {
        RestMethod getMethod = service.get().getGetMethod();
        performanceResult.set(loadService(seconds, getMethod));
    }

    @When("^I do status request with (\\d+) code$")
    public void iCallStatusRequest(int status) {
        restResponse.set(service.get().getStatus().call(String.valueOf(status)));
    }
}
