package com.epam.jdi.uitests.web.selenium;

import com.epam.jdi.uitests.core.interfaces.IAsserter;
import com.epam.web.matcher.testng.Check;
import org.slf4j.Logger;

/**
 * Created by Roman_Iovlev on 7/17/2016.
 */
public class TestNGCheck extends Check implements IAsserter {
    public IAsserter setUpLogger(Logger logger) { super.setLogger(logger); return this; }

}
