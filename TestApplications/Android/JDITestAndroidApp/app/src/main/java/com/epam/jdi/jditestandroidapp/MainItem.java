package com.epam.jdi.jditestandroidapp;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

/**
 * Created by Vitalii_Sokolov on 6/2/2016.
 */
 public class MainItem {
    public
    @StringRes
    int mMenuName;
    public
    @IdRes
    int mId;

    public MainItem(@StringRes int mMenuName, @IdRes int mId) {
        this.mMenuName = mMenuName;
        this.mId = mId;
    }
}
