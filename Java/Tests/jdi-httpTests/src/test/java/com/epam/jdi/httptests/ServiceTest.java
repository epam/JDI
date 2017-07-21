package com.epam.jdi.httptests;

import com.epam.http.requests.RestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.http.requests.RestStatusType.OK;
import static com.epam.http.requests.RestStatusType.SERVER_ERROR;
import static com.epam.http.requests.ServiceInit.init;

/**
 * Created by Roman_Iovlev on 7/21/2017.
 */
public class ServiceTest {
    @Test
    public void jsonTest() {
        init(ServiceExample.class);
        RestResponse resp = ServiceExample.getMethod.call();
        Assert.assertEquals(resp.status, 200);
        Assert.assertEquals(resp.statusType, OK);
        Assert.assertEquals(resp.jsonBody("url"),
                "http://httpbin.org/get");
        Assert.assertEquals(resp.jsonBody("headers.Host"),
                "httpbin.org");
        Assert.assertEquals(resp.jsonBody("headers"),
                getHeaders);
        Assert.assertEquals(trim(resp.jsonBody(null)),
                trim(getBody));
        Assert.assertEquals(trim(resp.jsonBody("")),
                trim(getBody));
    }
    private String trim(String s) {
        return s.replace(" ", "").replace("\n", "");
    }
    @Test
    public void statusTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse response = service.status.call("503");
        Assert.assertEquals(response.status, 503);
        Assert.assertEquals(response.statusType, SERVER_ERROR);
    }
    @Test
    public void staticServiceInitTest() {
        init(ServiceExample.class);
        RestResponse response = ServiceExample.getMethod.call();
        Assert.assertEquals(response.status, 200);
        Assert.assertEquals(response.statusType, OK);
        Assert.assertEquals(response.body, getBody);
    }
    @Test
    public void serviceInitTest() {
        ServiceExample service = init(ServiceExample.class);
        RestResponse response = service.postMethod.call();
        Assert.assertEquals(response.status, 200);
        Assert.assertEquals(response.statusType, OK);
        Assert.assertEquals(response.body, postBody);
    }

    String getBody = "{\n" +
            "  \"args\": {}, \n" +
            "  \"headers\": {\n" +
            "    \"Accept\": \"*/*\", \n" +
            "    \"Accept-Encoding\": \"gzip,deflate\", \n" +
            "    \"Connection\": \"close\", \n" +
            "    \"Host\": \"httpbin.org\", \n" +
            "    \"User-Agent\": \"Apache-HttpClient/4.5.2 (Java/1.8.0_131)\"\n" +
            "  }, \n" +
            "  \"origin\": \"188.187.12.6\", \n" +
            "  \"url\": \"http://httpbin.org/get\"\n" +
            "}\n";
    String getHeaders = "{\"Accept\":\"*/*\"," +
            "\"Accept-Encoding\":\"gzip,deflate\"," +
            "\"Connection\":\"close\"," +
            "\"Host\":\"httpbin.org\"," +
            "\"User-Agent\":\"Apache-HttpClient/4.5.2 (Java/1.8.0_131)\"}";
    String postBody = "{\n" +
            "  \"args\": {}, \n" +
            "  \"data\": \"\", \n" +
            "  \"files\": {}, \n" +
            "  \"form\": {}, \n" +
            "  \"headers\": {\n" +
            "    \"Accept\": \"*/*\", \n" +
            "    \"Accept-Encoding\": \"gzip,deflate\", \n" +
            "    \"Connection\": \"close\", \n" +
            "    \"Content-Length\": \"0\", \n" +
            "    \"Content-Type\": \"application/x-www-form-urlencoded; charset=ISO-8859-1\", \n" +
            "    \"Host\": \"httpbin.org\", \n" +
            "    \"User-Agent\": \"Apache-HttpClient/4.5.2 (Java/1.8.0_131)\"\n" +
            "  }, \n" +
            "  \"json\": null, \n" +
            "  \"origin\": \"188.187.12.6\", \n" +
            "  \"url\": \"http://httpbin.org/post\"\n" +
            "}\n";
}
