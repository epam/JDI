package com.epam.test_generator.services;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.StepDAO;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
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
    private DozerMapper mapper;

    public StepDTO addStepToCase(Step st, Long caseId) {
        caseDAO.getOne(caseId).getSteps().add(st);
        StepDTO stepDTO = new StepDTO();
        mapper.map(stepDAO.save(st), stepDTO);
        return stepDTO;
    }

    public List<StepDTO> getStepsByCaseId(Long caseId) {
        List<StepDTO> list = new ArrayList<StepDTO>();
        for (Step step : caseDAO.findOne(caseId).getSteps()) {
            StepDTO toAdd = new StepDTO();
            mapper.map(step,toAdd);
            list.add(toAdd);
        }
        return list;
    }

    public void removeStep(Long id) {
        stepDAO.delete(id);
    }

    public StepDTO updateStep(Step step) {
        StepDTO stepDTO = new StepDTO();
        mapper.map(stepDAO.save(step), stepDTO);
        return stepDTO;
    }

    public void addStep(StepDTO stepDTO, Long caseID) {
        Step step = new Step();
        mapper.map(stepDTO, step);
        addStepToCase(step, caseID);
    }

    public void removeAllSteps(Long caseId) {
        Case caze = caseDAO.getOne(caseId);
        List<Step> stepList = caze.getSteps();
        for (Step st : stepList) {
            stepDAO.delete(st.getId());
        }
        caze.setSteps(null);
        caseDAO.save(caze);
    }

    public void addSteps(Long caseId, List<StepDTO> steps) {
        for (StepDTO st : steps) {
            addStep(st, caseId);
        }
    }
}
