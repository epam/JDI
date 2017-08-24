package com.epam.mock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response template class.
 */
public class ConfigurationHandler {

    @JsonProperty("method") private String method;
    @JsonProperty("result") private int result;
    @JsonProperty("body-answer") private String bodyAnswer;

    public ConfigurationHandler() {
    }

    public ConfigurationHandler(String method, int result, String bodyAnswer) {
        this.method = method;
        this.result = result;
        this.bodyAnswer = bodyAnswer;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getBodyAnswer() {
        return bodyAnswer;
    }

    public void setBodyAnswer(String bodyAnswer) {
        this.bodyAnswer = bodyAnswer;
    }
}