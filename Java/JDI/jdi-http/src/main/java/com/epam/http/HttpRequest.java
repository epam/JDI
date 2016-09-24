package com.epam.http;

import java.net.URI;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Roman_Iovlev on 9/24/2016.
 */
public class HttpRequest {
    private String domain;
    private Map<String, String> urlParams;

    public HttpRequest(String url) {
        domain = url;
    }
    public HttpRequest(String url, Map<String, String> urlParams) {
        domain = url;
        this.urlParams = urlParams;
    }
    public HttpRequest(URI url) {
        this(url.toString());
    }
    public HttpRequest(URI url, Map<String, String> urlParams) {
        this(url);
        this.urlParams = urlParams;
    }

    public HttpResponse get() {
        return Http.Get(domain);
    }

    public HttpResponse post() {
        return urlParams == null
            ? Http.Post(domain)
            : Http.Post(domain, urlParams);
    }
    public boolean isAlive() {
        return isAlive(2000);
    }
    public boolean isAlive(int liveTimeMSec) {
        long start = currentTimeMillis();
        HttpStatus status;
        do { status = get().getResultStatus();
        } while (status != HttpStatus.OK && currentTimeMillis() - start < liveTimeMSec);
        return status == HttpStatus.OK;
    }
}
