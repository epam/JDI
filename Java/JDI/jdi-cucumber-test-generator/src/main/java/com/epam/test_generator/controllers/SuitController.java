package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SuitController {

    @Autowired
    public SuitService suitService;

    @RequestMapping(value = "/")
    public String getMainPage(){
        return "/WEB-INF/static/views/suits";
    }

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getSuits(){
        return suitService.getSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getSuit(@PathVariable("id") long id){
        return suitService.getSuit(id);
    }

    @RequestMapping(value="/editTestSuit", method = RequestMethod.POST, consumes = "application/json")
    public void editSuit(@RequestBody Suit suit){
        suitService.editSuit(suit);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET)
    public void removeSuit(@PathVariable("id") long id){
        suitService.removeSuit(id);
    }

    @RequestMapping(value="/addTestSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Suit addSuit(@RequestBody Suit suit){
        return suitService.addSuit(suit);
    }

}
