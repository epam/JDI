package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.AutoCompleteDTO;
import com.epam.test_generator.services.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AutoCompleteController {

    @Autowired
    AutoCompleteService autoCompleteService;

    @RequestMapping(value = "/manageSteps")
    public String getMainPage() {
        return "/WEB-INF/static/views/manageSteps";
    }

    @RequestMapping(value = "/getAutoCompleteList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AutoCompleteDTO>> getAutoCompleteList() {
        return new ResponseEntity<>(autoCompleteService.getAutoCompleteList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addAutoComplete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> addAutoComplete(@RequestBody AutoCompleteDTO autoCompleteDTO) {
        autoCompleteService.addAutoComplete(autoCompleteDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeAutoComplete/{autoCompleteId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> removeAutoComplete(@PathVariable("autoCompleteId") long autoCompleteId) {
        autoCompleteService.removeAutoComplete(autoCompleteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
