package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.SuitDTO;
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

    @RequestMapping(value = "/suits", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<SuitDTO>> getSuits() {

        return new ResponseEntity<>(suitService.getSuits(),HttpStatus.OK);
    }

    @RequestMapping(value = "/suit/{suitId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuitDTO> getSuit(@PathVariable("suitId") long id){

        return new ResponseEntity<>(suitService.getSuit(id), HttpStatus.OK);
    }

    @RequestMapping(value="/suit/{suitId}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Void> editSuit( @PathVariable("suitId") long id, @RequestBody SuitDTO suit){
        suitService.updateSuit(suit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/suit/{suitId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeSuit(@PathVariable("suitId") long id){
        suitService.removeSuit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/suit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SuitDTO> addSuit(@RequestBody SuitDTO suit) {
        return new ResponseEntity<>(suitService.addSuit(suit), HttpStatus.OK);
    }
}