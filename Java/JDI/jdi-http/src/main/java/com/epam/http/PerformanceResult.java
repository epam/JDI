package com.epam.http;

import com.epam.http.requests.RestResponse;

import static com.epam.http.requests.ResponseStatusType.ERROR;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
public class PerformanceResult {

    public long AverangeResponseTime = 0;
    public long NumberOfRquests = 0;
    public long NumberOfFails = 0;
    public boolean NoFails() {
        return NumberOfFails == 0;
    }

    public void addResult(RestResponse response) {
        AverangeResponseTime = (AverangeResponseTime * NumberOfRquests + response.responseTime())
                / (NumberOfRquests + 1);
        NumberOfRquests++;
        if (response.status().type() == ERROR)
            NumberOfFails++;

    }
}
