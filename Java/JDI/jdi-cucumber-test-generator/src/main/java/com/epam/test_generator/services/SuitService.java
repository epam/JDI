package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.transformers.SuitTransformer;
import com.epam.test_generator.file_generator.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Transactional
@Service
public class SuitService {

    @Autowired
    private FileGenerator fileGenerator;

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitTransformer suitTransformer;

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();

        for(Suit suit: suitDAO.findAll()){
            suitDTOlist.add(suitTransformer.toDto(suit));
        }

        return suitDTOlist;
    }

    public SuitDTO getSuit(long id) {

        return suitTransformer.toDto(suitDAO.findOne(id));
    }

    public SuitDTO updateSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.getOne(suitDTO.getId());
        List<Case> cases = suit.getCases();

        suit = suitTransformer.fromDto(suitDTO);
        suit.setCases(cases);

        return suitTransformer.toDto(suitDAO.save(suit));
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public SuitDTO addSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.save(suitTransformer.fromDto(suitDTO));

        return suitTransformer.toDto(suit);
    }

    public String generateFile(Long suitId, List<Long> caseIds) throws IOException {

        Suit suit = suitDAO.getOne(suitId);

        List<Case> cases = new ArrayList<>();

        for (Long caseId : caseIds) {
            cases.add(caseDAO.getOne(caseId));
        }
        return fileGenerator.generate(suit, cases);
    }
}