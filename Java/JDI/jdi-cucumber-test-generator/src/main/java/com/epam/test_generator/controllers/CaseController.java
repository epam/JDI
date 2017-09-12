package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CaseController {

    @Autowired
    private CaseService casesService;

    @RequestMapping(value = "/addCase/{suitId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addCaseToSuit(@PathVariable long suitId, @RequestBody CaseDTO caseArg) {
        if(isPriorityWrong(caseArg.getPriority()) || isDescriptionEmpty(caseArg.getDescription())){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        casesService.addCaseToSuit(caseArg, suitId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeCase(@PathVariable long caseId) {
        casesService.removeCase(caseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCase", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> updateCase(@RequestBody CaseDTO caseArg) {
        if(isPriorityWrong(caseArg.getPriority()) || isDescriptionEmpty(caseArg.getDescription())){

            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        casesService.updateCase(caseArg);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<CaseDTO> getCase(@PathVariable long caseId) {
        CaseDTO caseDTO = casesService.getCase(caseId);
        if(caseDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(caseDTO, HttpStatus.OK);
    }



    private boolean isPriorityWrong(int priority){

        return (priority < 1) || (priority > 5);
    }

    private boolean isDescriptionEmpty(String description){

        return description == null || description.length()==0;
    }

}