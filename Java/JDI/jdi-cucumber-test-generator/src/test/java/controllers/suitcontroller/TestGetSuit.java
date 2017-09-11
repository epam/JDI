package controllers.suitcontroller;

import com.epam.test_generator.controllers.SuitController;
import com.epam.test_generator.services.SuitService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestGetSuit {

    @Mock
    private SuitService suitService;

    @InjectMocks
    private SuitController suitController = new SuitController();
}
