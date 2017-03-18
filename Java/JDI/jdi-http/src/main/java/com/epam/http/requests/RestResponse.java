package com.epam.http.requests;

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
    public long responseTImeMSec;

    public RestResponse(Response response) {
        status = response.statusCode();
        body = response.body().print();
        statusType = RestStatusType.getStatusType(status);
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
}
