package com.epam.jdi.http.cucumber.stepdefs.ru;

import com.epam.http.requests.RestMethod;
import com.jayway.restassured.http.ContentType;
import cucumber.api.DataTable;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.И;
import static com.epam.jdi.http.cucumber.Utils.*;

public class RequestStepsRU {

    @Тогда("^Я проверяю, что метод запроса ([^\"]*) доступен$")
    public void theGetMethodIsAlive(String methodName){
        RestMethod getMethod = getRestMethod(methodName);
        Assert.assertTrue(getMethod.isAlive());
    }

    @Когда("^Я делаю ([^\"]*) запрос$")
    public void iCallMethod(String methodName){
        RestMethod restMethod;
        switch(methodName.toUpperCase()){
            case "POST":
                restMethod = service.get().getPostMethod();
                break;
            case "GET":
                restMethod = service.get().getGetMethod();
                break;
            case "PUT":
                restMethod = service.get().getPutMethod();
                break;
            case "DELETE":
                restMethod = service.get().getDelete();
                break;
            case "PATCH":
                restMethod = service.get().getPatch();
                break;
            case "STATUS":
                restMethod = service.get().getStatus();
                break;
            default:
                return;
        }
        if (preparedHeader.get() != null)
        {
            for (Map.Entry<String, String> entry : preparedHeader.get().entrySet()){
                restMethod.addHeader(entry.getKey(), entry.getValue());
            }
        }
        restResponse.set(restMethod.call());
    }

    @Пусть("^У меня следующий заголовок запроса:$")
    public void iHaveTheFollowingHeaders(DataTable headers) {
        preparedHeader.set(null);
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String, String> entry : headers.asMap(String.class, String.class).entrySet()){
            hashMap.put(entry.getKey(), entry.getValue());
        }
        preparedHeader.set(hashMap);
    }

    @И("^Я устанавливаю ([^\"]*) форматом запроса$")
    public void iSetJSONRequestContentType(String type) {
        for (ContentType contentType : ContentType.values()) {
            if(type.equalsIgnoreCase(contentType.name()))
                requestContentType.set(contentType);
        }
    }
}
