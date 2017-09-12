package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CaseService {

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

    public List<Case> getCasesBySuitId(long suitId) {
        return suitDAO.findOne(suitId).getCases();
    }

    public Case getCase(Long id) {
        return caseDAO.findOne(id);
    }

    public void removeCase(Long id) {
        caseDAO.delete(id);
    }

    public Case updateCase(CaseDTO cs) {
        Case caze = new Case();
        mapper.map(cs, caze);

        List<Step> list = caze.getSteps();
        for (Step step : list) {
            if (list.stream().noneMatch( (a) -> a.getId().equals(step.getId())) ) {
                stepService.addStepToCase(step, cs.getId());
            }
            else {
                stepService.updateStep(step);
            }
        }
        return caseDAO.save(caze);
    }

}