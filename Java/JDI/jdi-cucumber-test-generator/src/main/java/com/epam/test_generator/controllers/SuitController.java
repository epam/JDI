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
    public String getMainPage() {
        return "/WEB-INF/static/views/newSuits";
    }

    @RequestMapping(value = "/getAllSuits", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<SuitDTO>> getSuits() {

	    return new ResponseEntity<>(suitService.getSuits(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuitDTO> getSuit(@PathVariable("id") long id) {
    	SuitDTO suitDTO = suitService.getSuit(id);
        if (suitDTO.getId() == id) {

    	    return new ResponseEntity<>(suitDTO, HttpStatus.OK);
		}

        return new ResponseEntity<>(suitDTO, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/updateSuit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> editSuit(@RequestBody SuitDTO suitDTO) {
		if (isNameValid(suitDTO.getName()) && isPriorityValid(suitDTO.getPriority()) &&
			isDescriptionValid(suitDTO.getDescription()) && isTagsValid(suitDTO.getTags())) {
			suitService.updateSuit(suitDTO);

			return new ResponseEntity<>(HttpStatus.OK);
		}
      
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(value = "/removeSuit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeSuit(@PathVariable("id") long id) {
		SuitDTO suitDTO = suitService.getSuit(id);
        if (suitDTO.getId() == id) {
			suitService.removeSuit(id);

			return new ResponseEntity<>(HttpStatus.OK);
		}

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/addSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SuitDTO> addSuit(@RequestBody SuitDTO suitDTO) {
		if (isNameValid(suitDTO.getName()) && isPriorityValid(suitDTO.getPriority()) &&
			isDescriptionValid(suitDTO.getDescription()) && isTagsValid(suitDTO.getTags())) {

		    return new ResponseEntity<>(suitService.addSuit(suitDTO), HttpStatus.OK);
		}

		return new ResponseEntity<>(suitDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    private boolean isPriorityValid(Integer priority) {

        return (priority!=null) && (priority >= 1) && (priority <= 5);
    }

    private boolean isNameValid(String name) {

        return name != null && name.length() >= 1 && name.length() <= 255;
    }

    private boolean isDescriptionValid(String description) {

        return description == null || description.length() <= 255;
    }

    private boolean isTagsValid(String tags) {

        return tags == null || tags.length() <= 255;
    }

}