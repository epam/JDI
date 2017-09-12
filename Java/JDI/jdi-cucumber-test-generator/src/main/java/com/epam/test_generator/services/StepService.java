package com.epam.test_generator.services;

import com.epam.test_generator.DozerMapper;
import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.StepDAO;
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
        st.setParentCase(caseDAO.getOne(caseId));
        return stepDAO.save(st);
    }

    public List<Step> getStepsByCaseId(Long caseId) {
        return caseDAO.findOne(caseId).getSteps();
    }

    public void removeStep(Long id) {
        stepDAO.delete(id);
    }

    public Step updateStep(Step step) {
        step.setParentCase(stepDAO.getOne(step.getId()).getParentCase());
        return stepDAO.save(step);
    }
}
