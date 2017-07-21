package com.epam.http.requests;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.function.BiFunction;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public enum RestMethodTypes {
    GET((rs, url) -> rs.get(url)),
    POST((rs, url) -> rs.post(url)),
    PUT((rs, url) -> rs.put(url)),
    DELETE((rs, url) -> rs.delete(url)),
    PATCH((rs, url) -> rs.patch(url));

    public BiFunction<RequestSpecification, String, Response> method;
    RestMethodTypes(BiFunction<RequestSpecification, String, Response> method) {
        this.method = method;
    }
}
