package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.services.CaseService;
import com.epam.test_generator.services.SuitService;
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
        casesService.updateCase(caseArg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getCase/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<CaseDTO> getCase(@PathVariable long caseId) {

        return new ResponseEntity<>(casesService.getCase(caseId), HttpStatus.OK);
    }

}