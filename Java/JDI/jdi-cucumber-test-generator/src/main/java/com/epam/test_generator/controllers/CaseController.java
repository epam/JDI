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
public class CaseController {
    @Autowired
    private CasesService casesService;


    //It works
    @RequestMapping(value = "/suits/{suitId}/cases")
    public ModelAndView getCasesForSuit(@PathVariable String suitId){
        ModelAndView model = new ModelAndView("");
        model.addObject("cases", casesService.getCasesBySuitId(Long.parseLong(suitId)));
        return model;
    }

    //It doesn't work
    @RequestMapping(value = "/suits/{suitId}/add", method = RequestMethod.POST, consumes = "application/json")
    public void addNewCaseToSuit(@PathVariable String suitId, @RequestBody Case caseArg){
        System.out.println(suitId + " " + caseArg);
        casesService.addCase(caseArg);
    }

    //It works
    @RequestMapping(value = "/suits/{suitId}/cases/{caseId}")
    public ModelAndView getCaseById(@PathVariable String caseId) {
        ModelAndView model = new ModelAndView();
        model.addObject("case", casesService.getCase(Long.parseLong(caseId)));
        return model;
    }

    //It doesn't work
    @RequestMapping(value = "/suits/{suitId}/cases/{caseId}", method = RequestMethod.PUT)
    public void updateCaseById(@PathVariable Long caseId, @RequestBody Case caseArg) {
        System.out.println(caseId + " " + caseArg);
        casesService.removeCase(caseId);
        casesService.addCase(caseArg);
    }





}
