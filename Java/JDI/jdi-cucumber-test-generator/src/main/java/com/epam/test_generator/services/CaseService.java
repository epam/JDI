package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.DozerMapper;
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

    public Case addCaseToSuit(CaseDTO cs, long suitId) {
        Case caze = new Case();
        mapper.map(cs, caze);
        suitDAO.getOne(suitId).getCases().add(caze);
        return caseDAO.save(caze);
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

    public void removeCase(long id) {
        List<SuitDTO> suits = suitService.getSuits();
        for (SuitDTO su : suits) {
            List<CaseDTO> cases = getCasesBySuitId(su.getId());
            for (int i = 0; i < cases.size(); i++) {
                CaseDTO cd = new CaseDTO();
                mapper.map(cases.get(i), cd);
                if (cd.getId() == id) {
                    cases.remove(i);
                    su.setCases(cases);
                    break;
                }
            }
            suitService.updateSuit(su);
        }
        caseDAO.delete(id);
    }

    public CaseDTO updateCase(CaseDTO cs) {
        Case caze = new Case();
        mapper.map(cs, caze);

        List<Step> list = caze.getSteps();
        for (Step step : list) {
            if (list.stream().noneMatch((a) -> a.getId().equals(step.getId()))) {
                stepService.addStepToCase(step, cs.getId());
            } else {
                stepService.updateStep(step);
            }
        }
        mapper.map(cs, caze);
        mapper.map(caseDAO.save(caze), cs);
        return cs;
    }

}