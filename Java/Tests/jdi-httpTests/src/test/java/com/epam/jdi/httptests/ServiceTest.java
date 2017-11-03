package com.epam.jdi.httptests;

import com.epam.http.requests.RestResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.ResponseStatusType.OK;
import static com.epam.http.requests.ResponseStatusType.SERVER_ERROR;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.jdi.httptests.ServiceExample.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by Roman_Iovlev on 7/21/2017.
 */
public class ServiceTest {

    @BeforeTest
    public void before() {
        init(ServiceExample.class);
    }

    @Test
    public void jsonTest() {
        RestResponse resp = ServiceExample.getInfo.call();
        resp.assertStatus(200, OK);
        resp.assertThat().
            body("url", equalTo("http://httpbin.org/get")).
            body("headers.Host", equalTo("httpbin.org"));
        resp.assertThat().header("Connection", "keep-alive");
        assertEquals(resp.jsonBody("url"), "http://httpbin.org/get");
    }
    @Test
    public void entityTest() {
        Info e = getInfo.asData(Info.class);
        assertEquals(e.url, "http://httpbin.org/get");
        assertEquals(e.headers.Host, "httpbin.org");
        assertEquals(e.headers.Id, "Test");
        assertEquals(e.headers.Name, "Roman");
    }
    @Test
    public void statusTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse resp = service.status.call("503");
        assertEquals(resp.status().code(), 503);
        assertEquals(resp.status().type(), SERVER_ERROR);
        assertEquals(resp.body(), "");
    }
    @Test
    public void staticServiceInitTest() {
        init(ServiceExample.class);
        RestResponse resp = getInfo.call();
        resp.assertStatus(200, OK);
        resp.assertThat().
                body("url", equalTo("http://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org"));
    }
    @Test
    public void serviceInitTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse resp = service.postMethod.call();
        resp.assertStatus(200, OK);
        resp.assertThat().
                body("url", equalTo("http://httpbin.org/post")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void htmlBodyParseTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse resp = service.getHTMLMethod.call();
        resp.assertStatus(200, OK);
        assertEquals(resp.getFromHtml("html.body.h1"), "Herman Melville - Moby-Dick");
    }
}
