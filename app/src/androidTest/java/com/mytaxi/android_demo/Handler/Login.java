package com.mytaxi.android_demo.Handler;

import com.mytaxi.android_demo.PageObject.LoginPageObject;

public class Login {

    LoginPageObject loginPage = new LoginPageObject();

    public void loginCredentials(String username, String password){
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }
}
