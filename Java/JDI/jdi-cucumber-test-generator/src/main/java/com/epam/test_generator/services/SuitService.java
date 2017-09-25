package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.entities.SuitTransfer;
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
    private SuitTransfer suitTransfer;

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();

        for(Suit suit: suitDAO.findAll()){
            suitDTOlist.add(suitTransfer.toDto(suit));
        }

        return suitDTOlist;
    }

    public SuitDTO getSuit(long id) {

        return suitTransfer.toDto(suitDAO.findOne(id));
    }

    public SuitDTO updateSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.getOne(suitDTO.getId());
        List<Case> cases = suit.getCases();

        suit = suitTransfer.fromDto(suitDTO);
        suit.setCases(cases);

        return suitTransfer.toDto(suitDAO.save(suit));
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public SuitDTO addSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.save(suitTransfer.fromDto(suitDTO));

        return suitTransfer.toDto(suit);
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