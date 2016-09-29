package com.epam.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.security.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Roman_Iovlev on 9/24/2016.
 */
public class Http {

    public static CloseableHttpClient httpClient = HttpClients.createMinimal();

    public static HttpResponse Get(String url) {
        return sendRequest(new HttpGet(url));
    }
    public static HttpResponse Get(URI url) {
        return sendRequest(new HttpGet(url));
    }

    public static HttpResponse Post(String url) {
        return sendRequest(new HttpPost(url));
    }
    public static HttpResponse Post(String url, Map<String, String> urlParams) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(GetEntity(urlParams));
        return sendRequest(httpPost);
    }
    public static HttpResponse Post(URI url) {
        HttpPost httpPost = new HttpPost(url);
        return sendRequest(httpPost);
    }
    public static HttpResponse Post(URI url, Map<String, String> urlParams) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(GetEntity(urlParams));
        return sendRequest(httpPost);
    }

    private static HttpEntity GetEntity(Map<String, String> urlParams) {
        List<NameValuePair> listParams = urlParams.entrySet().stream()
                .map(param -> new BasicNameValuePair(param.getKey(), param.getValue())).collect(Collectors.toList());
        return new UrlEncodedFormEntity(listParams, Consts.UTF_8);
    }

    public static void CloseConnection() {
        try { httpClient.close();
        } catch (Exception ignore) { }
    }
    private static HttpResponse sendRequest(HttpUriRequest request) {
        try {
            long start = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(request);
            return new HttpResponse(response, System.currentTimeMillis() - start);
        } catch (Exception ex) { return new HttpResponse(); }
    }

}
