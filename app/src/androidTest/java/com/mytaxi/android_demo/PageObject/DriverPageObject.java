package com.mytaxi.android_demo.PageObject;

import android.support.test.espresso.ViewInteraction;
import com.mytaxi.android_demo.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by mshafea on 3/17/2018.
 */

public class DriverPageObject {

    public static ViewInteraction getDriverName(){
        return onView(withId(R.id.textViewDriverName));
    }

    public static ViewInteraction getDriverLocation(){
        return onView(withId(R.id.textViewDriverLocation));
    }

    public static ViewInteraction getDriverDate(){
        return onView(withId(R.id.textViewDriverDate));
    }

    public static ViewInteraction getDriverPhoto(){
        return onView(withId(R.id.imageViewDriverAvatar));
    }

    public static void callDriver(){
        onView(withId(R.id.fab)).perform(click());
    }

}
