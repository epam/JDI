package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.services.CaseService;
import com.epam.test_generator.services.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping(value = "/updateCase/{suitId}", method = RequestMethod.POST, consumes = "application/json")
//    public ResponseEntity<Void> updateCase(@RequestBody Case caseArg){
//        casesService.updateCase(caseArg);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @RequestMapping(value = "/getCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<Case> getCase(@PathVariable long caseId){

        return new ResponseEntity<Case>(casesService.getCase(caseId), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCase", method = RequestMethod.POST,produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    String Submit(@RequestParam("suitID") String suitId,
                  @RequestParam("caseID") String caseId,
                  @RequestParam("description") String caseDescription,
                  @RequestParam("priority") String casePriority, @RequestParam("code") String caseScenarioCode) {

        System.out.println("We're inside controller");
        casesService.updateCase(new Case(Long.parseLong(caseId), caseDescription,caseScenarioCode, suitService.getSuit(Long.parseLong(suitId))));

        return "" ;
    }
}