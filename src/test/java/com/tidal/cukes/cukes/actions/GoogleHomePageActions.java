package com.tidal.cukes.cukes.actions;



import com.tidal.cukes.cukes.locators.GoogleHomePageLocators;

import static com.tidal.wave.verification.expectations.Expectation.exactText;
import static com.tidal.wave.webelement.ElementFinder.find;

public class GoogleHomePageActions {

    public static void clickInputField(){
        find(GoogleHomePageLocators.QUERY_INPUT_FIELD).click();
    }
    public static void enterQuery(String query){
        find(GoogleHomePageLocators.QUERY_INPUT_FIELD).sendKeys(query);
    }

    public static String getInputFieldValue(){
        return find(GoogleHomePageLocators.QUERY_INPUT_FIELD).getText();
    }

    public static void expectedConditionError() {
        find(GoogleHomePageLocators.QUERY_INPUT_FIELD).expecting(exactText("test")).orElseFail();
    }
}
