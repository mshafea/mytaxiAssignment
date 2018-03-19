package com.mytaxi.android_demo.Tests;

import android.content.Intent;
import android.content.res.Resources;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.util.Log;
import com.google.gson.JsonObject;
import com.mytaxi.android_demo.DataReader.JsonReader;
import com.mytaxi.android_demo.Handler.SearchForDrivers;
import com.mytaxi.android_demo.PageObject.DriverPageObject;
import com.mytaxi.android_demo.R;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class SearchActivityTest {

    JsonObject jsonObject;
    Resources resources;
    String PACKAGE_ANDROID_DIALER = "com.android.dialer";
    SearchForDrivers searchForDriver;
    DriverPageObject driverPage;

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);


    @Before
    public void init() {
        resources = mActivityRule.getActivity().getResources();
    }

    public String getSearchData(String data){
        try {
            jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("SearchCriteria");
            return jsonObject.get(data).getAsString();
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public String getDriverProfileData(String data){
        try {
            jsonObject = (JsonObject) JsonReader.getJSONResource( resources, R.raw.testdata ).get( "SarahDriverProfile" );
            return jsonObject.get( data ).getAsString();
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    @Test
    public void testDriverProfileData(){
        searchForDriver = new SearchForDrivers();
        driverPage = new DriverPageObject();
        searchForDriver.SearchForExistingDriver(getSearchData( "searchString"),getSearchData( "fullname"));
        driverPage.getDriverName().check(matches(withText(getDriverProfileData("name"))));
        driverPage.getDriverLocation().check(matches(withText(getDriverProfileData("location"))));
        driverPage.getDriverDate().check(matches(withText(getDriverProfileData("date"))));
        driverPage.getDriverPhoto().check(matches(isDisplayed()));
    }

    @Test
    public void testToCallDriver(){
        searchForDriver = new SearchForDrivers();
        driverPage = new DriverPageObject();
        searchForDriver.SearchForExistingDriver(getSearchData( "searchString"),getSearchData( "fullname"));
        driverPage.callDriver();
        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(getDriverProfileData("telephone")),
                toPackage(PACKAGE_ANDROID_DIALER)));
    }
}