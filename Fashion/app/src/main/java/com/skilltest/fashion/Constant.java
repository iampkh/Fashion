package com.skilltest.fashion;

public class Constant {

    public static final String INTERNET_ENABLE_CLASS = "com.android.settings.Settings$DataUsageSummaryActivity";
    public static final String SETTINGS_PACKAGE = "com.android.settings";

    public static final String FRAGMENT_ITEM_POS_KEY = "ListFragmentInstance";

    /**
     * fragment enums, based on this fragment can be selected.
     * Note: Add a enum when new fragment is created
     */
    public static enum FRAGMENT_TYPE {
        MAIN,DETAIL,CART,PAYMENT
    }
}
