package com.tidal.cukes.cukes.actions;



import com.tidal.cukes.cukes.locators.GoogleHomePageLocators;

import static com.tidal.wave.webelement.ElementFinder.find;

public class BingHomePageActions {
    public static void enterQuery(String query){
        find(GoogleHomePageLocators.QUERY_INPUT_FIELD).sendKeys(query);
    }

    public static void tryEnterQuery(String query){
        find(GoogleHomePageLocators.INVALID_QUERY_INPUT_FIELD).sendKeys(query);
    }

    public static String getInputFieldValue(){
        return find(GoogleHomePageLocators.QUERY_INPUT_FIELD).getText();
    }
}
