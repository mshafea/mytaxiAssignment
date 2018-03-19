package com.mytaxi.android_demo.PageObject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNull;

/**
 * Created by mshafea on 3/17/2018.
 */

public class BasePage {
    protected static void clickOverItem(int Id) {
        onView(withId(Id)).perform(click());
    }

    protected static void enterValue(int elementId,String value ) {
        onView(withId(elementId)).perform(typeText(value));
    }

    //Close the keyword, otherwise LoginButton won't be visible to Espresso and it will throw an exception.
    protected static void closeKeyboard() {
        closeSoftKeyboard();
    }

    protected static void waitForTime(int ms) {

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            assertNull(e);
            e.printStackTrace();
        }
    }
}
