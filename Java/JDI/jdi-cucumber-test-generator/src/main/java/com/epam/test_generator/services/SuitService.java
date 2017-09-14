package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    public String generateStream(Long suitId, List<Long> CaseIds) throws IOException {
        Suit suit = suitDAO.findOne(suitId);
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
                        output.append(String.valueOf(StepType.values()[step.getType() - 1]));
                        output.append(' ');
                        output.append(step.getDescription());
                        output.append('\n');
                    }
                }
            }

        return output.toString();
    }
}