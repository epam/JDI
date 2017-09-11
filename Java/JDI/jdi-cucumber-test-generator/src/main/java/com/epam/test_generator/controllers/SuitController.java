package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SuitController {

    @Autowired
    SuitService suitService;

	@RequestMapping(value = "/")
    public String getMainPage(){
        return "/WEB-INF/static/views/newSuits";
    }

    @RequestMapping(value = "/getSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getSuits() {
        return suitService.getSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getSuit(@PathVariable("id") long id){
        return suitService.getSuit(id);
    }

    @RequestMapping(value = "/getSuitByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getSuitByName(@PathVariable("name") String name){
        return suitService.getSuitByName(name);
    }

    @RequestMapping(value="/editSuit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> editSuit(@RequestBody Suit suit){
        suitService.editSuit(suit);
      
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeSuit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeSuit(@PathVariable("id") long id){
        suitService.removeSuit(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/addSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Suit addSuit(@RequestBody Suit suit) {
        return suitService.addSuit(suit);
    }
}