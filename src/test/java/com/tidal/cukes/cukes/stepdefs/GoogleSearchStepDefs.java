package com.tidal.cukes.cukes.stepdefs;

import com.tidal.wave.verification.conditions.collections.CollectionsCondition;
import com.tidal.wave.wait.ThreadSleep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.tidal.wave.webelement.ElementFinder.find;
import static com.tidal.wave.webelement.ElementFinder.findAll;

public class GoogleSearchStepDefs {

    @Given("I open the search interface")
    public void i_open_the_search_interface() {
        System.out.println("First step implementation");
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }
//    @When("I type the search term Selenium")
//    public void i_type_the_search_term_selenium() {
//
//       find("name:q").sendKeys("Selenium").pressEnter();
//        System.out.println("Second Step");
//
//    }
//    @Then("I should see the relevant result")
//    public void i_should_see_the_relevant_result() {
//        findAll("//h3[contains(text(), 'Selenium')]").shouldHave(CollectionsCondition.sizeGreaterThan(0));
//    }

    @When("I type the search term {string}")
    public void iTypeTheSearchTerm(String searchTerm) {
        find("name:q").sendKeys(searchTerm).pressEnter();
    }

    @Then("I should see the relevant result for {string}")
    public void iShouldSeeTheRelevantResultForSelenium(String searchTerm) {
        findAll(String.format("//h3[contains(text(), '%s')]", searchTerm)).shouldHave(CollectionsCondition.sizeGreaterThan(0));
    }
}
