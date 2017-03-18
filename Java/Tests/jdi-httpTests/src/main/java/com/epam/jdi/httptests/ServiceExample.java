package com.epam.jdi.httptests;

import com.epam.http.annotations.GET;
import com.epam.http.annotations.POST;
import com.epam.http.annotations.ServiceDomain;
import com.epam.http.requests.RestMethod;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
@ServiceDomain("http://service.com")
public class ServiceExample {
    @GET("/color/get")
    public RestMethod getColor;
    @POST("/color/change/100")
    public RestMethod changeColor;

}
