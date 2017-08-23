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

@RestController
public class ConfigurationHandlerController {

    @Autowired
    private ConfigurationHandlerService configurationHandlerService;

    private static final HttpHeaders httpHeaders;
    static {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @RequestMapping("/getUsers")
    public ResponseEntity<String> getUsers() {
        ConfigurationHandler response =  configurationHandlerService.getUsers();

        return new ResponseEntity<String>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping("/getUser")
    public ResponseEntity<String> getUser() {
        ConfigurationHandler response =  configurationHandlerService.getUser();

        return new ResponseEntity<String>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerUser")
    public ResponseEntity<String> addUser() {
        ConfigurationHandler response =  configurationHandlerService.addUser();

        return new ResponseEntity<String>(response.getBodyAnswer(), httpHeaders, HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateUser")
    public ResponseEntity<Void> updateUser() {
        ConfigurationHandler response =  configurationHandlerService.updateUser();

        return new ResponseEntity<Void>(HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/updateUserField")
    public ResponseEntity<Void> updateUserField() {
        ConfigurationHandler response =  configurationHandlerService.updateUserField();

        return new ResponseEntity<Void>(HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clearUser")
    public ResponseEntity<Void> deleteUser() {
        ConfigurationHandler response =  configurationHandlerService.deleteUser();

        return new ResponseEntity<Void>(HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clearAll")
    public ResponseEntity<Void> deleteUsers() {
        ConfigurationHandler response =  configurationHandlerService.deleteUsers();

        return new ResponseEntity<Void>(HttpStatus.valueOf(response.getResult()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/manage")
    public ResponseEntity<Void> manage(@RequestBody ConfigurationHandler configurationHandler) {
        return configurationHandlerService.editMap(configurationHandler)
                ? new ResponseEntity<Void>(HttpStatus.OK)
                : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
}