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

    @RequestMapping(value = "/getAllSuits", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Suit>> getSuits() {

        return new ResponseEntity<>(suitService.getSuits(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Suit> getSuit(@PathVariable("id") long id){

        return new ResponseEntity<>(suitService.getSuit(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSuitByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Suit> getSuitByName(@PathVariable("name") String name){

        return new ResponseEntity<>(suitService.getSuitByName(name),HttpStatus.OK);
    }

    @RequestMapping(value="/updateSuit", method = RequestMethod.POST, consumes = "application/json")
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
    public ResponseEntity<Suit> addSuit(@RequestBody Suit suit) {

        return new ResponseEntity<>(suitService.addSuit(suit), HttpStatus.OK);
    }
}