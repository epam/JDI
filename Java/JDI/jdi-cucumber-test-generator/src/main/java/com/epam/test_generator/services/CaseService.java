package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class CaseService {

    @Autowired
    private SuitService suitService;

    @Autowired
    private DozerMapper mapper;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private StepService stepService;

    public CaseDTO addCaseToSuit(CaseDTO cs, long suitId) {
        Case caze = new Case();
        mapper.map(cs, caze);
        suitDAO.getOne(suitId).getCases().add(caze);
        mapper.map(caseDAO.save(caze), cs);
        return cs;
    }

    public List<CaseDTO> getCasesBySuitId(long suitId) {
        List<CaseDTO> listDTO = new ArrayList<>();
        List<Case> list = suitDAO.findOne(suitId).getCases();
        mapper.map(list, listDTO);
        return listDTO;
    }

    public CaseDTO getCase(Long id) {
        CaseDTO dto = new CaseDTO();
        mapper.map(caseDAO.getOne(id), dto);
        return dto;
    }

    public void removeCase(long suitId, long caseId) {
        SuitDTO suit = suitService.getSuit(suitId);
        List<CaseDTO> cases = suit.getCases();
        for (int i = 0; i < cases.size(); i++) {
            CaseDTO cd = new CaseDTO();
            mapper.map(cases.get(i), cd);
            if (cd.getId() == caseId) {
                cases.remove(i);
                suit.setCases(cases);
                break;
            }
        }
        suitService.updateSuit(suit);
        caseDAO.delete(caseId);
    }

    public CaseDTO updateCase(long suitId, CaseDTO cs) {
        Case caze = new Case();
        mapper.map(cs, caze);

        SuitDTO suit = suitService.getSuit(suitId);
        List<CaseDTO> cases = suit.getCases();
        for (int i = 0; i < cases.size(); i++) {
            CaseDTO cd = new CaseDTO();
            mapper.map(cases.get(i), cd);
            if (cd.getId().equals(cs.getId())) {
                cases.get(i).setSteps(null);
                mapper.map(caze, cases.get(i));
                break;
            }
        }
        suitService.updateSuit(suit);
        return cs;
    }

}