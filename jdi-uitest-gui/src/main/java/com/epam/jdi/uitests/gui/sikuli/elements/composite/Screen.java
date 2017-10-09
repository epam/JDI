package com.epam.jdi.uitests.gui.sikuli.elements.composite;

import com.epam.jdi.uitests.core.interfaces.Application;
import com.epam.jdi.uitests.gui.sikuli.elements.GUICascadeInit;

/**
 * Created by Natalia_Grebenshchik on 1/15/2016.
 */
public class Screen extends Application {
    protected String driverName;

    public static <T extends Application> void init(Class<T> site) {
        new GUICascadeInit().initPages(site, "sikuli");
    }

   /* public Screen(DriverTypes driver) {
        driverName = useDriver(driver);
        GUICascadeInit.initPages(this, driverName);
    }
    public Screen(String driver) {
        driverName = useDriver(driver);
        GUICascadeInit.initPages(this, driverName);
    }

    public void isInState(IPreconditions precondition) {
        PreconditionsState.isInState(precondition, getDriverFactory().getDriver(driverName));
    }*/
}
