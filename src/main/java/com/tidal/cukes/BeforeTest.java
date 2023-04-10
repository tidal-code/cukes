package com.tidal.cukes;

import com.tidal.wave.browser.Browser;
import com.tidal.wave.options.BrowserWithOptions;
import com.tidal.wave.propertieshandler.Config;
import com.tidal.wave.propertieshandler.PropertiesFinder;
import com.tidal.wave.scenario.ScenarioInfo;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.time.Duration;

import static com.tidal.wave.utils.CheckString.isNullOrEmpty;


/**
 * The BeforeTest class is now included with the framework to ensure consistency across projects.
 *
 */
public class BeforeTest {

    /**
     * This method primarily do the initial set of test runs through cucumber framework.
     * <p> The parameters it depends on are </p>
     * <p>1. Execution type - whether it is running in local or docker.</p>
     * <p>2. Various page stability parameters like doc ready check and angular activities check.</p>
     * <p>3. The default folder for downloading files.</p>
     * <p>4. Browser type to run tests </p>
     * <p>5. Base url to start the run</p>
     * @param scenario corresponding cucumber scenario running
     */
    @Before(value = "not (@api-regression or @bod-regression or @api or @no-browser or @ignore-base-hooks)", order = 10010)
    public void beforeScenario(Scenario scenario) {

        //Indication of test run start
        TestStatusMessages.logTestStartMessage(scenario);

        //set browser type: options are chrome, firefox, safari and edge
        String browser = Config.BROWSER_NAME;
        
        //Wait Duration
        Duration duration = Duration.ofSeconds(5);

        AbstractDriverOptions<?> options = null;

        //Set the options corresponding to remote and local runs
        String executionType = Config.EXECUTION_TYPE;
        if (executionType.equalsIgnoreCase("local")) {
           options = setLocalOptions(browser);
           duration = Duration.ofSeconds(Config.LOCAL_TIMEOUT);
        } else if (executionType.equalsIgnoreCase("docker") || executionType.equalsIgnoreCase("remote")) {
           options = setRemoteOptions(browser);
           duration = Duration.ofSeconds(Config.REMOTE_TIMEOUT);
        }

        //Option to complete the initial setting without setting up a browser session
        if(!isNullOrEmpty(Config.BASE_URL)) {
            Browser
                    .withOptions(options)
                    .type(browser)
                    .withWaitTime(duration)
                    .open(PropertiesFinder.getProperty("base.url"));
        }

        //This scenario set up is for csv reading scripts to automatically parse corresponding test data
        ScenarioInfo.setScenarioName(scenario.getName());
    }

    private AbstractDriverOptions<?> setLocalOptions(String browserType){
        return new BrowserWithOptions().getLocalOptions(browserType);
    }

    private AbstractDriverOptions<?> setRemoteOptions(String browserType){
        return new BrowserWithOptions().getRemoteOptions(browserType);
    }
}
