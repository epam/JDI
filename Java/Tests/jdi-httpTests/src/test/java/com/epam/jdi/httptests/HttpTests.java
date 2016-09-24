package com.epam.jdi.httptests;

import com.epam.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HttpTests {

    @Test
    public void isAliveTest() {
        Assert.assertTrue(new HttpRequest("https://www.epam.com/").isAlive());
    }
    @Test
    public void printTest() {
        Assert.assertTrue(!new HttpRequest("https://www.epam.com/").get().Body.equals(""));
    }
}
