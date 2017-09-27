package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.StepSuggestionDAO;
import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepSuggestion;
import com.epam.test_generator.transformers.StepSuggestionTransformer;
import com.epam.test_generator.entities.StepType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StepSuggestionServiceTest {

    private static final long SIMPLE_AUTOCOMPLETE_ID = 1L;

    @Mock
    private StepSuggestionTransformer stepSuggestionTransformer;

    @Mock
    private StepSuggestionDAO stepSuggestionDAO;

    @InjectMocks
    private StepSuggestionService stepSuggestionService;

    private List<StepSuggestion> listSteps;
    private List<StepSuggestionDTO> expectedListSteps;

    @Before
    public void setUp() {
        listSteps = new ArrayList<>();
        listSteps.add(new StepSuggestion(1L, "StepSuggestion 1", StepType.GIVEN));
        listSteps.add(new StepSuggestion(2L, "StepSuggestion 2", StepType.THEN));

        expectedListSteps = new ArrayList<>();
        expectedListSteps.add(new StepSuggestionDTO(1L, "StepSuggestion 1", StepType.GIVEN.ordinal()));
        expectedListSteps.add(new StepSuggestionDTO(2L, "StepSuggestion 2", StepType.THEN.ordinal()));
    }

    @Test
    public void getStepsSuggestion() throws Exception {
        when(stepSuggestionDAO.findAll()).thenReturn(listSteps);
        when(stepSuggestionTransformer.toDto(any(StepSuggestion.class))).thenReturn(expectedListSteps.get(0))
                                                                     .thenReturn(expectedListSteps.get(1));

        List<StepSuggestionDTO> getListStepsSuggestion = stepSuggestionService.getStepsSuggestion();
        assertEquals(true, Arrays.deepEquals(expectedListSteps.toArray(), getListStepsSuggestion.toArray()));

        verify(stepSuggestionDAO).findAll();
    }

    @Test
    public void addStepSuggestion() throws Exception {
        StepSuggestion newStepSuggestion = new StepSuggestion(3L, "StepSuggestion 3", StepType.AND);
        StepSuggestionDTO newStepSuggestionDTO = new StepSuggestionDTO(3L, "StepSuggestion 3", StepType.AND.ordinal());

        when(stepSuggestionDAO.save(any(StepSuggestion.class))).thenReturn(newStepSuggestion);
        when(stepSuggestionTransformer.toDto(any(StepSuggestion.class))).thenReturn(newStepSuggestionDTO);
        when(stepSuggestionTransformer.fromDto(any(StepSuggestionDTO.class))).thenReturn(newStepSuggestion);

        StepSuggestionDTO stepSuggestionAdded = stepSuggestionService.addStepSuggestion(newStepSuggestionDTO);
        assertEquals(newStepSuggestionDTO, stepSuggestionAdded);

        verify(stepSuggestionDAO).save(any(StepSuggestion.class));
    }

    @Test
    public void removeStepSuggestion() throws Exception {
        stepSuggestionService.removeStepSuggestion(SIMPLE_AUTOCOMPLETE_ID);

        verify(stepSuggestionDAO).delete(SIMPLE_AUTOCOMPLETE_ID);
    }

}