package com.mytaxi.android_demo.Handler;

import com.mytaxi.android_demo.PageObject.SearchPageObject;

/**
 * Created by mshafea on 3/17/2018.
 */

public class SearchForDrivers {

    public static void SearchForExistingDriver(String shortName, String fullName){
        SearchPageObject.searchForDriver(shortName);
        SearchPageObject.selectFromDisplayedOptions(fullName);
    }
}
