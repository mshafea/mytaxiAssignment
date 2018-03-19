package com.mytaxi.android_demo.Handler;

import com.mytaxi.android_demo.PageObject.LoginPageObject;

/**
 * Created by mshafea on 3/17/2018.
 */

public class Login {

    public static void loginCredentials(String username, String password){
        LoginPageObject.enterUserName(username);
        LoginPageObject.enterPassword(password);
        LoginPageObject.clickOverLoginButton();
    }
}
