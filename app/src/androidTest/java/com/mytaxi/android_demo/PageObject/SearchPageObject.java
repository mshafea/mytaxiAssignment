package com.mytaxi.android_demo.PageObject;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.RootMatchers;
import com.mytaxi.android_demo.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by mshafea on 3/17/2018.
 */

public class SearchPageObject extends BasePage{

    public static void searchForDriver(String driverName) {
        waitForTime(3000);
        enterValue(R.id.textSearch,driverName);
    }

    public static void selectFromDisplayedOptions(String fullDriverName) {
        onView(withText(fullDriverName)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
    }

    public static ViewInteraction getTextSearch(){
        return onView(withId(R.id.textSearch));
    }

}
