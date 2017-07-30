package com.epam.jdi.uitests.testing.unittests.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Natalia_Grebenshchik on 12/21/2015.
 */
public class IndexesDP {

    @DataProvider(name = "cellIndexes")
    public static Object[][] cellIndexes() {
        return new Object[][]{
                {4, 1},
                {-1, 1},
                {0, 1},
                {1, 10},
                {1, -1},
                {1, 0}
        };
    }

    @DataProvider(name = "indexes")
    public static Object[][] indexesLess() {
        return new Object[][]{
                {10},
                {1000},
                {1000000000},
                {0},
                {-1},
                {-1000},
        };
    }
}
