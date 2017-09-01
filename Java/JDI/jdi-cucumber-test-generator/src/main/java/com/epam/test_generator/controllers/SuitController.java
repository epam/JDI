package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SuitController {

    @Autowired
    public SuitsService suitsService;

    @RequestMapping("/")
    public ModelAndView getSuits(){
        ModelAndView model = new ModelAndView("index");
        //model.addObject("suits", suitsService.getAllTestEntities());
        return model;
    }

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getTestSuits(){
        return suitsService.getTestSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getTestSuit(@PathVariable("id") long id){
        return suitsService.getTestSuit(id);
    }

    @RequestMapping(value="/editTestSuit", method = RequestMethod.POST, consumes = "application/json")
    public void editTestSuit(@RequestBody Suit suit){
        suitsService.editTestSuit(suit);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET, produces = "application/json")
    public void removeTestSuit(@PathVariable("id") long id){
        suitsService.removeTestSuit(id);
    }

    @RequestMapping(value="/addTestSuit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTestSuit(@RequestBody Suit suit){
        System.out.println(suit);
        suitsService.addTestSuit(suit);
    }

}
