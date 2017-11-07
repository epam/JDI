package com.epam.jdi.http.cucumber.tests.steps;

import com.epam.jdi.http.cucumber.tests.ServiceExample;
import cucumber.api.java.en.Given;

import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.http.cucumber.Utils.*;

public class InitService {
    @Given("^I init service$")
    public void initService() {
        domainUrl.set(getDomain(ServiceExample.class));
        service.set(init(ServiceExample.class));
    }
}
