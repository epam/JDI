package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepSuggestion;
import org.springframework.stereotype.Component;

@Component
public class StepSuggestionTransformer extends AbstractDozerTransformer<StepSuggestion, StepSuggestionDTO> {

    @Override
    protected Class<StepSuggestion> getEntityClass() {
        return StepSuggestion.class;
    }

    @Override
    protected Class<StepSuggestionDTO> getDTOClass() {
        return StepSuggestionDTO.class;
    }
}
