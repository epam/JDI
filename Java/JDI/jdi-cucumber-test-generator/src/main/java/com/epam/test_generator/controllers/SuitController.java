package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET)
    public @ResponseBody List<Suit> getTestSuits(){
        return suitsService.getTestSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getTestSuit(@PathVariable("id") long id){
        return suitsService.getTestSuit(id);
    }

    @RequestMapping(value="/editTestSuit", method = RequestMethod.POST)
    public void editTestSuit(@RequestBody Suit suit){
        return suitsService.editTestSuit(suit);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET)
    public void removeTestSuit(@PathVariable("id") long id){
        return suitsService.removeTestSuit(id);
    }

    @RequestMapping(value="/addTestSuit", method = RequestMethod.POST)
    public @ResponseBody
    Suit addTestSuit(@RequestParam(value="name") String name,
                     @RequestParam(value="txtEmail") String description){
        return suitsService.addTestSuit(new Suit(name, description));
    }

}
