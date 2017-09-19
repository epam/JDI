package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.AutoCompleteDTO;
import com.epam.test_generator.entities.AutoComplete;
import com.epam.test_generator.services.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AutoCompleteController {

    @Autowired
    AutoCompleteService autoCompleteService;

    @RequestMapping(value = "/autoCompletelist", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AutoCompleteDTO>> getAutocompleteList() {
        return new ResponseEntity<>(autoCompleteService.getAutoCompleteList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/autoComplete/{autoCompleteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AutoCompleteDTO> getAutoComplete(@PathVariable("suitId") long id){
        AutoCompleteDTO autoCompleteDTO = autoCompleteService.getAutoComplete(id);

        if (autoCompleteDTO != null) {
            return new ResponseEntity<>(autoCompleteDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/addAutoComplete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<AutoCompleteDTO> addAutoComplete(AutoCompleteDTO autoCompleteDTO) {
        return new ResponseEntity<AutoCompleteDTO>(autoCompleteService.addAutoComplete(autoCompleteDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/autoComplete/{autoCompleteId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> removeAutoComplete(@PathVariable("autoCompleteId") long id) {
        AutoCompleteDTO autoCompleteDTO = autoCompleteService.getAutoComplete(id);

        if (autoCompleteDTO != null) {
            autoCompleteService.removeAutoComplete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
