package com.epam.test_generator.controllers;


import com.epam.test_generator.dto.TagDTO;
import com.epam.test_generator.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getTags();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
