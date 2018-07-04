package com.epam.http.requests;

import com.epam.commons.PrintUtils;
import com.epam.commons.linqinterfaces.JActionT;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.http.annotations.QueryParameter;
import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.logger;
import static com.epam.http.requests.RestRequest.doRequest;
import static com.epam.http.response.ResponseStatusType.OK;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;


/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestMethod<T> {
    public RequestSpecification spec = given();
    private RequestData data;
    private RestMethodTypes type;
    private Gson gson = new Gson();
    private ResponseStatusType expectedStatus = OK;

    public RestMethod() {}
    public RestMethod(JActionT<RequestSpecification> specFunc, RestMethodTypes type) {
        specFunc.invoke(spec);
        this.type = type;
    }
    public RestMethod(RestMethodTypes type, String url) {
        this(type, new RequestData().set(d -> d.url = url));
    }
    public RestMethod(RestMethodTypes type, RequestData data) {
        this.data = data;
        this.type = type;
    }
    public void addHeader(String name, String value) {
        data.headers.add(name, value);
    }
    public void addHeader(com.epam.http.annotations.Header header) {
        addHeader(header.name(), header.value());
    }
    public void addHeader(Header header) {
        addHeader(header.getName(), header.getValue());
    }
    public void addHeaders(com.epam.http.annotations.Header... headers) {
        for(com.epam.http.annotations.Header header : headers)
            addHeader(header);
    }
    public void setContentType(ContentType ct) {
        data.contentType = ct;
    }
    public void addHeaders(Header[] headers) {
        for(Header header : headers)
            addHeader(header);
    }
    public RestMethod expectStatus(ResponseStatusType status) {
        expectedStatus = status; return this;
    }

    void addQueryParameters(QueryParameter... params) {
        data.queryParams.addAll(new MapArray<>(params,
            QueryParameter::name, QueryParameter::value));
    }

    public RestResponse call() {
        if (type == null)
            throw exception("HttpMethodType not specified");
        RequestSpecification spec = getSpec().log().all();
        logger.info(format("Do %s request %s", type, data.url));
        return doRequest(type, spec, expectedStatus);
    }
    public T callAsData(Class<T> c) {
        try {
            return call().raResponse().body().as(c);
        } catch (Exception ex) {
            throw new RuntimeException("Can't convert response in " + c.getSimpleName());
        }
    }
    public T asData(Class<T> c) {
        return callAsData(c);
    }
    public RestResponse postData(T data) {
        this.data.body = gson.toJson(data);
        getSpec().body(this.data.body);
        return call();
    }

    public RestResponse call(String... params) {
        if (data.url.contains("%s") && params.length > 0)
            data.url = format(data.url, params);
        return call();
    }
    public RestResponse post(String body) {
        return call(new RequestData().set(rd -> rd.body = body));
    }

    public RestResponse call(RequestData requestData) {
        if (!requestData.pathParams.isEmpty())
            data.pathParams.addAll(requestData.pathParams);
        if (!requestData.queryParams.isEmpty())
            data.queryParams.addAll(requestData.queryParams);
        if (requestData.body != null)
            data.body = requestData.body;
       return call();
    }
    public RequestSpecification getSpec() {
        if (data == null)
            return spec;
        if (data.pathParams.any() && data.url.contains("{"))
            for (Pair<String, String> param : data.pathParams)
                data.url = data.url.replaceAll("\\{" + param.key + "}", param.value);
        spec.contentType(data.contentType);
        spec.baseUri(data.url);
        if (data.queryParams.any()) {
            spec.queryParams(data.queryParams.toMap());
            data.url += "?" + PrintUtils.print(data.queryParams.toMap(), "&", "{0}={1}");
        }
        if (data.body != null)
            spec.body(data.body);
        if (data.headers.any())
            spec.headers(data.headers.toMap());
        return spec;
    }
    public boolean isAlive() {
        return isAlive(2000);
    }
    public boolean isAlive(int liveTimeMSec) {
        long start = currentTimeMillis();
        ResponseStatusType status;
        do { status = call().status.type;
        } while (status != OK && currentTimeMillis() - start < liveTimeMSec);
        return status == OK;
    }
}
