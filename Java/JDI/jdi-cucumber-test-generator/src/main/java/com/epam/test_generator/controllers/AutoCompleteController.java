package com.epam.test_generator.controllers;

import com.epam.test_generator.services.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AutoCompleteController {

    @Autowired
    AutoCompleteService autoCompleteService;
}
