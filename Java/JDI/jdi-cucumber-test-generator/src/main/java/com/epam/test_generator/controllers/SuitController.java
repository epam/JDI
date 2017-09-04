package com.epam.test_generator.controllers;


import com.epam.test_generator.services.SuitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SuitController {

    @Autowired
    public SuitsService testSuitsService;

    @RequestMapping("/")
    public String getSuits(){
        //model.addObject("suits", testSuitsService.getAllTestEntities());
        return "static/views/suits";
    }

}
