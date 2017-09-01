package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.services.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestCaseController {
    @Autowired
    private CasesService testCasesService;

    @RequestMapping(value = "/suits/{suitId}")
    public ModelAndView getCasesForSuit(@PathVariable String suitId){
        ModelAndView model = new ModelAndView("");
        model.addObject("cases", testCasesService.getCasesBySuitId(Long.parseLong(suitId)));
        return model;
    }

    @RequestMapping(value = "/suits/{suitId}", method = RequestMethod.POST)
    public void addNewCaseToSuit(@RequestBody Case caseArg){
        testCasesService.addEntity(caseArg);
    }

    @RequestMapping(value = "/suits/{caseId}/{caseId}")
    public void updateCaseById(@PathVariable Long caseId, @RequestBody Case caseArg) {
        testCasesService.removeEntity(caseId);
        testCasesService.addEntity(caseArg);
    }





}
