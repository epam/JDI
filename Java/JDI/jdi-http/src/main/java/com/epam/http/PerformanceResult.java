package com.epam.http;

/**
 * Created by Roman_Iovlev on 9/25/2016.
 */
public class PerformanceResult {

    public double AverangeResponseTime = 0;
    public long NumberOfRquests = 0;
    public long NumberOfFails = 0;
    public boolean NoFails() {
        return NumberOfFails == 0;
    }

    public void addResult(HttpResponse response) {
        AverangeResponseTime = (AverangeResponseTime * NumberOfRquests + response.ResponseTIme) / (NumberOfRquests + 1);
        NumberOfRquests++;
        if (response.getResultStatus() == HttpStatus.ERROR)
            NumberOfFails++;

    }
}
