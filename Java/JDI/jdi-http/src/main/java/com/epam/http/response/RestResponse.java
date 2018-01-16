package com.epam.http.response;

import com.epam.commons.linqinterfaces.JActionT;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.function.Function;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.JdiHttpSettigns.logger;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestResponse{
    private final Response raResponse;
    private final long responseTimeMSec;
    public String body = null;
    public ResponseStatus status = null;
    public String contenType = "";

    public RestResponse() {
        this.raResponse = null;
        responseTimeMSec = 0;
    }
    public static RestResponse Response() {
        return new RestResponse();
    }
    public RestResponse(Response raResponse) {
        this(raResponse, 0);
    }
    public RestResponse(Response raResponse, long time) {
        this.raResponse = raResponse;
        responseTimeMSec = time;
        body = raResponse.body().asString();
        status = new ResponseStatus(raResponse);
        contenType = raResponse.contentType();
        logger.info(toString());
    }
    public RestResponse set(JActionT<RestResponse> valueFunc) {
        RestResponse thisObj = (RestResponse)this;
        valueFunc.invoke(thisObj);
        return thisObj;
    }

    public boolean verify(Function<RestResponse, Boolean> validator) {
        return validator.apply(this);
    }
    public void validate(Function<RestResponse, Boolean> validator) {
        if (!verify(validator))
            throw exception("Bad raResponse: " + toString());
    }

    public ValidatableResponse isOk() {
        isStatus(ResponseStatusType.OK);
        return assertThat();
    }
    public ValidatableResponse hasErrors() {
        isStatus(ResponseStatusType.ERROR);
        return assertThat();
    }
    public ValidatableResponse isStatus(ResponseStatusType type) {
        validate(r -> status.type == type);
        return assertThat();
    }
    public ValidatableResponse isEmpty() {
        validate(r -> body.equals(""));
        return assertThat();
    }
    public ValidatableResponse assertBody(MapArray<String, Matcher<?>> params) {
        ValidatableResponse vr = assertThat();
        try {
            for (Pair<String, Matcher<?>> pair : params)
                vr.body(pair.key, pair.value);
            return vr;
        } catch (Exception ex) { throw new RuntimeException("Only <String, Matcher> pairs available for assertBody"); }
    }
    public ValidatableResponse assertBody(Object[][] params) {
        return assertBody(new MapArray<>(params));
    }

    public String getFromHtml(String path) {
        return raResponse.body().htmlPath().getString(path);
    }

    public List<Header> headers() { return raResponse.getHeaders().asList(); }
    public String cookie(String name) { return raResponse.getCookie(name); }

    public Response raResponse() { return raResponse; }
    public long responseTime() { return responseTimeMSec; }

    public ValidatableResponse assertThat() { return raResponse.then(); }

    public RestResponse assertStatus(ResponseStatus rs) {
        String errors = "";
        if (status.code != rs.code)
            errors += format("Wrong status code %s. Expected: %s", status.code, rs.code) + LINE_BREAK;
        if (!status.type.equals(rs.type))
            errors += format("Wrong status type %s. Expected: %s", status.type, rs.type) + LINE_BREAK;
        if (!status.text.equals(rs.text))
            errors += format("Wrong status text %s. Expected: %s", status.text, rs.text);
        if (!errors.equals(""))
            throw exception(errors);
        return this;
    }
    @Override
    public String toString() {
        return format("Response status: %s %s (%s)", status.code, status.text, status.type) + LINE_BREAK +
               "Response body: " + body;
    }
}
