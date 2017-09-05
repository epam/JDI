package com.epam.jdi.uitests.testing.unittests.dataProviders;


import org.testng.annotations.DataProvider;

public class CheckBoxDP {

    @DataProvider(name = "inputData")
    public static Object[][] inputData() {
        return new Object[][]{
                {"true", true},
                {"1",    true},
                {"false", false},
                {"0", false}
        };
    }

    @DataProvider(name = "inputDataNothing2Changed")
    public static Object[][] inputDataNothing2Changed() {
        return new Object[][]{
                {"true "},
                {"1 "},
                {" false"},
                {"0 "},
                {" "},
                {"123"},
                {" 1"},
                {" 0"},
                {"!@#$%^&*"},
                {"qwdewf"},
                {"1qwe"},
                {"true123"},
                {"123true"},
                {"false123"},
                {"123false"},
                {"o"},
                {"O"},
                {"tr ue"},
        };
    }
}
