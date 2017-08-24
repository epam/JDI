package com.epam.mock.service;

import com.epam.mock.model.ConfigurationHandler;
import com.epam.mock.model.Method;
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
        configurationHandlerMap.put(Method.GETUSERS.toString(), new ConfigurationHandler("getusers", 200, "{\"name\":\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        configurationHandlerMap.put(Method.GETUSER.toString(), new ConfigurationHandler("getuser", 200, "{\"name\":\"roman\", \"id\":\"300\",\"role\":\"admin\"}"));
        configurationHandlerMap.put(Method.REGISTERUSER.toString(), new ConfigurationHandler("registeruser", 404, ""));
        configurationHandlerMap.put(Method.UPDATEUSER.toString(), new ConfigurationHandler("updateuser", 200, ""));
        configurationHandlerMap.put(Method.UPDATEUSERFIELD.toString(), new ConfigurationHandler("updateuserfield", 200, ""));
        configurationHandlerMap.put(Method.CLEARUSER.toString(), new ConfigurationHandler("clearuser", 404, ""));
        configurationHandlerMap.put(Method.CLEARALL.toString(), new ConfigurationHandler("clearall", 200, ""));
    }

    /**
     * Responses to controller call for all users.
     * @return Our representation of response with JSON format.
     */
    public ConfigurationHandler getConfigForGetUsers() {
        return configurationHandlerMap.get(Method.GETUSERS.toString());
    }

    /**
     * Responses to controller call for specified user.
     * @return Our representation of response with JSON format or 'user no found' error.
     */
    public ConfigurationHandler getConfigForGetUser() {
        return configurationHandlerMap.get(Method.GETUSER.toString());
    }

    /**
     * Responses to controller call for adding new user.
     * @return Our representation of response with JSON format  with status result (success or not) and user id.
     */
    public ConfigurationHandler getConfigForRegisterUser() {
        return configurationHandlerMap.get(Method.REGISTERUSER.toString());
    }

    /**
     * Responses to controller call for updating user.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler getConfigForUpdateUser() {
        return configurationHandlerMap.get(Method.UPDATEUSER.toString());
    }

    /**
     * Responses to controller call for updating user's field(s).
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler getConfigForUpdateUserField() {
        return configurationHandlerMap.get(Method.UPDATEUSERFIELD.toString());
    }

    /**
     * Responses to controller call for removing user.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler getConfigForDeleteUser() {
        return configurationHandlerMap.get(Method.CLEARUSER.toString());
    }

    /**
     * Responses to controller call for removing all users.
     * @return Our representation of response with status result (success or not).
     */
    public ConfigurationHandler getConfigForDeleteUsers() {
        return configurationHandlerMap.get(Method.CLEARALL.toString());
    }

    /**
     * Edit representation for method specified in sent parameter after controller calls for it.
     * @param configurationHandler Our representation of response.
     * @return True if mock-service contains sent method, false otherwise.
     */
    public boolean editMap(ConfigurationHandler configurationHandler) {
        String method = configurationHandler.getMethod().toUpperCase();

        if (configurationHandlerMap.containsKey(method)) {
            configurationHandlerMap.put(method, configurationHandler);

            return true;
        }

        return false;
    }
}