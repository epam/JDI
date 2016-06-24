package com.epam.jdi.uitests.win.winium.driver;

/**
 * Created by Iuliia_Petrova on 6/1/2016.
 */
public enum DriverTypes {
    WINIUMDESKTOP("winiumDesktop"),
    WINIUMMOBILE("winiumPhone"),
    WINIUMSTORE("winiumStore");

    public String name;

    DriverTypes(String name) {

        this.name = name;
    }
}
