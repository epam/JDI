package com.epam.http.requests;

import java.util.Map;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestRequestsLib.*;
import static com.epam.http.requests.RestStatusType.OK;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestMethod {
    private String url;
    private Map<String, String> header;
    private RestMethodTypes type;

    public RestMethod(String url) {
        this.url = url;
    }
    public RestMethod(String url, Map<String, String> header, RestMethodTypes type) {
        this.url = url;
        this.header = header;
        this.type = type;
    }
    public RestMethod(RestMethodData data) {
        this.url = data.uri;
        this.header = null;
        this.type = data.type;
    }

    private RestResponse callMethod(Map<String, String> params) {
        if (type == null)
            throw exception("HttpMethodType not specified");
        switch (type) {
            case GET: return GetRequest(url, params);
            case POST: return PostRequest(url, params);
            case PUT: return PutRequest(url, params);
            case DELETE: return DeleteRequest(url, params);
        }
        return null;
    }

    public RestResponse call() {
        return callMethod(header);
    }
    public RestResponse call(Map<String, String> params) {
        return callMethod(params);
    }
    public RestResponse get() {
        return GetRequest(url, header);
    }
    public RestResponse get(Map<String, String> params) {
        return GetRequest(url, params);
    }
    public RestResponse post() {
        return PostRequest(url, header);
    }
    public RestResponse post(Map<String, String> params) {
        return PostRequest(url, params);
    }
    public RestResponse put() {
        return PutRequest(url, header);
    }
    public RestResponse put(Map<String, String> params) {
        return PutRequest(url, params);
    }
    public RestResponse delete() {
        return DeleteRequest(url, header);
    }
    public RestResponse delete(Map<String, String> params) {
        return DeleteRequest(url, params);
    }


    public boolean isAlive() {
        return isAlive(2000);
    }
    public boolean isAlive(int liveTimeMSec) {
        long start = currentTimeMillis();
        RestStatusType status;
        do { status = get().statusType;
        } while (status != OK && currentTimeMillis() - start < liveTimeMSec);
        return status == OK;
    }
}
