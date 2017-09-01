package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuitsController {

    @Autowired
    public SuitsServiceImpl suitsServiceImpl;

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getTestSuits(){
        return suitsServiceImpl.getTestSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getTestSuit(@PathVariable("id") long id){
        return suitsServiceImpl.getTestSuit(id);
    }

    @RequestMapping(value="/editTestSuit", method = RequestMethod.POST, consumes = "application/json")
    public void editTestSuit(@RequestBody Suit suit){
        suitsServiceImpl.editTestSuit(suit);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET)
    public void removeTestSuit(@PathVariable("id") long id){
        suitsServiceImpl.removeTestSuit(id);
    }

    @RequestMapping(value="/addTestSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Suit addTestSuit(@RequestBody Suit suit){
        return suitsServiceImpl.addTestSuit(suit);
    }

}
