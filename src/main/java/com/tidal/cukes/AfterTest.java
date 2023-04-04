package com.tidal.cukes;

import com.tidal.flow.assertions.stackbuilder.ErrorStack;
import com.tidal.wave.browser.Driver;
import com.tidal.wave.filehandlers.FileOutWriter;
import com.tidal.wave.filehandlers.FilePaths;
import com.tidal.wave.propertieshandler.Config;
import com.tidal.wave.utils.Helper;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static com.tidal.wave.browser.Browser.close;


public class AfterTest {

    private static final Path TARGET_FOLDER_PATH = Paths.get(Helper.getAbsoluteFromRelativePath(FilePaths.TARGET_FOLDER_PATH.getPath()));
    private static final Path PATH_TO_WRITE_FILE = Paths.get(TARGET_FOLDER_PATH.toString(), "screenshots");


    /**
     * If it is required to keep the browser open for debugging purpose, use <b>@debug</b> tag with the scenario.
     * <p>This tag will keep the browser open in a local environment,
     * but will ensure that, it will be properly closed in a remote environment.</p>
     * <p>The order number is given a negative value so that this After hook will be executed <b>last</b>. <br/>
     * This is required as the current session will be closed and all stored context values will be deleted after the execution of this hook.<br/>
     * As per cucumber documentation, the hooks with higher order number will be executed first.</p>
     *
     * @param scenario Current Cucumber Scenario
     */
    @After(value = "not (@api-regression or @bod-regression or @api or @no-browser or @ignore-base-hooks)", order = -1)
    public void afterScenario(Scenario scenario) {
        String executionType = Config.EXECUTION_TYPE;

        try {
            if (scenario.isFailed()) {
                saveScreenShot(scenario);
            } else {
                passedScenario(scenario);
            }
        } finally {
            try {
                if (!scenario.getSourceTagNames().contains("@debug") || executionType.equals("docker") || executionType.equals("remote")) {
                    close();
                }
            } finally {
                new ErrorStack().execute();
            }
        }
        //Test run finishing message
        TestStatusMessages.logTestRunFinishMessage(scenario);
    }

    @After(value = "@api", order = -1)
    public void apiAfterScenario(Scenario scenario) {
        new ErrorStack().execute();
    }


    private void saveScreenShot(Scenario scenario) {
        byte[] screenShot = getScreenshot();
        Allure.getLifecycle().addAttachment("Image Attachment" + scenario.getName(), "image/png", "png", screenShot);

        //Write screenshot to a temp folder in 'target' folder to upload later for ADO results.
        saveScreenShotForUpload(scenario, screenShot);
    }

    private void saveScreenShotForUpload(Scenario scenario, byte[] screenShot) {
        String formattedFileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "");
        Path screenshotStringPath = Paths.get(PATH_TO_WRITE_FILE.toString(), formattedFileName + ".txt");
        String encodedScreenshotData = Base64.getEncoder().encodeToString(screenShot);
        FileOutWriter.createDirectory(PATH_TO_WRITE_FILE.toString());
        FileOutWriter.writeFileTo(encodedScreenshotData, screenshotStringPath.toString());
    }

    private byte[] getScreenshot() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private void passedScenario(Scenario scenario) {
        Allure.addAttachment("Text Attachment - " + scenario.getName(), "text/plain", "", "txt");
    }
}
