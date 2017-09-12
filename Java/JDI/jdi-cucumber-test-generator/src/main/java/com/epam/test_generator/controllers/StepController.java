package com.epam.test_generator.controllers;


import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StepController {

    @Autowired
    private StepService stepService;

    @RequestMapping(value = "/addStep/{caseId}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addStepToCase(@RequestBody StepDTO stepDTO, @PathVariable("caseId")Long caseID){
        stepService.addStep(stepDTO, caseID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
