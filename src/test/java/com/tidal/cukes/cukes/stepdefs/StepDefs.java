package com.tidal.cukes.cukes.stepdefs;

import com.tidal.cukes.cukes.actions.BingHomePageActions;
import com.tidal.cukes.cukes.actions.GoogleHomePageActions;
import com.tidal.cukes.cukes.rules.InputValueRules;
import com.tidal.utils.filehandlers.FileReader;
import com.tidal.utils.loggers.Logger;
import com.tidal.wave.exceptions.RuntimeTestException;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class StepDefs {
    Logger logger =  new Logger(StepDefs.class);

    @When("I open google")
    public void iOpenGoogle() {
        Logger.info(StepDefs.class, "Allure Test: Open google");
        GoogleHomePageActions.enterQuery("Testing");
        String getInputText =GoogleHomePageActions.getInputFieldValue();
    }

    @When("I open bing homepage")
    public void iOpenBingHome() {
        Logger.info(StepDefs.class, "Allure Test: Open Bing");
        BingHomePageActions.enterQuery("Testing");
        InputValueRules.bingSearchSoftAssertion();

    }

    @Then("Then I can see google homepage")
    public void thenICanSeeGoogleHomepage() {
        Logger.info(StepDefs.class, "Allure Test: Test Verification");
        GoogleHomePageActions.clickInputField();
        String getInputText = GoogleHomePageActions.getInputFieldValue();
        //test file reader
        FileReader.readFileToString("TestResultTemplate.xml");
    }

    @Then("Then I can see bing homepage")
    public void thenICanSeeBingHomepage() {
        Logger.info(StepDefs.class, "Allure Test: Test Failure Verification");
        InputValueRules.bingSearchInputVerification();
    }

    @Then("Then I can see bing homepage with assertion error")
    public void thenICanSeeBingHomepageWithAssertionError() {
        InputValueRules.bingSearchInputAssertion();
    }

    @Then("Then I cannot find input locator")
    public void thenICanSeeBingHomepageInvalidLocator() {
        BingHomePageActions.tryEnterQuery("Testing");
    }

    @Then("this is a skipped test")
    public void thisIsASkippedTest() {
        FileReader.readFileToString("TestResultTemplate.xml");
        throw new PendingException("This step is pending to be implemented");
    }

    @Then("Assertion error is thrown")
    public void assertionErrorIsThrown() {
        throw new AssertionError("A Test Assertion Exception");
    }

    @Then("Assertion error Junit type is thrown")
    public void assertionErrorJunitTypeIsThrown() {
        Assert.assertEquals("hello", "world");
    }

    @Then("Runtime Exception is thrown")
    public void runtimeExceptionIsThrown() {
        throw new RuntimeTestException("A Runtime Test Exception");
    }

    @Then("Expected condition error is thrown")
    public void expectedConditionErrorIsThrown() {
        GoogleHomePageActions.expectedConditionError();
    }
}
