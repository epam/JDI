package com.epam.http.requests;

import com.epam.http.response.RestResponse;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.requests.RestMethodTypes.*;

/**
 * Created by Roman_Iovlev on 12/15/2017.
 */
public class RestMethods {
    public static RestResponse GET(RequestData data) {
        return new RestMethod(GET, data).call();
    }
    public static RestResponse GET(String url) {
        return new RestMethod(GET, url).call();
    }
    public static RestResponse GET(String url, RequestSpecification requestSpecification) {
        return new RestMethod(GET, url, requestSpecification).call();
    }
    public static RestResponse POST(RequestData data) {
        return new RestMethod(POST, data).call();
    }
    public static RestResponse POST(String url) {
        return new RestMethod(POST, url).call();
    }
    public static RestResponse POST(String url, RequestSpecification requestSpecification) {
        return new RestMethod(POST, url, requestSpecification).call();
    }
    public static RestResponse PUT(RequestData data) {
        return new RestMethod(PUT, data).call();
    }
    public static RestResponse PUT(String url) {
        return new RestMethod(PUT, url).call();
    }
    public static RestResponse PUT(String url, RequestSpecification requestSpecification) {
        return new RestMethod(PUT, url, requestSpecification).call();
    }
    public static RestResponse DELETE(RequestData data) {
        return new RestMethod(DELETE, data).call();
    }
    public static RestResponse DELETE(String url) {
        return new RestMethod(DELETE, url).call();
    }
    public static RestResponse DELETE(String url, RequestSpecification requestSpecification) {
        return new RestMethod(DELETE, url, requestSpecification).call();
    }
    public static RestResponse PATCH(RequestData data) {
        return new RestMethod(PATCH, data).call();
    }
    public static RestResponse PATCH(String url) {
        return new RestMethod(PATCH, url).call();
    }
    public static RestResponse PATCH(String url, RequestSpecification requestSpecification) {
        return new RestMethod(PATCH, url, requestSpecification).call();
    }
    public static RestResponse OPTIONS(RequestData data) {
        return new RestMethod(OPTIONS, data).call();
    }
    public static RestResponse OPTIONS(String url) {
        return new RestMethod(OPTIONS, url).call();
    }
    public static RestResponse OPTIONS(String url, RequestSpecification requestSpecification) {
        return new RestMethod(OPTIONS, url, requestSpecification).call();
    }
    public static RestResponse HEAD(RequestData data) {
        return new RestMethod(HEAD, data).call();
    }
    public static RestResponse HEAD(String url) {
        return new RestMethod(HEAD, url).call();
    }
    public static RestResponse HEAD(String url, RequestSpecification requestSpecification) {
        return new RestMethod(HEAD, url, requestSpecification).call();
    }
}
