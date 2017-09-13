package com.epam.test_generator.services;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SuitService {

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private DozerMapper mapper;

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();
        for(Suit suit : suitDAO.findAll()){
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
        Suit suit = new Suit();
        mapper.map(suitDTO, suit);
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

    public String generateStream(Long suitId, List<Long> CaseIds) throws IOException {
        Suit suit = suitDAO.findOne(suitId);

//        Suit suit = new Suit("Suit name", "description");
//
//        List<Case> caseList = new ArrayList<>();
//
//        Case caze1 = new Case();
//        caze1.setPriority(1);
//        caze1.setDescription("Case1 description");
//        caze1.setId((long) 1);
//
//        Step step1 = new Step();
//        step1.setDescription("step 1");
//        step1.setType(StepType.GIVEN);
//
//        Step step2 = new Step();
//        step2.setDescription("step 2");
//        step2.setType(StepType.WHEN);
//
//        List<Step> stepList1 = new ArrayList<>();
//        stepList1.add(step1);
//        stepList1.add(step2);
//        caze1.setSteps(stepList1);
//
//        caseList.add(caze1);
//
//        Case caze2 = new Case();
//        caze1.setPriority(1);
//        caze1.setDescription("Case2 description");
//        caze2.setId((long) 2);
//
//        Step step3 = new Step();
//        step3.setDescription("step 3");
//        step3.setType(StepType.GIVEN);
//
//        Step step4 = new Step();
//        step4.setDescription("step 4");
//        step4.setType(StepType.WHEN);
//
//        List<Step> stepList2 = new ArrayList<>();
//        stepList2.add(step3);
//        stepList2.add(step4);
//        caze1.setSteps(stepList2);
//
//        caseList.add(caze2);
//
//        suit.setCases(caseList);

        StringBuilder output = new StringBuilder("Feature: ");
            output.append(suit.getName());
            output.append('\n');
            for (Case caze : suit.getCases()) {
                if (CaseIds.contains(caze.getId())) {
                    output.append("Scenario: ");
                    output.append(caze.getDescription());
                    output.append('\n');

                    for (Step step: caze.getSteps()) {
                        output.append('\t');
                        output.append(step.getKeyword());
                        output.append(' ');
                        output.append(step.getDescription());
                        output.append('\n');
                    }
                }
            }

        return output.toString();
    }
}