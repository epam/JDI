package com.epam.test_generator.controllers;


import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StepController {

    @Autowired
    private StepService stepService;

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}/step", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addStepToCase(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId, @RequestBody StepDTO stepDTO) {
        stepService.addStep(stepDTO, caseId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}/steps", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<StepDTO> getStepsByCaseId(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId) {
        return stepService.getStepsByCaseId(caseId);
    }

    @RequestMapping(value = "/suit/{suitId}/case/{caseId}/steps", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> saveStepsForCaseId(@PathVariable("suitId") long suitId, @PathVariable("caseId") long caseId, @RequestBody List<StepDTO> steps) {
        stepService.removeAllSteps(caseId);
        stepService.addSteps(caseId, steps);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
