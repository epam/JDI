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

    @RequestMapping(value = "/case/suit/{suitId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addCaseToSuit(@PathVariable long suitId, @RequestBody CaseDTO caseArg) {
        casesService.addCaseToSuit(caseArg, suitId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/case/{caseId}/suit/{suitId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeCase(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId) {
        casesService.removeCase(suitId, caseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/case/{caseId}/suit/{suitId}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> updateCase(@PathVariable("caseId") long caseId, @PathVariable("suitId") long suitId, @RequestBody CaseDTO caseArg) {
        casesService.updateCase(suitId, caseArg);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/case/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<CaseDTO> getCase(@PathVariable long caseId) {

        return new ResponseEntity<>(casesService.getCase(caseId), HttpStatus.OK);
    }

}