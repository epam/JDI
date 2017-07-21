package com.epam.http.requests;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestMethodTypes.*;
import static com.jayway.restassured.RestAssured.with;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestRequest {
    private static RequestSpecification getRS(Map<String, String> params) {
        RequestSpecification rs = with();
        if (params != null && params.size() > 0)
            rs = rs.params(params);
        return rs;
    }
    public static RestResponse doRequest(RestMethodTypes methodType, String url, RequestParams params) {
        Response response;
        long time;
        try {
            String requestUrl = url;
            if (url.contains("%s"))
                if (params.stringParams != null && params.stringParams.length > 0)
                    requestUrl = format(requestUrl, params.stringParams);
            time = currentTimeMillis();
            response = methodType.method.apply(getRS(params.urlParams), requestUrl);
            time = currentTimeMillis() - time;
        } catch (Exception ex) { throw exception("Can't do %s request to '%s' with params (%s)", methodType, url, params); }
        return new RestResponse(response, time);
    }
    public static RestResponse GetRequest(String url, RequestParams params) {
        return doRequest(GET, url, params);
    }
    public static RestResponse PostRequest(String url, RequestParams params) {
        return doRequest(POST, url, params);
    }
    public static RestResponse PutRequest(String url, RequestParams params) {
        return doRequest(PUT, url, params);
    }
    public static RestResponse DeleteRequest(String url, RequestParams params) {
        return doRequest(DELETE, url, params);
    }
    public static RestResponse PatchRequest(String url, RequestParams params) {
        return doRequest(PATCH, url, params);
    }
}
