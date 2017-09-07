package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.services.CaseService;
import com.epam.test_generator.services.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CaseController {

    @Autowired
    private CaseService casesService;

    @Autowired
    private SuitService suitService;

    @RequestMapping(value = "/addCase/{suitId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addCaseToSuit(@PathVariable int suitId, @RequestBody Case caseArg){
        casesService.addCaseToSuit(caseArg,suitId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeCase(@PathVariable long caseId){
        casesService.removeCase(caseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCase/{suitId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> updateCase(@RequestBody Case caseArg){
        casesService.updateCase(caseArg);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<Case> getCase(@PathVariable long caseId){

        return new ResponseEntity<Case>(casesService.getCase(caseId), HttpStatus.OK);
    }

}