package com.epam.test_generator.controllers;

import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sergey Lomdjaria on 05.09.2017.
 */

@RestController
public class RESTTestController {

    @Autowired
    private SuitService suitService;

    @RequestMapping(value = "/addNewSuitDB")
    public void addSuit(@RequestParam(name="name") String name,
                        @RequestParam(name="description") String description){
        Suit suit=new Suit();
        suit.setName(name);
        suit.setDescription(description);
        suitService.addSuit(suit);
    }

    @RequestMapping(value = "/listSuitsDB")
    public List<Suit> listSuits(){
        return suitService.getSuits();
    }
}
