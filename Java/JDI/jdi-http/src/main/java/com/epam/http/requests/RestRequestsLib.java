package com.epam.http.requests;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestMethodTypes.*;
import static com.jayway.restassured.RestAssured.with;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestRequestsLib {
    private static RequestSpecification getRS(Map<String, String> params) {
        RequestSpecification rs = with();
        if (params != null && params.size() > 0)
            rs = rs.params(params);
        return rs;
    }
    private static RestResponse doRequest(RestMethodTypes methodType, String url, Map<String, String> params) {
        Response response;
        try {
            response = methodType.method.apply(getRS(params), url);
        } catch (Exception ex) { throw exception("Can't do %s request to '%s' with params (%s)", methodType, url, params); }
        return new RestResponse(response);
    }
    public static RestResponse GetRequest(String url, Map<String, String> params) {
        return doRequest(GET, url, params);
    }
    public static RestResponse PostRequest(String url, Map<String, String> params) {
        return doRequest(POST, url, params);
    }
    public static RestResponse PutRequest(String url, Map<String, String> params) {
        return doRequest(PUT, url, params);
    }
    public static RestResponse DeleteRequest(String url, Map<String, String> params) {
        return doRequest(DELETE, url, params);
    }
}
