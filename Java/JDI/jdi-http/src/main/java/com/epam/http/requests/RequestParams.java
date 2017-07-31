package com.epam.http.requests;

/**
 * Created by Roman_Iovlev on 7/21/2017.
 */
public class RequestParams {
    public UrlParams urlParams;
    public String[] stringParams;
    public Headers headers;

    public RequestParams(UrlParams urlParams) {
        this.urlParams = urlParams;
    }
    public RequestParams(String[] stringParams) {
        this.stringParams = stringParams;
    }
    public RequestParams(Headers urlParams) {
        this.headers = headers;
    }
}
