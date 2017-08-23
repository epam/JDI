package com.epam.mock.service;

import com.epam.mock.model.ConfigurationHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationHandlerService {

    private static Map<String, ConfigurationHandler> configurationHandlerMap = new HashMap<>();

    static {
        configurationHandlerMap.put("getusers", new ConfigurationHandler("getusers", 200, "{\"name\":\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        configurationHandlerMap.put("getuser", new ConfigurationHandler("getuser", 200, "{\"name\":\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        configurationHandlerMap.put("registeruser", new ConfigurationHandler("registeruser", 404, ""));
        configurationHandlerMap.put("updateuser", new ConfigurationHandler("updateuser", 200, ""));
        configurationHandlerMap.put("updateuserfield", new ConfigurationHandler("updateuserfield", 200, ""));
        configurationHandlerMap.put("clearuser", new ConfigurationHandler("clearuser", 404, ""));
        configurationHandlerMap.put("clearall", new ConfigurationHandler("clearall", 200, ""));
    }

    public ConfigurationHandler getUsers() {
        return configurationHandlerMap.get("getusers");
    }

    public ConfigurationHandler getUser() {
        return configurationHandlerMap.get("getuser");
    }

    public ConfigurationHandler addUser() {
        return configurationHandlerMap.get("registeruser");
    }

    public ConfigurationHandler updateUser() {
        return configurationHandlerMap.get("updateuser");
    }

    public ConfigurationHandler updateUserField() {
        return configurationHandlerMap.get("updateuserfield");
    }

    public ConfigurationHandler deleteUser() {
        return configurationHandlerMap.get("clearuser");
    }

    public ConfigurationHandler deleteUsers() {
        return configurationHandlerMap.get("clearall");
    }

    public boolean editMap(ConfigurationHandler configurationHandler) {
        String method = configurationHandler.getMethod().toLowerCase();

        if (configurationHandlerMap.containsKey(method)) {
            configurationHandlerMap.put(method, configurationHandler);

            return true;
        }

        return false;
    }
}