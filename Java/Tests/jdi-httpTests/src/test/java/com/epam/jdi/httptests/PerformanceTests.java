package com.epam.jdi.httptests;

import com.epam.http.PerformanceResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.RestLoad.loadService;
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
        PerformanceResult pr = loadService(20000, getInfo);
        Assert.assertTrue(pr.NoFails());
        System.out.println(pr.AverageResponseTime);
        System.out.println(pr.NumberOfRequests);

    }
}
