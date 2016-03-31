package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.Factory;

import static com.epam.commons.Timer.nowTime;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DATES_PAGE_FILLED;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dates;

/**
 * Created by Dmitry_Lebedev1 on 11/12/2015.
 */
public class DatePickerTests extends InitTests {

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextFieldTests("DatePicker", DATES_PAGE_FILLED,
                        () -> dates.datepicker,
                        nowTime("MM/dd/yyyy"),
                        nowTime("MM/dd/yyyy"),
                        "09/09/1945",
                        "1945",
                        "([0-9]{2}[\\/]{1}){2}[0-9]{4}")
        };
    }
}
