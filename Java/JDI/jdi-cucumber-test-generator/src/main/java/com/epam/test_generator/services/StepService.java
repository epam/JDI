package com.epam.test_generator.services;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.StepDAO;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StepService {

    @Autowired
    private StepDAO stepDAO;

    @Autowired
    private CaseDAO caseDAO;
    @Autowired
    private DozerMapper mapper;

    public Step addStepToCase(Step st, Long caseId) {
        caseDAO.getOne(caseId).getSteps().add(st);
        return stepDAO.save(st);
    }

    public List<Step> getStepsByCaseId(Long caseId) {
        return caseDAO.findOne(caseId).getSteps();
    }

    public void removeStep(Long id) {
        stepDAO.delete(id);
    }

    public Step updateStep(Step step) {
        return stepDAO.save(step);
    }

    public void addStep(StepDTO stepDTO, Long caseID){
        Step step = new Step();
        mapper.map(stepDTO, step);
        addStepToCase(step, caseID);
    }
}
