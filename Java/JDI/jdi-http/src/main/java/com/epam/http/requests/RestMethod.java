package com.epam.http.requests;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestRequest.*;
import static com.epam.http.requests.RestStatusType.OK;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestMethod {
    private String url;
    private Headers header;
    private RestMethodTypes type;

    public RestMethod(String url) {
        this.url = url;
    }
    public RestMethod(String url, Headers header, RestMethodTypes type) {
        this.url = url;
        this.header = header;
        this.type = type;
    }
    public RestMethod(RestMethodData data) {
        this.url = data.uri;
        this.header = null;
        this.type = data.type;
    }

    private RestResponse callMethod(RequestParams params) {
        if (type == null)
            throw exception("HttpMethodType not specified");
        return doRequest(type, url, params);
    }

    public RestResponse call() {
        return call(new RequestParams(header));
    }
    public RestResponse call(RequestParams params) {
        return callMethod(params);
    }
    public RestResponse call(String... params) {
        return call(new RequestParams(params));
    }
    public RestResponse call(UrlParams params) {
        return call(new RequestParams(params));
    }
    public RestResponse call(Headers params) {
        return call(new RequestParams(params));
    }
    public RestResponse GET() {
        return GET(new RequestParams(header));
    }
    public RestResponse GET(RequestParams params) {
        return GetRequest(url, params);
    }
    public RestResponse GET(String... params) {
        return GET(new RequestParams(params));
    }
    public RestResponse GET(UrlParams params) {
        return GET(new RequestParams(params));
    }
    public RestResponse GET(Headers params) {
        return GET(new RequestParams(params));
    }
    public RestResponse POST() {
        return POST(new RequestParams(header));
    }
    public RestResponse POST(RequestParams params) {
        return PostRequest(url, params);
    }
    public RestResponse POST(String... params) {
        return POST(new RequestParams(params));
    }
    public RestResponse POST(UrlParams params) {
        return POST(new RequestParams(params));
    }
    public RestResponse POST(Headers params) {
        return POST(new RequestParams(params));
    }
    public RestResponse PUT() {
        return PUT(new RequestParams(header));
    }
    public RestResponse PUT(RequestParams params) {
        return PutRequest(url, params);
    }
    public RestResponse PUT(String... params) {
        return PUT(new RequestParams(params));
    }
    public RestResponse PUT(UrlParams params) {
        return PUT(new RequestParams(params));
    }
    public RestResponse PUT(Headers params) {
        return PUT(new RequestParams(params));
    }
    public RestResponse DELETE() {
        return DELETE(new RequestParams(header));
    }
    public RestResponse DELETE(RequestParams params) {
        return DeleteRequest(url, params);
    }
    public RestResponse DELETE(String... params) {
        return DELETE(new RequestParams(params));
    }
    public RestResponse DELETE(UrlParams params) {
        return DELETE(new RequestParams(params));
    }
    public RestResponse DELETE(Headers params) {
        return DELETE(new RequestParams(params));
    }

    public boolean isAlive() {
        return isAlive(2000);
    }
    public boolean isAlive(int liveTimeMSec) {
        long start = currentTimeMillis();
        RestStatusType status;
        do { status = GET().statusType;
        } while (status != OK && currentTimeMillis() - start < liveTimeMSec);
        return status == OK;
    }
}
