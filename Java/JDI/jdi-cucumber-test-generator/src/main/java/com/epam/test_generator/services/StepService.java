package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.StepDAO;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.transformers.StepTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class StepService {

    @Autowired
    private StepDAO stepDAO;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private StepTransformer stepTransformer;

    public StepDTO addStepToCase(Step st, Long caseId) {
    	caseDAO.getOne(caseId).getSteps().add(st);

        return stepTransformer.toDto(stepDAO.save(st));
    }

    public List<StepDTO> getStepsByCaseId(Long caseId) {
        return caseDAO.findOne(caseId).getSteps().stream().
                map(step -> stepTransformer.toDto(step)).collect(Collectors.toList());
    }

    public void removeStep(Long id) {
        stepDAO.delete(id);
    }

    public StepDTO updateStep(Step step) {
        return stepTransformer.toDto(stepDAO.save(step));
    }

    public void addStep(StepDTO stepDTO, Long caseID) {
        addStepToCase(stepTransformer.fromDto(stepDTO), caseID);
    }

    public void removeAllSteps(Long caseId) {
        Case caze = caseDAO.getOne(caseId);

        caze.getSteps().forEach(step -> stepDAO.delete(step));

        caze.setSteps(null);
        caseDAO.save(caze);
    }

    public void addSteps(Long caseId, List<StepDTO> steps) {
        steps.forEach(stepDTO -> addStep(stepDTO,caseId));
    }
}
