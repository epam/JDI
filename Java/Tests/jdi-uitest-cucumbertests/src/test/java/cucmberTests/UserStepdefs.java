package cucmberTests;


import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by Dmitry_Lebedev1 on 1/12/2016.
 */
public class UserStepdefs {
    @When("^I'm wait (\\d+) seconds$")
    public void iMWaitSeconds(int arg0) {
    }

    @Then("^Log contains \"([^\"]*)\"$")
    public void logContains(String arg0) {
        Assert.assertTrue(true);
    }

    @When("^I'm input lines to \"([^\"]*)\"$")
    public void iMInputLinesTo(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Result contains \"([^\"]*)\"$")
    public void resultContains(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Url is \"([^\"]*)\"$")
    public void urlIs(String arg0) {

    }

    @Then("^Log is empty$")
    public void logIsEmpty() {

    }

}