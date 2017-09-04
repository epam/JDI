package com.epam.test_generator.controllers;


import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SuitController {

    static List<Suit> list = new ArrayList<>();
    static List<Case> caseList1 = new ArrayList<>();
    static List<Case> caseList2 = new ArrayList<>();

    static {
        Suit suit1 = new Suit("Suit 1", "Some description");
        Suit suit2 = new Suit("Suit 2", "Some description");
        Suit suit3 = new Suit("Suit 3", "Some description");

//        for (int i = 0; i < 30; i++) {
//            caseList1.add(new Case((long) i, "test description", "", suit1));
//        }
//        suit1.setCases(caseList1);
//
//        for (int i = 0; i < 25; i++) {
//            caseList2.add(new Case((long) i, "test description", "", suit2));
//        }
//        suit2.setCases(caseList2);

        list.add(suit1);
        list.add(suit2);
        list.add(suit3);
    }



    @Autowired
    public SuitService suitService;

    @RequestMapping(value = "/")
    public String getMainPage(){
        return "/WEB-INF/static/views/newSuits";
    }

    @RequestMapping(value = "/getTestSuits", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Suit> getSuits() {
        return list;
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
    public Suit addSuit(@RequestBody Suit suit) {
        list.add(suit);

        return suit;
    }

}
