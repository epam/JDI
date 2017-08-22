package com.epam.mock.model;

public class JSONResponse {

    private String method;
    private int result;
    private String bodyAnswer;

    public JSONResponse() {
    }

    public JSONResponse(String method, int result, String bodyAnswer) {
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