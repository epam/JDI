package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.services.SuitService;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ResponseEntity<List<SuitDTO>> getSuits() {
		try {
			return new ResponseEntity<>(suitService.getSuits(), HttpStatus.OK);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<>(ImmutableList.of(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuitDTO> getSuit(@PathVariable("id") long id){

        return new ResponseEntity<>(suitService.getSuit(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSuitByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuitDTO> getSuitByName(@PathVariable("name") String name){

        return new ResponseEntity<>(suitService.getSuitByName(name),HttpStatus.OK);
    }

    @RequestMapping(value="/updateSuit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> editSuit(@RequestBody SuitDTO suit){
        suitService.updateSuit(suit);
      
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeSuit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeSuit(@PathVariable("id") long id){
        suitService.removeSuit(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/addSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SuitDTO> addSuit(@RequestBody SuitDTO suit) {

        return new ResponseEntity<>(suitService.addSuit(suit), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Void> handleRunTimeException(RuntimeException ex) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}