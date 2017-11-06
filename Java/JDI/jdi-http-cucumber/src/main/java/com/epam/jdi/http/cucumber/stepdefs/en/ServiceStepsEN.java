package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.RestMethod;
import com.epam.http.requests.RestMethodTypes;
import cucumber.api.java.en.When;

import static com.epam.http.RestLoad.loadService;
import static com.epam.jdi.http.cucumber.Utils.*;
import static com.epam.jdi.http.cucumber.Utils.getRestMethod;

public class ServiceStepsEN {

    @When("^I load service for (\\d+) sec with get requests$")
    public void loadServiceForSecWithGetRequests(int seconds) {
        RestMethod getMethod = service.get().getGetMethod();
        performanceResult.set(loadService(seconds, getMethod));
    }

    @When("^I call status method with (\\d+) code$")
    public void iCallStatus(int status) {
        restResponse.set(service.get().getStatus().call(String.valueOf(status)));
    }
}
