package com.epam.http.requests;

import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.ResponseStatusType.ERROR;
import static com.epam.http.requests.ResponseStatusType.OK;
import static io.restassured.RestAssured.when;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestResponse {
    private final Response raResponse;
    private final long responseTimeMSec;
    public final String body;
    public final ResponseStatus status;

    public RestResponse(Response raResponse) {
        this(raResponse, 0);
    }
    public RestResponse(Response raResponse, long time) {
        this.raResponse = raResponse;
        responseTimeMSec = time;
        body = raResponse.body().print();
        status = new ResponseStatus(raResponse);
    }

    public boolean verify(Function<RestResponse, Boolean> validator) {
        return validator.apply(this);
    }
    public void validate(Function<RestResponse, Boolean> validator) {
        if (!verify(validator))
            throw exception("Bad raResponse: " + toString());
    }

    public ValidatableResponse isOk() {
        isStatus(OK);
        return assertThat();
    }
    public ValidatableResponse hasErrors() {
        isStatus(ERROR);
        return assertThat();
    }
    public ValidatableResponse isStatus(ResponseStatusType type) {
        validate(r -> status.type == type);
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
}
