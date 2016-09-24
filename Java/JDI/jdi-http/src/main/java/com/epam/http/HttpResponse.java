package com.epam.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman_Iovlev on 9/24/2016.
 */
public class HttpResponse {
    public String Body;
    public int Status;
    public String Protocol;
    public Map<String, String> Headers;
    public HttpStatus getResultStatus() {
        if (Status / 100 == 2)
            return HttpStatus.OK;
        if (Status / 100 == 3)
            return HttpStatus.REDIRECT;
        return HttpStatus.ERROR;
    }
    public org.apache.http.HttpResponse ApacheResponse;

    public HttpResponse() {
        Body = "";
        Status = 0;
        Protocol = "";
        Headers = new HashMap<>();
    }

    public HttpResponse(org.apache.http.HttpResponse response) {
        HttpEntity entity = response.getEntity();
        try {
            Body = entity != null ? EntityUtils.toString(entity) : "";
        } catch (Exception ex) { Body = ""; }
        Status = response.getStatusLine().getStatusCode();
        Protocol = response.getStatusLine().getProtocolVersion().toString();
        Headers = new HashMap<>();
        for (Header header : response.getAllHeaders()) {
            if (!Headers.containsKey(header.getName()))
                Headers.put(header.getName(), header.getValue());
        }
    }

    public <TJson> TJson asJson(Class<TJson> jsonType) {
        HttpEntity entity = ApacheResponse.getEntity();
        if (entity == null)
            return null;
        Gson gson = new GsonBuilder().create();
        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();
        Reader reader;
        try { reader = new InputStreamReader(entity.getContent(), charset);
        } catch (Exception ex) { return null; }
        return gson.fromJson(reader, jsonType);
    }

    public Document asXml() {
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = fact.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(Body));
            return builder.parse(source);
        } catch (Exception ex) { return null; }
    }
}
