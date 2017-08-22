package com.epam.mock.service;

import com.epam.mock.model.JSONResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JSONResponseService {

    private static Map<String, JSONResponse> jsonResponseMap = new HashMap<>();

    static {
        jsonResponseMap.put("getUsers", new JSONResponse("getUsers", 200, "{name:\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        jsonResponseMap.put("getUser", new JSONResponse("getUser", 200, "{name:\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        jsonResponseMap.put("registerUser", new JSONResponse("registerUser", 404, ""));
        jsonResponseMap.put("updateUser", new JSONResponse("updateUser", 200, ""));
        jsonResponseMap.put("updateUserField", new JSONResponse("updateUserField", 200, ""));
        jsonResponseMap.put("clearUser", new JSONResponse("clearUser", 404, ""));
        jsonResponseMap.put("clearAll", new JSONResponse("clearAll", 200, ""));
    }

    public JSONResponse getUsers() {
        return jsonResponseMap.get("getUsers");
    }

    public JSONResponse getUser() {
        return jsonResponseMap.get("getUser");
    }

    public JSONResponse addUser() {
        return jsonResponseMap.get("registerUser");
    }

    public JSONResponse updateUser() {
        return jsonResponseMap.get("updateUser");
    }

    public JSONResponse updateUserField() {
        return jsonResponseMap.get("updateUserField");
    }

    public JSONResponse deleteUser() {
        return jsonResponseMap.get("clearUser");
    }

    public JSONResponse deleteUsers() {
        return jsonResponseMap.get("clearAll");
    }

    public void editMap(JSONResponse jsonResponse) {
        String method = jsonResponse.getMethod();

        System.out.println(jsonResponseMap.get(method));

        if (jsonResponseMap.containsKey(method)) {
            jsonResponseMap.put(method, jsonResponse);
        }
    }
}