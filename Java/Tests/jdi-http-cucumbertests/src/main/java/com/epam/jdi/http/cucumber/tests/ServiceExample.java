package com.epam.jdi.http.cucumber.tests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import com.epam.jdi.http.cucumber.IRestService;

import static com.jayway.restassured.http.ContentType.JSON;

@ServiceDomain("http://httpbin.org/")
public class ServiceExample implements IRestService {
    @ContentType(JSON)
    @GET("/get")
    @Headers({
            @Header(name = "Name", value = "Roman"),
            @Header(name = "Id", value = "Test")
    })
    static RestMethod getMethod;

    @Header(name = "Type", value = "Test")
    @POST("/post")
    RestMethod postMethod;

    @PUT("/put")
    RestMethod putMethod;
    @PATCH("/patch")
    RestMethod patch;
    @DELETE("/delete")
    RestMethod delete;
    @GET("/status/%s")
    RestMethod status;

    public RestMethod getGetMethod() {
        return getMethod;
    }

    public RestMethod getPostMethod() {
        return postMethod;
    }

    public RestMethod getPutMethod() {
        return putMethod;
    }

    public RestMethod getPatch() {
        return patch;
    }

    public RestMethod getDelete() {
        return delete;
    }

    public RestMethod getStatus() {
        return status;
    }
}
