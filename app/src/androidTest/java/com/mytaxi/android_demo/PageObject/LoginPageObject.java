package com.mytaxi.android_demo.PageObject;

import android.support.test.espresso.ViewInteraction;
import com.mytaxi.android_demo.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by mshafea on 3/13/2018.
 */

public class LoginPageObject extends BasePage {

    public static void enterUserName(String username) {
        enterValue(R.id.edt_username,username);
    }

    public static void enterPassword(String password) {
        enterValue(R.id.edt_password,password);
    }

    public static void clickOverLoginButton() {
        closeKeyboard();
        clickOverItem(R.id.btn_login);
        waitForTime(3000);
    }

    public static ViewInteraction getLoginButton(){
        return onView(withId(R.id.btn_login));
    }

}
