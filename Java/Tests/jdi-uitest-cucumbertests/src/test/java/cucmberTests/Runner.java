package cucmberTests;

/**
 * Created by Dmitry_Lebedev1 on 1/22/2016.
 */

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.cucmber.stepdefs", "cucmberTests.stepdefs"}
)
public class Runner {


}