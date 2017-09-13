package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.services.CaseService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CaseControllerTest {

    public static final long SIMPLE_SUIT_ID = 1L;
    public static final long SIMPLE_CASE_ID = 1L;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private CaseService casesService;

    @InjectMocks
    private CaseController caseController;

    private CaseDTO caseDTO;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(caseController)
                .setControllerAdvice(new GlobalExceptionController())
                .build();
        caseDTO = new CaseDTO();
        caseDTO.setId(SIMPLE_CASE_ID);
        caseDTO.setDescription("case1");
        caseDTO.setPriority(1);
        caseDTO.setSteps(new ArrayList<>());
    }

    @Test
    public void testAddCase_return200whenAddNewCase() throws Exception {
        caseDTO.setId(null);
        when(casesService.addCaseToSuit(any(CaseDTO.class), anyLong())).thenReturn(caseDTO);

        mockMvc.perform(post("/addCase/"+SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isOk());

        verify(casesService).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithNullDescription() throws Exception {
        caseDTO.setId(null);
        caseDTO.setDescription(null);
        mockMvc.perform(post("/addCase/"+SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithEmptyDescription() throws Exception {
        caseDTO.setId(null);
        caseDTO.setDescription("");
        mockMvc.perform(post("/addCase/"+SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithNullPriority() throws Exception {
        caseDTO.setId(null);
        caseDTO.setPriority(null);
        mockMvc.perform(post("/addCase/" + SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithZeroPriority() throws Exception {
        caseDTO.setId(null);
        caseDTO.setPriority(0);
        mockMvc.perform(post("/addCase/" + SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithMoreThanTheRequiredPriority() throws Exception {
        caseDTO.setId(null);
        caseDTO.setPriority(6);
        mockMvc.perform(post("/addCase/" + SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return422whenAddCaseWithLessThanTheRequiredPriority() throws Exception {
        caseDTO.setId(null);
        caseDTO.setPriority(-4);
        mockMvc.perform(post("/addCase/" + SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testAddCase_return500whenAddNewCase() throws Exception {
        when(casesService.addCaseToSuit(any(CaseDTO.class), anyLong())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/addCase/"+SIMPLE_SUIT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isInternalServerError());

        verify(casesService).addCaseToSuit(any(CaseDTO.class), eq(SIMPLE_SUIT_ID));
    }

    @Test
    public void testRemoveCase_return200whenRemoveCase() throws Exception {
        mockMvc.perform(get("/removeCase/"+SIMPLE_CASE_ID))
                .andExpect(status().isOk());

        verify(casesService).removeCase(eq(SIMPLE_CASE_ID));
    }

    @Test
    public void testRemoveCase_return500whenRemoveCase() throws Exception {
        doThrow(RuntimeException.class).when(casesService).removeCase(anyLong());
        mockMvc.perform(get("/removeCase/"+SIMPLE_CASE_ID))
                .andExpect(status().isInternalServerError());

        verify(casesService).removeCase(eq(SIMPLE_CASE_ID));
    }

    @Test
    public void testUpdateCase_return200whenUpdateCase() throws Exception {
        when(casesService.updateCase(any(CaseDTO.class))).thenReturn(caseDTO);

        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isOk());

        verify(casesService).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithNullPriority() throws Exception {
        caseDTO.setPriority(null);
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithZeroPriority() throws Exception {
        caseDTO.setPriority(0);
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithMoreThanTheRequiredPriority() throws Exception {
        caseDTO.setPriority(6);
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithLessThanTheRequiredPriority() throws Exception {
        caseDTO.setPriority(-4);
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithNullDescription() throws Exception {
        caseDTO.setDescription(null);
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return422whenUpdateCaseWithEmptyDescription() throws Exception {
        caseDTO.setDescription("");
        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isUnprocessableEntity());
        verify(casesService, times(0)).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testUpdateCase_return500whenUpdateCase() throws Exception {
        when(casesService.updateCase(any(CaseDTO.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/updateCase/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(caseDTO)))
                .andExpect(status().isInternalServerError());

        verify(casesService).updateCase(any(CaseDTO.class));
    }

    @Test
    public void testGetCase_return200whenGetCase() throws Exception {
        when(casesService.getCase(anyLong())).thenReturn(caseDTO);

        mockMvc.perform(get("/getCase/"+SIMPLE_CASE_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(caseDTO)));

        verify(casesService).getCase(eq(SIMPLE_CASE_ID));
    }

    @Test
    public void testGetCase_return404whenGetCase() throws Exception {
        when(casesService.getCase(anyLong())).thenReturn(null);

        mockMvc.perform(get("/getCase/"+SIMPLE_CASE_ID))
                .andExpect(status().isNotFound());

        verify(casesService).getCase(eq(SIMPLE_CASE_ID));
    }

    @Test
    public void testGetCase_return500whenGetCase() throws Exception {
        when(casesService.getCase(anyLong())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/getCase/"+SIMPLE_CASE_ID))
                .andExpect(status().isInternalServerError());

        verify(casesService).getCase(eq(SIMPLE_CASE_ID));
    }

}