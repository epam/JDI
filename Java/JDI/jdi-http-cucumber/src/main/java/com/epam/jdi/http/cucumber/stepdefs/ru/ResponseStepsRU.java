package com.epam.jdi.http.cucumber.stepdefs.ru;

import com.epam.http.requests.ResponseStatusType;
import cucumber.api.java.ru.Тогда;
import cucumber.api.java.ru.И;
import org.testng.Assert;

import static com.epam.jdi.http.cucumber.Utils.performanceResult;
import static com.epam.jdi.http.cucumber.Utils.restResponse;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class ResponseStepsRU {

    @Тогда("^Результаты не содержат ошибок$")
    public void performanceResultsDonTHaveAnyFails() {
        Assert.assertTrue(performanceResult.get().NoFails());
    }

    @И("^Я проверяю число запросов$")
    public void iCheckNumberOfRequests() {
        System.out.println("There were " + performanceResult.get().NumberOfRequests + " requests.");
    }

    @Тогда("^Код ответа равен (\\d+)$")
    public void responseStatusCodeEquals(int statusCode){
        assertEquals(restResponse.get().status().code(), statusCode);
    }

    @И("^Тело ответа пустое")
    public void responseBodyIs() {
        assertEquals(restResponse.get().body(), "");
    }

    @И("^Статус ответа это ([^\"]*)$")
    public void responseStatusTypeIs(String type) {
        Boolean typeMatches = false;
        for (ResponseStatusType responseStatusType : ResponseStatusType.values()) {
            if(type.equalsIgnoreCase(responseStatusType.name()))
                typeMatches = true;
        }
        Assert.assertTrue(typeMatches);
    }

    @И("^Параметр ответа \"([^\"]*)\" равен \"([^\"]*)\"$")
    public void responseParameterIsValue(String parameter, String value) {
        restResponse.get().assertThat().body(parameter, equalTo(value));
    }

    @Тогда("^Проверяю, содержат ли результаты ошибки$")
    public void iCheckIfPerformanceResultsContainAnyFails() {
        long numberOfFails = performanceResult.get().NumberOfFails;
        if (numberOfFails == 0)
            System.out.println("There were no failures.");
        else if (numberOfFails == 1)
            System.out.println("There was 1 failure.");
        else
            System.out.println("There were " + numberOfFails + " failures.");
    }

    @И("^Срднее время ответа не превышает (\\d+) сек$")
    public void averageResponseTime(long seconds) {
        Assert.assertTrue(performanceResult.get().AverageResponseTime < seconds);
    }

    @И("^Параметр заголовка ответа \"([^\"]*)\" равен \"([^\"]*)\"$")
    public void responseHeaderIs(String parameter, String value) {
        restResponse.get().assertThat().header(parameter, value);
    }

    @И("^Параметр json ответа \"([^\"]*)\" равен \"([^\"]*)\"$")
    public void jsonResponseIs(String parameter, String value){
        assertEquals(restResponse.get().jsonBody(parameter), value);
    }

    @И("^Я печатаю ответ на запрос$")
    public void iPrintResponse() {
        restResponse.get().body();
    }
}
