package com.epam.jdi.httptests;

import com.epam.http.PerformanceResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.RestLoad.loadService;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.ServiceExample.getMethod;


public class PerformanceTests {

    @Test
    public void isAliveTest() {
        init(ServiceExample.class);
        Assert.assertTrue(getMethod.isAlive());
    }
    @Test
    public void printTest() {
        init(ServiceExample.class);
        PerformanceResult pr = loadService(20000, getMethod);
        Assert.assertTrue(pr.NoFails());
        System.out.println(pr.AverangeResponseTime);
        System.out.println(pr.NumberOfRquests);

    }
}
