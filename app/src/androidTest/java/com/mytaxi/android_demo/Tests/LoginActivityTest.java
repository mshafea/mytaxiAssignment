package com.mytaxi.android_demo.Tests;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.res.Resources;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.gson.JsonObject;
import com.mytaxi.android_demo.DataReader.JsonReader;
import com.mytaxi.android_demo.Handler.Login;
import com.mytaxi.android_demo.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.mytaxi.android_demo.PageObject.LoginPageObject.getLoginButton;
import static com.mytaxi.android_demo.PageObject.SearchPageObject.getTextSearch;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by mshafea on 3/12/2018.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {


    private static JsonObject jsonObject;
    public static String username, password;

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    private Resources resources;

    @Before
    public void init() {
        resources = mActivityRule.getActivity().getResources();
    }

    @Test
    public void testSuccessfulLOGIN() {
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("validLogin");
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        Login.loginCredentials(username,password);
        getTextSearch().check(matches(isDisplayed()));
    }

    @Test
    public void testFailLOGIN() {
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("invalidLogin");
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        Login.loginCredentials(username,password);
        getLoginButton().check(matches(isDisplayed()));
    }
}
