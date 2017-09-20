package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.StepSuggestionDTO;
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
    }

    @Test
    public void getSuggestionsList_return200whenGetSuggestions() throws Exception {
        when(stepSuggestionService.getStepsSuggestion()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/step_suggestion"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(stepSuggestionService).getStepsSuggestion();
    }

    @Test
    public void getSuggestionsList_return500whenGetSuggestions() throws Exception {
        when(stepSuggestionService.getStepsSuggestion()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/step_suggestion"))
                .andDo(print())
                .andExpect(status().isInternalServerError());

        verify(stepSuggestionService).getStepsSuggestion();
    }

    @Test
    public void testAddSuggestion_return200whenAddNewSuggestion() throws Exception {
        stepSuggestionDTO.setId(null);
        when(stepSuggestionService.addStepSuggestion(any(StepSuggestionDTO.class))).thenReturn(stepSuggestionDTO);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isOk());

        verify(stepSuggestionService).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddSuggestionWithNullContent() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setContent(null);

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }


    @Test
    public void testAddSuggestion_return422whenAddEmptySuggestion() throws Exception {
        stepSuggestionDTO.setId(null);
        stepSuggestionDTO.setContent("");

        mockMvc.perform(post("/step_suggestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stepSuggestionDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(stepSuggestionService, times(0)).addStepSuggestion(any(StepSuggestionDTO.class));
    }

    @Test
    public void testRemoveSuggestion_return200whenRemoveSuggestion() throws Exception {
        mockMvc.perform(delete("/step_suggestion/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isOk());

        verify(stepSuggestionService).removeStepSuggestion(eq(SIMPLE_AUTOCOMPLETE_ID));
    }

    @Test
    public void testRemoveSuggestion_return500whenRemoveSuggestion() throws Exception {
        doThrow(RuntimeException.class).when(stepSuggestionService).removeStepSuggestion(anyLong());

        mockMvc.perform(delete("/step_suggestion/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isInternalServerError());

        verify(stepSuggestionService).removeStepSuggestion(eq(SIMPLE_AUTOCOMPLETE_ID));
    }
}