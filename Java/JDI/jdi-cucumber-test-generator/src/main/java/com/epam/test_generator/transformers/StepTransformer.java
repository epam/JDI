package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Step;
import org.springframework.stereotype.Component;

@Component
public class StepTransformer extends AbstractDozerTransformer<Step, StepDTO> {

    @Override
    protected Class<Step> getEntityClass() {
        return Step.class;
    }

    @Override
    protected Class<StepDTO> getDTOClass() {
        return StepDTO.class;
    }
}
