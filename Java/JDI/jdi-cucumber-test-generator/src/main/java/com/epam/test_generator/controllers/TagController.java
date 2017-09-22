package com.epam.test_generator.controllers;


import com.epam.test_generator.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/suit/{suitId}/tags", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<String>> getAllTags(@PathVariable Long suitId) {
        List<String> tags = tagService.getTags();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
