package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StepTransformer {

    @Autowired
    private DozerMapper dozerMapper;

    public Step fromDto(StepDTO stepDTO){
        Step step = new Step();
        dozerMapper.map(stepDTO, step);
        return step;
    }

    public StepDTO toDto(Step step){
        StepDTO stepDTO = new StepDTO();
        dozerMapper.map(step, stepDTO);
        return stepDTO;
    }

    public List<Step> fromDtoList(List<StepDTO> stepDTOList){
        List<Step> stepList = new ArrayList<>();
        dozerMapper.map(stepDTOList, stepList);
        return stepList;
    }

    public List<StepDTO> toDtoList(List<Step> stepList){
        List<StepDTO> stepDTOList = new ArrayList<>();
        dozerMapper.map(stepList, stepDTOList);
        return stepDTOList;
    }
}
