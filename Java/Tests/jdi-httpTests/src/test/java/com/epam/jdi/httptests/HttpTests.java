package com.epam.jdi.httptests;

import com.epam.http.PerformanceResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.RestLoad.loadService;
import static com.epam.jdi.httptests.EpamService.main;


public class HttpTests {

    @Test
    public void isAliveTest() {
        Assert.assertTrue(main.isAlive());
    }
    @Test
    public void printTest() {
        Assert.assertTrue(!main.get().body.equals(""));
        PerformanceResult pr = loadService(2000, main);
        Assert.assertTrue(pr.NoFails());
        System.out.println(pr.AverangeResponseTime);
        System.out.println(pr.NumberOfRquests);

    }
}
