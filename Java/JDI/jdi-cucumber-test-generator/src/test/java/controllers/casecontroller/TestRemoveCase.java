package controllers.casecontroller;

import com.epam.test_generator.controllers.CaseController;
import com.epam.test_generator.services.CaseService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestRemoveCase {
    @Mock
    private CaseService casesService;

    @InjectMocks
    private CaseController caseController = new CaseController();
}
