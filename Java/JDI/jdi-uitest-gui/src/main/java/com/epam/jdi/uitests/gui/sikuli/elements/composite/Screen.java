package com.epam.jdi.uitests.gui.sikuli.elements.composite;

import com.epam.jdi.uitests.gui.sikuli.elements.CascadeInit;

/**
 * Created by Natalia_Grebenshchik on 1/15/2016.
 */
public class Screen {
    protected String driverName;

    public static <T> void init(Class<T> site) {

        CascadeInit.initPages(site, "sikuli");
    }

   /* public Screen(DriverTypes driver) {
        driverName = useDriver(driver);
        CascadeInit.initPages(this, driverName);
    }
    public Screen(String driver) {
        driverName = useDriver(driver);
        CascadeInit.initPages(this, driverName);
    }

    public void isInState(IPreconditions precondition) {
        PreconditionsState.isInState(precondition, getDriverFactory().getDriver(driverName));
    }*/
}
