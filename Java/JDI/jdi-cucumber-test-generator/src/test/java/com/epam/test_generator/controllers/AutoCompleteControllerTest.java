package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.AutoCompleteDTO;
import com.epam.test_generator.services.AutoCompleteService;
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
public class AutoCompleteControllerTest {

	private ObjectMapper mapper = new ObjectMapper();

	private MockMvc mockMvc;

	private AutoCompleteDTO autoCompleteDTO;

	private static final long SIMPLE_AUTOCOMPLETE_ID = 1L;

	@Mock
    private AutoCompleteService autoCompleteService;

    @InjectMocks
    private AutoCompleteController autoCompleteController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(autoCompleteController)
                .setControllerAdvice(new GlobalExceptionController())
                .build();
        autoCompleteDTO = new AutoCompleteDTO();
        autoCompleteDTO.setId(SIMPLE_AUTOCOMPLETE_ID);
        autoCompleteDTO.setContent("Some step description");
    }

    @Test
    public void getSuggestionsList_return200whenGetSuggestions() throws Exception {
        when(autoCompleteService.getAutoCompleteList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/getAutoCompleteList"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(autoCompleteService).getAutoCompleteList();
    }

    @Test
    public void getSuggestionsList_return500whenGetSuggestions() throws Exception {
        when(autoCompleteService.getAutoCompleteList()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/getAutoCompleteList"))
                .andDo(print())
                .andExpect(status().isInternalServerError());

        verify(autoCompleteService).getAutoCompleteList();
    }

    @Test
    public void testAddSuggestion_return200whenAddNewSuggestion() throws Exception {
        autoCompleteDTO.setId(null);
        when(autoCompleteService.addAutoComplete(any(AutoCompleteDTO.class))).thenReturn(autoCompleteDTO);

        mockMvc.perform(post("/addAutoComplete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(autoCompleteDTO)))
                .andExpect(status().isOk());

        verify(autoCompleteService).addAutoComplete(any(AutoCompleteDTO.class));
    }

    @Test
    public void testAddSuggestion_return422whenAddSuggestionWithNullContent() throws Exception {
        autoCompleteDTO.setId(null);
        autoCompleteDTO.setContent(null);

        mockMvc.perform(post("/addAutoComplete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(autoCompleteDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(autoCompleteService, times(0)).addAutoComplete(any(AutoCompleteDTO.class));
    }


    @Test
    public void testAddSuggestion_return422whenAddEmptySuggestion() throws Exception {
        autoCompleteDTO.setId(null);
        autoCompleteDTO.setContent("");

        mockMvc.perform(post("/addAutoComplete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(autoCompleteDTO)))
                .andExpect(status().isUnprocessableEntity());

        verify(autoCompleteService, times(0)).addAutoComplete(any(AutoCompleteDTO.class));
    }

    @Test
    public void testRemoveSuggestion_return200whenRemoveSuggestion() throws Exception {
        mockMvc.perform(delete("/removeAutoComplete/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isOk());

        verify(autoCompleteService).removeAutoComplete(eq(SIMPLE_AUTOCOMPLETE_ID));
    }

    @Test
    public void testRemoveSuggestion_return500whenRemoveSuggestion() throws Exception {
        doThrow(RuntimeException.class).when(autoCompleteService).removeAutoComplete(anyLong());

        mockMvc.perform(delete("/removeAutoComplete/" + SIMPLE_AUTOCOMPLETE_ID))
                .andExpect(status().isInternalServerError());

        verify(autoCompleteService).removeAutoComplete(eq(SIMPLE_AUTOCOMPLETE_ID));
    }
}