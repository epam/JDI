package com.epam.http.requests;

import com.epam.commons.DataClass;
import com.epam.commons.linqinterfaces.JActionT;
import com.epam.commons.map.MapArray;
import io.restassured.http.ContentType;

import static io.restassured.http.ContentType.ANY;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RequestData extends DataClass<RequestData> {
    public String url = null;
    public String body = null;
    public ContentType contentType = ANY;
    public MapArray<String, String> headers = new MapArray<>();
    public MapArray<String, String> pathParams = new MapArray<>();
    public MapArray<String,String> queryParams = new MapArray<>();


    public static RequestData requestData(JActionT<RequestData> valueFunc) {
        return new RequestData().set(valueFunc);
    }
    public static RequestData requestBody(String body) {
        return new RequestData().set(rd -> rd.body = body);
    }
    public static RequestData requestParams(Object[][] params) {
        return new RequestData().set(rd -> rd.pathParams = new MapArray<>(params));
    }
    public static RequestData requestParams(String paramName, String paramValue) {
        return requestParams(new Object[][] { {paramName, paramValue}});
    }
}
