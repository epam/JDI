package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.TestSuit;
import com.epam.test_generator.services.TestSuitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestSuitController {

    @Autowired
    public TestSuitsService testSuitsService;

    @RequestMapping("/")
    public ModelAndView getSuits(){
        ModelAndView model = new ModelAndView("index");
        //model.addObject("suits", testSuitsService.getAllTestEntities());
        return model;
    }

}
