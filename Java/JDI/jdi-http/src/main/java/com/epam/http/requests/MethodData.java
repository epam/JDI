package com.epam.http.requests;

import com.epam.commons.DataClass;

import static com.epam.http.requests.RestMethodTypes.GET;

/**
 * Created by Roman_Iovlev on 10/23/2017.
 */
public class MethodData extends DataClass {
    private String url = "";
    private RestMethodTypes type = GET;

    public String getUrl() {
        return url;
    }
    public RestMethodTypes getType() {
        return type;
    }
    public MethodData(String url, RestMethodTypes type) {
        this.url = url;
        this.type = type;
    }
}
