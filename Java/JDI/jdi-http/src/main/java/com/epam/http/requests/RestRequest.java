package com.epam.http.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.ExceptionHandler.exception;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestRequest {
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec) {
        spec.log().uri();
        Response response;
        long time;
        try {
            time = currentTimeMillis();
            response = methodType.method.apply(spec);
            time = currentTimeMillis() - time;
        } catch (Exception ex) { throw exception("Can't do %s request (%s)", methodType, printRS(spec)); }
        return new RestResponse(response, time);
    }
    private static String printRS(RequestSpecification rs) {
        return "";
    }
}
