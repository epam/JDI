package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.StepSuggestionDAO;
import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepSuggestion;
import com.epam.test_generator.entities.StepSuggestionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StepSuggestionService {

    @Autowired
    private StepSuggestionTransformer stepSuggestionTransformer;

    @Autowired
    private StepSuggestionDAO stepSuggestionDAO;

    public List<StepSuggestionDTO> getStepsSuggestion() {
        List<StepSuggestionDTO> stepSuggestionDTOList = new ArrayList<>();

        for(StepSuggestion stepSuggestion : stepSuggestionDAO.findAll()){
            StepSuggestionDTO stepSuggestionDTO = stepSuggestionTransformer.toDto(stepSuggestion);
            stepSuggestionDTOList.add(stepSuggestionDTO);
        }

        return stepSuggestionDTOList;
    }


    public StepSuggestionDTO addStepSuggestion(StepSuggestionDTO stepSuggestionDTO) {
        StepSuggestion stepSuggestion = new StepSuggestion();
        stepSuggestion = stepSuggestionDAO.save(stepSuggestionTransformer.fromDto(stepSuggestionDTO));

        return stepSuggestionTransformer.toDto(stepSuggestion);
    }

    public void removeStepSuggestion(long id) {
        stepSuggestionDAO.delete(id);
    }
}
