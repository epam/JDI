package com.epam.jdi.http.cucumber.stepdefs.en;

import com.epam.http.requests.RestMethod;
import com.jayway.restassured.http.ContentType;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.epam.jdi.http.cucumber.Utils.*;

public class RequestStepsEN {

    @Then("^I verify that ([^\"]*) method is alive$")
    public void theGetMethodIsAlive(String methodName){
        RestMethod getMethod = getRestMethod(methodName);
        Assert.assertTrue(getMethod.isAlive());
    }

    @When("^I do ([^\"]*) request$")
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
        if (preparedHeader.get() != null)
        {
            for (Map.Entry<String, String> entry : preparedHeader.get().entrySet()){
                restMethod.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (requestContentType.get() != null)
            restMethod.spec.contentType(requestContentType.get());
        restResponse.set(restMethod.call());
    }

    @Given("^I have the following headers:$")
    public void iHaveTheFollowingHeaders(DataTable headers) {
        preparedHeader.set(null);
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String, String> entry : headers.asMap(String.class, String.class).entrySet()){
            hashMap.put(entry.getKey(), entry.getValue());
        }
        preparedHeader.set(hashMap);
    }

    @And("^I set ([^\"]*) request content type$")
    public void iSetJSONRequestContentType(String type) {
        for (ContentType contentType : ContentType.values()) {
            if(type.equalsIgnoreCase(contentType.name()))
                requestContentType.set(contentType);
        }
    }
}
