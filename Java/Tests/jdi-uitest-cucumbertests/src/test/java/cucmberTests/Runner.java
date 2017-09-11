package cucmberTests;

/**
 * Created by Dmitry_Lebedev1 on 1/22/2016.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"com.epam.jdi.cucumber.stepdefs.en", "cucmberTests"}
)
public class Runner extends AbstractTestNGCucumberTests {

}