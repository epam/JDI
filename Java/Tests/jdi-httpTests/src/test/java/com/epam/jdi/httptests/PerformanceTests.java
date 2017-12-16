package com.epam.jdi.httptests;

import com.epam.http.performance.PerformanceResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.performance.RestLoad.loadService;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.ServiceExample.getInfo;


public class PerformanceTests {

    @Test
    public void isAliveTest() {
        init(ServiceExample.class);
        Assert.assertTrue(getInfo.isAlive());
    }
    @Test
    public void printTest() {
        init(ServiceExample.class);
        PerformanceResult pr = loadService(5, getInfo);
        Assert.assertTrue(pr.NoFails(), "Number of fails: " + pr.NumberOfFails);
        System.out.println("Average time: " + pr.AverageResponseTime + "ms");
        System.out.println("Requests amount: " + pr.NumberOfRequests);
    }
}
