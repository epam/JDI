package com.epam.http.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;

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
