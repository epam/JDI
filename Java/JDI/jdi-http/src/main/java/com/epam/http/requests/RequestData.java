package com.epam.http.requests;

import com.epam.commons.DataClass;

import static com.epam.http.requests.RestMethodTypes.GET;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class RequestData extends DataClass<RequestData> {
    public String url = null;
    public String body = null;
}
