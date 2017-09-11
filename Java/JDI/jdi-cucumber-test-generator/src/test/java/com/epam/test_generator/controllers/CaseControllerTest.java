package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.services.CaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CaseControllerTest {

    @Mock
    private CaseService casesService;

    @InjectMocks
    private CaseController caseController;

    @Test
    public void testAddCase_return200whenAddSuit() {
        CaseDTO caseDTO = new CaseDTO();

        caseDTO.setDescription("case");
        when(casesService.addCaseToSuit(any(CaseDTO.class), anyLong())).thenReturn(caseDTO);

        ResponseEntity<Void> response = caseController.addCaseToSuit(1L, caseDTO);

        assert(response.getStatusCode().equals(HttpStatus.OK));
        verify(casesService).addCaseToSuit(caseDTO,1L);
    }

}