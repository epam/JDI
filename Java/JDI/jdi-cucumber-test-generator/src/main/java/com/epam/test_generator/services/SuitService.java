package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
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
    private DozerMapper mapper;

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();

        for(Suit suit: suitDAO.findAll()){
            SuitDTO suitDTO = new SuitDTO();

            mapper.map(suit, suitDTO);
            suitDTOlist.add(suitDTO);
        }
        return suitDTOlist;
    }

    public SuitDTO getSuit(long id) {
        SuitDTO suitDTO = new SuitDTO();
        mapper.map(suitDAO.findOne(id), suitDTO);
        return suitDTO;
    }

    public SuitDTO updateSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.getOne(suitDTO.getId());
        List<Case> cases = suit.getCases();

        mapper.map(suitDTO, suit);
        suit.setCases(cases);
        mapper.map(suitDAO.save(suit), suitDTO);

        return suitDTO;
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public SuitDTO addSuit(SuitDTO suitDTO) {
        Suit suit = new Suit();

        mapper.map(suitDTO,suit);
        suit = suitDAO.save(suit);
        mapper.map(suit, suitDTO);

        return suitDTO;
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