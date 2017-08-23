package com.epam.mock.service;

import com.epam.mock.model.ConfigurationHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Response service class.
 */
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

    /**
     * Responses to controller call for all users.
     * @return Our representation of response with JSON format.
     */
    public ConfigurationHandler getUsers() {
        return configurationHandlerMap.get("getusers");
    }

    /**
     * Responses to controller call for specified user.
     * @return Our representation of response with JSON format or 'user no found' error.
     */
    public ConfigurationHandler getUser() {
        return configurationHandlerMap.get("getuser");
    }

    /**
     * Responses to controller call for adding new user.
     * @return Our representation of response with JSON format  with status result (success or not) and user id.
     */
    public ConfigurationHandler addUser() {
        return configurationHandlerMap.get("registeruser");
    }

    /**
     * Responses to controller call for updating user.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler updateUser() {
        return configurationHandlerMap.get("updateuser");
    }

    /**
     * Responses to controller call for updating user's field(s).
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler updateUserField() {
        return configurationHandlerMap.get("updateuserfield");
    }

    /**
     * Responses to controller call for removing user.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler deleteUser() {
        return configurationHandlerMap.get("clearuser");
    }

    /**
     * Responses to controller call for removing all users.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler deleteUsers() {
        return configurationHandlerMap.get("clearall");
    }

    /**
     * Edit representation for method specified in sent parameter after controller calls for it.
     * @param configurationHandler Our representation of response.
     * @return True if mock-service contains sent method, false otherwise.
     */
    public boolean editMap(ConfigurationHandler configurationHandler) {
        String method = configurationHandler.getMethod().toLowerCase();

        if (configurationHandlerMap.containsKey(method)) {
            configurationHandlerMap.put(method, configurationHandler);

            return true;
        }

        return false;
    }
}