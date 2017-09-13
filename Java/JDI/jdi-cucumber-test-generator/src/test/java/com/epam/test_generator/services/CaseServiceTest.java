package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CaseServiceTest {

    @Mock
    private CaseDAO casesDAO;

    @InjectMocks
    private CaseService caseService;

    @Test
    public void addCaseToSuit() throws Exception {
    }

    @Test
    public void getCasesBySuitId() throws Exception {
    }

    @Test
    public void getCase() throws Exception {
    }

    @Test
    public void removeCase() throws Exception {
    }

    @Test
    public void updateCase() throws Exception {
    }

}