package cucmberTests;

/**
 * Created by Dmitry_Lebedev1 on 1/22/2016.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.cucmber.stepdefs", "cucmberTests.stepdefs"}
)
public class Runner {


}