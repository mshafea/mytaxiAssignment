package com.mytaxi.android_demo.Tests;

import android.content.Intent;
import android.content.res.Resources;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.google.gson.JsonObject;
import com.mytaxi.android_demo.DataReader.JsonReader;
import com.mytaxi.android_demo.Handler.SearchForDrivers;
import com.mytaxi.android_demo.PageObject.DriverPageObject;
import com.mytaxi.android_demo.R;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by mshafea on 3/17/2018.
 */

public class SearchActivityTest {

    private static JsonObject jsonObject;
    private Resources resources;
    private static String PACKAGE_ANDROID_DIALER = "com.android.dialer";

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);


    @Before
    public void init() {
        resources = mActivityRule.getActivity().getResources();
    }

    public String getSearchData(String data){
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("SearchCriteria");
        return jsonObject.get(data).getAsString();
    }

    public String getDriverProfileData(String data){
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("SarahDriverProfile");
        return jsonObject.get(data).getAsString();
    }

    @Test
    public void testDriverProfileData(){
        SearchForDrivers.SearchForExistingDriver(getSearchData( "shortname"),getSearchData( "fullname"));
        DriverPageObject.getDriverName().check(matches(withText(getDriverProfileData("name"))));
        DriverPageObject.getDriverLocation().check(matches(withText(getDriverProfileData("location"))));
        DriverPageObject.getDriverDate().check(matches(withText(getDriverProfileData("date"))));
        DriverPageObject.getDriverPhoto().check(matches(isDisplayed()));
    }

    @Test
    public void testToCallDriver(){
        SearchForDrivers.SearchForExistingDriver(getSearchData( "shortname"),getSearchData( "fullname"));
        DriverPageObject.callDriver();
        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(getDriverProfileData("telephone")),
                toPackage(PACKAGE_ANDROID_DIALER)));
    }
}