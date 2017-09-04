package com.epam.test_generator.controllers;


import com.epam.test_generator.services.interfaces.CaseExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SuitController {

    @Autowired
    public CaseExService testSuitsService;

    @RequestMapping("/")
    public ModelAndView getSuits(){
        ModelAndView model = new ModelAndView("index");
        //model.addObject("suits", testSuitsService.getAllTestEntities());
        return model;
    }

}
