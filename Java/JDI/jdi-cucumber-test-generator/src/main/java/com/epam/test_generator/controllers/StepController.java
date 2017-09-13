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

    @RequestMapping(value = "/addStep/case/{caseId}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> addStepToCase(@RequestBody StepDTO stepDTO, @PathVariable("caseId")Long caseID){
        stepService.addStep(stepDTO, caseID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getSteps/case/{caseId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<StepDTO> getStepsByCaseId(@PathVariable Long caseId) {
        return stepService.getStepsByCaseId(caseId);
    }

    @RequestMapping(value = "/saveSteps/case/{caseId}", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<Void> saveStepsForCaseId(@PathVariable Long caseId, @RequestBody List<StepDTO> steps) {
        stepService.saveSteps(caseId, steps);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
