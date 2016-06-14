package com.epam.jdi.jditestandroidapp;

import android.support.annotation.StringRes;

/**
 * Created by vitalii_sokolov on 09.06.16.
 */
public class ListItem {
    public @StringRes int text;
    public boolean checked;


    public ListItem(int text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }
}
