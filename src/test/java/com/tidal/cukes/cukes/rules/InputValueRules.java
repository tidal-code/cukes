package com.tidal.cukes.cukes.rules;

import com.tidal.cukes.cukes.actions.BingHomePageActions;
import com.tidal.flow.assertions.Soft;

import static com.tidal.flow.assertions.Assert.verify;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputValueRules {
    public static void bingSearchInputVerification(){
        verify("Intentional assertion failure", BingHomePageActions.getInputFieldValue()).endsWith("hello");
    }

    public static void bingSearchInputAssertion(){
        assertThat("hello", is(equalTo("world")));
    }

    public static void bingSearchSoftAssertion(){
        String getInputText = BingHomePageActions.getInputFieldValue();
        Soft.verify("A soft assertion failure One", getInputText).isEqualTo("Test");
        Soft.verify("A soft assertion failure Two", getInputText).isEqualTo("Test");
        Soft.verify("A soft assertion failure Three", getInputText).isEqualTo("Test");
    }
}
