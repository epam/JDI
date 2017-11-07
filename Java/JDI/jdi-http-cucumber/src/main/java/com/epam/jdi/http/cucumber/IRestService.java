package com.epam.jdi.http.cucumber;

import com.epam.http.requests.RestMethod;

public interface IRestService {
    RestMethod getGetMethod();

    RestMethod getPostMethod();

    RestMethod getPutMethod();

    RestMethod getPatch();

    RestMethod getDelete();

    RestMethod getStatus();
}
