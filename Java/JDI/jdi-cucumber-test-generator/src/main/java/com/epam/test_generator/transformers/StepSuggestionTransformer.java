package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepSuggestionTransformer {

    @Autowired
    private DozerMapper dozerMapper;

    public StepSuggestion fromDto(StepSuggestionDTO stepSuggestionDTO){
        StepSuggestion stepSuggestion = new StepSuggestion();
        dozerMapper.map(stepSuggestionDTO, stepSuggestion);
        return stepSuggestion;
    }

    public StepSuggestionDTO toDto(StepSuggestion stepSuggestion){
        StepSuggestionDTO stepSuggestionDTO = new StepSuggestionDTO();
        dozerMapper.map(stepSuggestion, stepSuggestionDTO);
        return stepSuggestionDTO;
    }

}
