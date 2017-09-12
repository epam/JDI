package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

        return new ResponseEntity<>(suitService.getSuits(),HttpStatus.OK);
    }

    @RequestMapping(value = "/getSuit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SuitDTO> getSuit(@PathVariable("id") long id){

        return new ResponseEntity<>(suitService.getSuit(id), HttpStatus.OK);
    }

    @RequestMapping(value="/updateSuit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> editSuit(@RequestBody SuitDTO suit){
        suitService.updateSuit(suit);
      
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/removeTestSuit/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> removeSuit(@PathVariable("id") long id) {
        suitService.removeSuit(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/addSuit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<SuitDTO> addSuit(@RequestBody SuitDTO suit) {

        return new ResponseEntity<>(suitService.addSuit(suit), HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFeatureFile", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(@RequestParam(name = "suitId") Long suitId,
                             @RequestParam(name = "caseIds") List<Long> caseIds,
                             HttpServletResponse response) throws IOException {

        String mimeType = "application/octet-stream";
        System.out.println("mimetype : " + mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + "File.feature" + "\"");

        return  suitService.generateStream(suitId, caseIds);
//        ByteArrayInputStream inputStream = suitService.generateStream(suitId, caseIds);
//        response.setContentLength(inputStream.available());
//        FileCopyUtils.copy(inputStream, response.getOutputStream());
//        inputStream.close();
    }
}