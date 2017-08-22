package com.epam.mock.web;

import com.epam.mock.model.JSONResponse;
import com.epam.mock.service.JSONResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONResponseController {

    @Autowired
    private JSONResponseService JSONResponseService;

    @RequestMapping("/getUsers")
    public JSONResponse getUsers() {
        return JSONResponseService.getUsers();
    }

    @RequestMapping("/getUser")
    public JSONResponse getUser() {
        return JSONResponseService.getUser();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerUser")
    public JSONResponse addUser() {
        return JSONResponseService.addUser();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateUser")
    public JSONResponse updateUser() {
        return JSONResponseService.updateUser();
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/updateUserField")
    public JSONResponse updateUserField() {
        return JSONResponseService.updateUserField();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clearUser")
    public JSONResponse deleteUser() {
        return JSONResponseService.deleteUser();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clearAll")
    public JSONResponse deleteUsers() {
        return JSONResponseService.deleteUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/manage")
    public void manage(@RequestBody JSONResponse jsonResponse) {
        JSONResponseService.editMap(jsonResponse);
    }
}