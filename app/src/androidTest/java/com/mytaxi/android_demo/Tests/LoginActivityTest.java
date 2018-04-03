package com.mytaxi.android_demo.Tests;


import android.content.res.Resources;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.gson.JsonObject;
import com.mytaxi.android_demo.DataReader.JsonReader;
import com.mytaxi.android_demo.Handler.Login;
import com.mytaxi.android_demo.PageObject.LoginPageObject;
import com.mytaxi.android_demo.PageObject.SearchPageObject;
import com.mytaxi.android_demo.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    JsonObject jsonObject;
    String username, password;
    Login loginHandler;
    LoginPageObject loginPage;
    SearchPageObject searchPage;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private Resources resources;

    @Before
    public void init() {
        resources = mActivityRule.getActivity().getResources();
    }

    @Test
    public void testSuccessfulLogin() {
        loginHandler = new Login();
        searchPage = new SearchPageObject();
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("validLogin");
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        loginHandler.loginCredentials(username,password);
        searchPage.getTextSearch().check(matches(isDisplayed()));
    }

    @Test
    public void testFailLogin() {
        loginHandler = new Login();
        loginPage = new LoginPageObject();
        jsonObject = (JsonObject) JsonReader.getJSONResource(resources,R.raw.testdata ).get("invalidLogin");
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        loginHandler.loginCredentials(username,password);
        loginPage.getLoginButton().check(matches(isDisplayed()));
    }
}
