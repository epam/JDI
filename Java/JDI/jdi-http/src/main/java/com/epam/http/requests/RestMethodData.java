package com.epam.http.requests;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RestMethodData {
    public String uri = null;
    public RestMethodTypes type = null;

    public RestMethodData() { }
    public RestMethodData(String uri, RestMethodTypes type) {
        this.uri = uri;
        this.type = type;
    }
}
