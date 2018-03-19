package com.mytaxi.android_demo.Handler;

import com.mytaxi.android_demo.PageObject.SearchPageObject;

public class SearchForDrivers {

    SearchPageObject searchPage = new SearchPageObject();

    public void SearchForExistingDriver(String searchText, String fullName){
        searchPage.searchForDriver(searchText);
        searchPage.selectFromDisplayedOptions(fullName);
    }
}
