package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.services.StepSuggestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StepSuggestionControllerTest {

	private ObjectMapper mapper = new ObjectMapper();

	private MockMvc mockMvc;

	private StepSuggestionDTO stepSuggestionDTO;

	private static final long SIMPLE_AUTOCOMPLETE_ID = 1L;

	@Mock
    private StepSuggestionService stepSuggestionService;

    @InjectMocks
    private StepSuggestionController stepSuggestionController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(stepSuggestionController)
                .setControllerAdvice(new GlobalExceptionController())
                .build();
        stepSuggestionDTO = new StepSuggestionDTO();
        stepSuggestionDTO.setId(SIMPLE_AUTOCOMPLETE_ID);
        stepSuggestionDTO.setContent("Some step description");
        stepSuggestionDTO.setType(StepType.GIVEN.ordinal());
    }

    @Test
    public void getSuggestionsList_return200whenGetStepsSuggestion() throws Exception {
        when(stepSuggestionService.getStepsSuggestion()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/step_suggestion"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(stepSuggestionService).getStepsSuggestion();
    }

    @Test
    public void getSuggestionsList_return500whenGetStepsSuggestion() throws Exception {
        when(stepSuggestionService.getStepsSuggestion()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/step_suggestion"))
                .andDo(print())
                .andExpect(status().isInternalServerError());

        verify(stepSuggestionService).getStepsSuggestion();
    }

    @Test
    public void testAddSuggestion_return200whenAddNewStepSuggestion() throws Exception {
        stepSuggestionDTO.setId(null);
        when(stepSuggestionService.addStepSuggestion(any(StepSuggestionDTO.class))).thenReturn(stepSuggestionDTO);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isOk());

        verify(stepSuggestionService).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddStepSuggestionWithNullContent() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setContent(null);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddStepSuggestionWithEmptyContent() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setContent("");

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddStepSuggestionWithNullType() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setType(null);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddStepSuggestionWithWrongType() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setType(10);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testRemoveSuggestion_return200whenRemoveStepSuggestion() throws Exception {
        mockMvc.perform(delete("/step_suggestion/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isOk());

        verify(stepSuggestionService).removeStepSuggestion(eq(SIMPLE_AUTOCOMPLETE_ID));
    }

    @Test
    public void testRemoveSuggestion_return500whenRemoveStepSuggestion() throws Exception {
        doThrow(RuntimeException.class).when(stepSuggestionService).removeStepSuggestion(anyLong());

        mockMvc.perform(delete("/step_suggestion/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isInternalServerError());

        verify(stepSuggestionService).removeStepSuggestion(eq(SIMPLE_AUTOCOMPLETE_ID));
    }
}