package com.epam.jdi.httptests;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
@ServiceDomain("http://httpbin.org/")
public class ServiceExample {
    @GET("/get")
    static RestMethod getMethod;
    @POST("/post")
    RestMethod postMethod;
    @PUT("/put")
    RestMethod putMethod;
    @PATCH("/patch")
    RestMethod patch;
    @DELETE("/delete")
    RestMethod delete;
    @GET("/status/%s")
    RestMethod status;

}
