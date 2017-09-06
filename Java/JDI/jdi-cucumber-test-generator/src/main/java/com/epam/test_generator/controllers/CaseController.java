package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CaseController {
    @Autowired
    private CaseService casesService;

    @RequestMapping(value = "/suits/{suitId}/cases")
    public ModelAndView getCasesForSuit(@PathVariable String suitId){
        ModelAndView model = new ModelAndView("");
        model.addObject("cases", casesService.getCasesBySuitId(Long.parseLong(suitId)));
        return model;
    }

    @RequestMapping(value = "/suits/{suitId}/add", method = RequestMethod.POST, consumes = "application/json")
    public void addNewCaseToSuit(@PathVariable String suitId, @RequestBody Case caseArg){
        System.out.println(suitId + " " + caseArg);
        casesService.addCase(caseArg);
    }

    @RequestMapping(value = "/suits/{suitId}/cases/{caseId}")
    public ModelAndView getCaseById(@PathVariable String caseId) {
        ModelAndView model = new ModelAndView();
        model.addObject("case", casesService.getCase(Long.parseLong(caseId)));
        return model;
    }

//    @RequestMapping(value = "/saveCase", method = RequestMethod.POST)
//    public @ResponseBody String updateCaseById(@RequestBody String msg1) {
//        System.out.println(msg1);
//        return "{}";
//    }

    // Roman realization Save case button
    @RequestMapping(value = "/saveCase", method = RequestMethod.POST,produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    String Submit(@RequestParam("suitID") String suitId, @RequestParam("description") String caseDescription, @RequestParam("priority") String casePriority,
                  @RequestParam("numOfSteps") String numberOfStepsInCase, @RequestParam("code") String caseScenarioCode) {
        System.out.println(suitId);
        System.out.println(caseDescription);
        System.out.println(casePriority);
        System.out.println(numberOfStepsInCase);
        System.out.println(caseScenarioCode);

        return "" ;
    }

}