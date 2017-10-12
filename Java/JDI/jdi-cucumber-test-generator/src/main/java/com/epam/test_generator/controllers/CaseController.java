package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CaseController {

    @Autowired
    private CaseService casesService;

    @RequestMapping(value = "/suit/{suitId}/case", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addCaseToSuit(@PathVariable long suitId, @RequestBody CaseDTO caseArg) {
        if(isPriorityValid(caseArg.getPriority()) && isDescriptionValid(caseArg.getDescription())) {
            casesService.addCaseToSuit(caseArg, suitId);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeCase(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId) {
        casesService.removeCase(suitId, caseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> updateCase(@PathVariable("caseId") long caseId, @PathVariable("suitId") long suitId, @RequestBody CaseDTO caseArg) {
        if(isPriorityValid(caseArg.getPriority()) && isDescriptionValid(caseArg.getDescription())) {
            casesService.updateCase(suitId, caseArg);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}", method = RequestMethod.GET)
    public ResponseEntity<CaseDTO> getCase(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId) {
        CaseDTO caseDTO = casesService.getCase(caseId);
        if (caseDTO != null) {
            return new ResponseEntity<>(caseDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/suit/{suitId}/cases", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> removeCases(@PathVariable("suitId") long suitId, @RequestBody SuitDTO suitDTO){
        List<Long> caseIds = suitDTO.getCases().stream().map(c->c.getId()).collect(Collectors.toList());
        casesService.removeCases(suitId, caseIds);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isPriorityValid(Integer priority){
		return (priority != null) && (priority >= 1) && (priority <= 5);
    }

    private boolean isDescriptionValid(String description){
		return description != null && description.length()>0 && description.length()<=255;
    }

}