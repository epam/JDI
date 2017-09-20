package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.StepSuggestionDAO;
import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.entities.StepSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StepSuggestionService {

    @Autowired
    private DozerMapper dozerMapper;

    @Autowired
    private StepSuggestionDAO stepSuggestionDAO;

    public List<StepSuggestionDTO> getStepsSuggestion() {
        List<StepSuggestionDTO> stepSuggestionDTOList = new ArrayList<>();

        for(StepSuggestion stepSuggestion : stepSuggestionDAO.findAll()){
            StepSuggestionDTO stepSuggestionDTO = new StepSuggestionDTO();

            dozerMapper.map(stepSuggestion, stepSuggestionDTO);
            stepSuggestionDTOList.add(stepSuggestionDTO);
        }

        return stepSuggestionDTOList;
    }


    public StepSuggestionDTO addStepSuggestion(StepSuggestionDTO stepSuggestionDTO) {
        StepSuggestion stepSuggestion = new StepSuggestion();

        dozerMapper.map(stepSuggestionDTO, stepSuggestion);
        stepSuggestion = stepSuggestionDAO.save(stepSuggestion);
        dozerMapper.map(stepSuggestion, stepSuggestionDTO);

        return stepSuggestionDTO;
    }

    public void removeStepSuggestion(long id) {
        stepSuggestionDAO.delete(id);
    }
}
