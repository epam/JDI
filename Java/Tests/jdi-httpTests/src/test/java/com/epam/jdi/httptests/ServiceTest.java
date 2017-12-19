package com.epam.jdi.httptests;

import com.epam.commons.map.MapArray;
import com.epam.http.response.RestResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.epam.http.requests.RequestData.requestData;
import static com.epam.http.requests.RestMethods.GET;
import static com.epam.http.requests.ServiceInit.init;
import static com.epam.http.response.ResponseStatusType.SERVER_ERROR;
import static com.epam.jdi.httptests.ServiceExample.getInfo;
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
    public void simpleRestTest() {
        RestResponse resp = ServiceExample.getInfo.call();
        resp.isOk().
            body("url", equalTo("http://httpbin.org/get")).
            body("headers.Host", equalTo("httpbin.org")).
            body("headers.Id", equalTo("Test"));
        resp.assertThat().header("Connection", "keep-alive");
    }

    @Test
    public void noServiceObjectTest() {
        RestResponse resp = GET(requestData(
            rd -> { rd.url = "http://httpbin.org/get";
                rd.headers = new MapArray<>(new Object[][] {
                    {"Name", "Roman"},
                    {"Id", "TestTest"}
                });}
        ));
        resp.isOk().header("Connection", "keep-alive");
        resp.assertBody(new Object[][] {
            {"url", equalTo("http://httpbin.org/get")},
            {"headers.Host", equalTo("httpbin.org")},
            {"headers.Id", equalTo("TestTest")}
        });
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
        assertEquals(resp.status.code, 503);
        assertEquals(resp.status.type, SERVER_ERROR);
        resp.isEmpty();
    }
    @Test
    public void staticServiceInitTest() {
        init(ServiceExample.class);
        RestResponse resp = getInfo.call();
        resp.isOk().assertThat().
                body("url", equalTo("http://httpbin.org/get")).
                body("headers.Host", equalTo("httpbin.org"));
    }
    @Test
    public void serviceInitTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse resp = service.postMethod.call();
        resp.isOk().assertThat().
                body("url", equalTo("http://httpbin.org/post")).
                body("headers.Host", equalTo("httpbin.org"));
    }

    @Test
    public void htmlBodyParseTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse resp = service.getHTMLMethod.call();
        resp.isOk();
        assertEquals(resp.getFromHtml("html.body.h1"), "Herman Melville - Moby-Dick");
    }
}
