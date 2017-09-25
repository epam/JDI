package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.StepDAO;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StepService {

    @Autowired
    private StepDAO stepDAO;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private StepTransfer stepTransfer;

    public StepDTO addStepToCase(Step st, Long caseId) {
    	caseDAO.getOne(caseId).getSteps().add(st);

        return stepTransfer.toDto(stepDAO.save(st));
    }

    public List<StepDTO> getStepsByCaseId(Long caseId) {
        List<StepDTO> list = new ArrayList<>();

        for (Step step: caseDAO.findOne(caseId).getSteps()) {
            list.add(stepTransfer.toDto(step));
        }

        return list;
    }

    public void removeStep(Long id) {
        stepDAO.delete(id);
    }

    public StepDTO updateStep(Step step) {

        return stepTransfer.toDto(stepDAO.save(step));
    }

    public void addStep(StepDTO stepDTO, Long caseID) {
        addStepToCase(stepTransfer.fromDto(stepDTO), caseID);
    }

    public void removeAllSteps(Long caseId) {
        Case caze = caseDAO.getOne(caseId);
        List<Step> stepList = caze.getSteps();

        for (Step st: stepList) {
            stepDAO.delete(st.getId());
        }
        caze.setSteps(null);
        caseDAO.save(caze);
    }

    public void addSteps(Long caseId, List<StepDTO> steps) {
        for (StepDTO st: steps) {
            addStep(st, caseId);
        }
    }
}
