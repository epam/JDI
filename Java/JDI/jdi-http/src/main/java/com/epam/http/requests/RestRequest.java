package com.epam.http.requests;

import com.epam.http.response.ResponseStatusType;
import com.epam.http.response.RestResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.verifyOkStatus;
import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestRequest {
    public static RestResponse doRequest(
            RestMethodTypes methodType, RequestSpecification spec, ResponseStatusType expectedStatus) {
        Response response;
        long time;
        try {
            time = currentTimeMillis();
            response = methodType.method.apply(spec).prettyPeek();
            time = currentTimeMillis() - time;
        } catch (Exception ex) { throw exception("Request failed"); }
        RestResponse resp = new RestResponse(response, time);
        if (verifyOkStatus)
            resp.isStatus(expectedStatus);
        return resp;
    }
    private static String printRS(RequestSpecification rs) {
        return rs.toString();
    }
}
