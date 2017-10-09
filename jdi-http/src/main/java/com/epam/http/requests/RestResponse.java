package com.epam.http.requests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

import java.util.function.Function;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestStatusType.ERROR;
import static com.epam.http.requests.RestStatusType.OK;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestResponse {
    public int status;
    public String body;
    public RestStatusType statusType;
    public long responseTimeMSec;

    public RestResponse(Response response) {
        this(response, 0);
    }
    public RestResponse(Response response, long time) {
        status = response.statusCode();
        body = response.body().print();
        statusType = RestStatusType.getStatusType(status);
        responseTimeMSec = time;
    }
    public void validate(Function<RestResponse, Boolean> validator) {
        if (!verify(validator))
            throw exception("Bad response");
    }
    public boolean verify(Function<RestResponse, Boolean> validator) {
        return validator.apply(this);
    }
    public void isOk() {
        validate(r -> statusType == OK);
    }
    public void hasError() {
        validate(r -> statusType == ERROR);
    }
    public JsonObject jsonBody() {
        return (JsonObject) new JsonParser().parse(body);
    }
    public String jsonBody(String path) {
        if (path == null || path.equals(""))
            return jsonBody().toString();
        String[] steps = path.split("\\.");
        JsonObject json = jsonBody();
        for (int i = 0; i < steps.length - 1; i++)
            json = json.getAsJsonObject(steps[i]);
        try {
            return json.getAsJsonPrimitive(steps[steps.length-1]).getAsString();
        } catch (Exception ignore) {
            return json.getAsJsonObject(steps[steps.length - 1]).toString();
        }
    }
}
