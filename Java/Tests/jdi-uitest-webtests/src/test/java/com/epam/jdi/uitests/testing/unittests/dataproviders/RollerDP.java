package com.epam.jdi.uitests.testing.unittests.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Konstantin_Pulin on 9/29/2016.
 */
public class RollerDP {

    @DataProvider(name = "leftRollerDataProvider")
    public static Object[][] leftRollerDP() {
        return new Object[][]{
                {40},
                {50},
                {10}
        };
    }

    @DataProvider(name = "rightRollerDataProvider")
    public static Object[][] rightRollerDP() {
        return new Object[][]{
                {90},
                {80},
                {50}
        };
    }

    @DataProvider(name = "bothRollersDataProvider")
    public static Object[][] bothRollersDP() {
        return new Object[][]{
                {30, 90},
                {40, 80},
                {50, 50}
        };
    }
}
