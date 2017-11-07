package com.epam.http.requests;

import com.epam.commons.DataClass;
import com.jayway.restassured.response.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RequestData extends DataClass<RequestData> {
    public String url = null;
    public String body = null;
    public List<Header> headers = new ArrayList<>();
}
