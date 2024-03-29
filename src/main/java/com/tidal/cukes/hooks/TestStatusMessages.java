package com.tidal.cukes.hooks;

import com.tidal.utils.loggers.Logger;
import com.tidal.utils.propertieshandler.PropertiesFinder;
import io.cucumber.java.Scenario;

public class TestStatusMessages {

    private TestStatusMessages() {
    }

    protected static void logTestStartMessage(Scenario scenario){
        Logger.info(BeforeTest.class, "==================== TEST CASE STARTS ==================");
        Logger.info(BeforeTest.class, scenario.getName());
        Logger.info(BeforeTest.class, "Environment: " + PropertiesFinder.getEnvironment().toUpperCase());
        Logger.info(BeforeTest.class, "========================================================");
    }

    protected static void logTestRunFinishMessage(Scenario scenario){
        Logger.info(AfterTest.class, "===================== TEST RUN ENDS =====================");
        Logger.info(AfterTest.class, scenario.getName());
        Logger.info(AfterTest.class, "=========================================================");
    }
}
