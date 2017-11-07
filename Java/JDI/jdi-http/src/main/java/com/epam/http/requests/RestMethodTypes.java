package com.epam.http.requests;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSenderOptions;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.function.Function;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public enum RestMethodTypes {
    GET(RequestSenderOptions::get),
    POST(RequestSenderOptions::post),
    PUT(RequestSenderOptions::put),
    DELETE(RequestSenderOptions::delete),
    PATCH(RequestSenderOptions::patch);

    public Function<RequestSpecification, Response> method;
    RestMethodTypes(Function<RequestSpecification, Response> method) {
        this.method = method;
    }
}
