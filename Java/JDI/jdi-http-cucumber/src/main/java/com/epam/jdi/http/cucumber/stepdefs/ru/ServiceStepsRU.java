package com.epam.jdi.http.cucumber.stepdefs.ru;

import com.epam.http.requests.RestMethod;
import cucumber.api.java.ru.Когда;

import static com.epam.http.RestLoad.loadService;
import static com.epam.jdi.http.cucumber.Utils.*;

public class ServiceStepsRU {

    @Когда("^Я нагружаю сервис запросами на (\\d+) сек$")
    public void loadServiceForSecWithGetRequests(int seconds) {
        RestMethod getMethod = service.get().getGetMethod();
        performanceResult.set(loadService(seconds, getMethod));
    }

    @Когда("^Я делаю status запрос с (\\d+) кодом$")
    public void iCallStatusRequest(int status) {
        restResponse.set(service.get().getStatus().call(String.valueOf(status)));
    }
}
