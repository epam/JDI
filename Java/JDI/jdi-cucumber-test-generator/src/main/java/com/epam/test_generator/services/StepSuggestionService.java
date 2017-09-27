package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.StepSuggestionDAO;
import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepSuggestion;
import com.epam.test_generator.transformers.StepSuggestionTransformer;
import com.epam.test_generator.entities.StepType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class StepSuggestionService {

    @Autowired
    private StepSuggestionTransformer stepSuggestionTransformer;

    @Autowired
    private StepSuggestionDAO stepSuggestionDAO;

    @PostConstruct
    private void initializeDB(){
        List<String> given = Arrays.asList(
                "I open \"<pageName>\"",
                "I click on \"<linkName>\" linkI fill \"<fieldName>\" textfield with \"<data>\""
                );

        List<String> when = Arrays.asList(
                "I click on \"<buttonName>\" button",
                "I click on \"<linkName>\" link",
                "I click on \"<buttonName>\" button from \"<containerName>\"",
                "I click on \"<linkName>\" link from \"<containerName>\"",
                "I check \"<fieldName>\"",
                "I uncheck \"<fieldName>\"",
                "for element \"<fieldName>\" I set attribute \"<attributeName>\" on \"<attributeValue>\"",
                "I submit form \"<formName>\" data \"<json>\"",
                "I fill form \"<formName>\" data \"<json>\"",
                "I refresh page",
                "I go back",
                "I go forward",
                "On pagination \"<paginationName>\" I press next",
                "On pagination \"<paginationName>\" I press previous",
                "On pagination \"<paginationName>\" I select page number \"<index>\"",
                "On pagination \"<paginationName>\" I go to last",
                "On pagination \"<paginationName>\" I go to first",
                "I find \"<findString>\"",
                "I input to \"<filedName>\" lines \"<lines>\"",
                "I input to \"<filedName>\" new line \"<newLine>\"",
                "I input to \"<filedName>\" new input \"<newInput>\""
        );

        List<String> then = Arrays.asList(
                "checkbox \"<fieldName>\" is checked",
                "checkbox \"<fieldName>\" is unchecked",
                "element \"<fieldName>\" has attribute \"<attributeName>\" with value \"<attributeValue>\"",
                "form \"<formName>\" contains data \"<json>\"",
                "image \"<fieldName>\" has source \"<source>\"",
                "image \"<fieldName>\" has alt \"<alt>\"",
                "link \"<linkName>\" from \"<containerName>\" contains reference \"<contains>\"",
                "link \"<linkName>\" from \"<containerName>\" match reference \"<regex>\"",
                "I'm on \"<pageName>\"",
                "I check that page url match",
                "I check that page url contains",
                "field \"<filedName>\" contains \"<contains>\"",
                "text \"<fieldName>\" contains \"<contains>\"",
                "label \"<fieldName>\" contains \"<contains>\"",
                "link \"<fieldName>\" contains \"<contains>\"",
                "button \"<fieldName>\" contains \"<contains>\"",
                "text \"<fieldName>\" match \"<regex>\"",
                "label \"<fieldName>\" match \"<regex>\"",
                "link \"<fieldName>\" match \"<regex>\"",
                "button \"<fieldName>\" match \"<regex>\"",
                "text \"<linkName>\" from \"<containerName>\" contains \"<contains>\"",
                "label \"<linkName>\" from \"<containerName>\" contains \"<contains>\"",
                "link \"<linkName>\" from \"<containerName>\" contains \"<contains>\"",
                "button \"<linkName>\" from \"<containerName>\" contains \"<contains>\"",
                "text \"<linkName>\" from \"<containerName>\" match  \"<regex>\"",
                "label \"<linkName>\" from \"<containerName>\" match  \"<regex>\"",
                "link \"<linkName>\" from \"<containerName>\" match  \"<regex>\"",
                "button \"<linkName>\" from \"<containerName>\" match  \"<regex>\""
        );

        List<String> and = Arrays.asList("I fill field \"<fieldName>\" by text \"<text>\"");

        List<StepSuggestionDTO> allSuggestionSteps = getStepsSuggestion();

        List<StepSuggestionDTO> givenSuggestions = allSuggestionSteps.stream()
                .filter(c -> new Integer(0).equals(c.getType()))
                .collect(Collectors.toList());
        for (String s : given) {
            if(givenSuggestions.stream().map(StepSuggestionDTO::getContent).noneMatch(s::equals)){
                stepSuggestionDAO.save(new StepSuggestion(s, StepType.GIVEN));
            }
        }

        List<StepSuggestionDTO> whenSuggestions = allSuggestionSteps.stream()
                .filter(c -> new Integer(1).equals(c.getType()))
                .collect(Collectors.toList());
        for (String s : when) {
            if(whenSuggestions.stream().map(StepSuggestionDTO::getContent).noneMatch(s::equals)){
                stepSuggestionDAO.save(new StepSuggestion(s, StepType.WHEN));
            }
        }

        List<StepSuggestionDTO> thenSuggestions = allSuggestionSteps.stream()
                .filter(c -> new Integer(2).equals(c.getType()))
                .collect(Collectors.toList());
        for (String s : then) {
            if(thenSuggestions.stream().map(StepSuggestionDTO::getContent).noneMatch(s::equals)){
                stepSuggestionDAO.save(new StepSuggestion(s, StepType.THEN));
            }
        }

        List<StepSuggestionDTO> andSuggestions = allSuggestionSteps.stream()
                .filter(c -> new Integer(3).equals(c.getType()))
                .collect(Collectors.toList());
        for (String s : and) {
            if(andSuggestions.stream().map(StepSuggestionDTO::getContent).noneMatch(s::equals)){
                stepSuggestionDAO.save(new StepSuggestion(s, StepType.AND));
            }
        }

    }

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
