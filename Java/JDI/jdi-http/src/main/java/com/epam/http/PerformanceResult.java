package com.epam.http;

import com.epam.http.requests.RestResponse;

import static com.epam.http.requests.ResponseStatusType.ERROR;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
public class PerformanceResult {

    public long AverageResponseTime = 0;
    public long NumberOfRequests = 0;
    public long NumberOfFails = 0;
    public boolean NoFails() {
        return NumberOfFails == 0;
    }

    public void addResult(RestResponse response) {
        AverageResponseTime = (AverageResponseTime * NumberOfRequests + response.responseTime())
                / (NumberOfRequests + 1);
        NumberOfRequests++;
        if (response.status().type() == ERROR)
            NumberOfFails++;

    }
}
