package com.epam.test_generator.controllers;


import com.epam.test_generator.dao.MockDao;
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
    MockDao mockDao;

    @Autowired
    public SuitService suitService;

    @RequestMapping(value = "/")
    public String getMainPage(){
        return "/WEB-INF/static/views/newSuits";
    }

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getSuits() {
        return mockDao.getSuits();
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Suit getSuit(@PathVariable("id") long id){
        return mockDao.getSuit(id);
    }

    @RequestMapping(value="/editTestSuit", method = RequestMethod.POST, consumes = "application/json")
    public void editSuit(@RequestBody Suit suit){
        mockDao.editSuit(suit);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeSuit(@PathVariable("id") long id){
        mockDao.removeSuit(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/addTestSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Suit addSuit(@RequestBody Suit suit) {
        return mockDao.addSuit(suit);
    }
}