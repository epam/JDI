package com.epam.mock.web;

import com.epam.mock.model.ConfigurationHandler;
import com.epam.mock.service.ConfigurationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Response controller class.
 */
@RestController
public class ConfigurationHandlerController {

    private ConfigurationHandlerService configurationHandlerService;

    private static final HttpHeaders httpHeaders;

    static {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Autowired
    public void setConfigurationHandlerService(ConfigurationHandlerService configurationHandlerService) {
        this.configurationHandlerService = configurationHandlerService;
    }

    /**
     * Processes HTTP.GET request (URL: .../getUsers).
     * @return ResponseEntity with string in JSON format with all users.
     */
    @RequestMapping("/getUsers")
    public ResponseEntity<String> getUsers() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForGetUsers();

        return new ResponseEntity<>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.GET request (URL: .../getUser).
     * @return ResponseEntity with string in JSON format with specified user or 'user no found' error.
     */
    @RequestMapping("/getUser")
    public ResponseEntity<String> getUser() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForGetUser();

        return new ResponseEntity<>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.POST request (URL: .../registerUser).
     * @return ResponseEntity with string in JSON format with http status result (success or not) and user id.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/registerUser")
    public ResponseEntity<String> registerUser() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForRegisterUser();

        return new ResponseEntity<>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.PUT request (URL: .../updateUser).
     * @return ResponseEntity with http status result (success or not).
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/updateUser")
    public ResponseEntity<Void> updateUser() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForUpdateUser();

        return new ResponseEntity<>(HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.PATCH request (URL: .../updateUserField).
     * @return ResponseEntity with http status result (success or not).
     */
    @RequestMapping(method = RequestMethod.PATCH, value = "/updateUserField")
    public ResponseEntity<Void> updateUserField() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForUpdateUserField();

        return new ResponseEntity<>(HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.DELETE request (URL: .../clearUser).
     * @return ResponseEntity with http status result (success or not).
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/clearUser")
    public ResponseEntity<Void> deleteUser() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForDeleteUser();

        return new ResponseEntity<>(HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Processes HTTP.DELETE request (URL: .../clearAll).
     * @return ResponseEntity with http status result (success or not).
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/clearAll")
    public ResponseEntity<Void> deleteUsers() {
        ConfigurationHandler response =  configurationHandlerService.getConfigForDeleteUsers();

        return new ResponseEntity<>(HttpStatus.valueOf(response.getResult()));
    }

    /**
     * Edit representation for method specified in sent parameter after processing HTTP.POST request (URL: .../manage).
     * @param configurationHandler Representation of response.
     * @return HttpStatus.OK if contains sent method, HttpStatus.BAD_REQUEST otherwise.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/manage")
    public ResponseEntity<Void> manage(@RequestBody ConfigurationHandler configurationHandler) {
        return configurationHandlerService.editMap(configurationHandler)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}