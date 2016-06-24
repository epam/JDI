package com.epam.jdi.uitests.testing.unittests.tests.common.factories;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.ButtonTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.TextFieldTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.TextTests;
import com.epam.jdi.uitests.testing.unittests.tests.composite.FormTests;
import org.testng.annotations.Factory;

public class TextTestsFactory extends InitTests {
    @Factory
    public Object[] textTests() {
        return new Object[]{new TextTests()};
    }
    @Factory
    public Object[] ButtonTests() {
        return new Object[]{new ButtonTests()};
    }
    @Factory
    public Object[] TextFieldTests() {
        return new Object[]{new TextFieldTests()};
    }
    @Factory
    public Object[] FormOneButtonTests() {
        return new Object[]{new FormTests()};
    }



}