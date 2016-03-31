package com.epam.jdi.uitests.web.selenium.driver;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */


import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.Rule;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static java.lang.System.getenv;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM;

/**
 * Created by Roman_Iovlev on 8/4/2015.
 */
public class SauceLabRunner implements SauceOnDemandSessionIdProvider {
    public static SauceOnDemandAuthentication authentication =
            new SauceOnDemandAuthentication(getenv("SAUCE_USER_NAME"), getenv("SAUCE_API_KEY"));
    private static String sessionId;
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(
            this, authentication);

    public static void setSauceSessionID(RemoteWebDriver remoteDriver) {
        sessionId = remoteDriver.getSessionId().toString();
    }

    public static DesiredCapabilities getSauceDesiredCapabilities(DriverTypes driverType) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(getenv("SELENIUM_BROWSER"));
        capabilities.setVersion(getenv("SELENIUM_VERSION"));
        capabilities.setCapability(PLATFORM, getenv("SELENIUM_PLATFORM"));
        return capabilities;
    }

    public static URL getSauceUrl() {
        return asserter.silent(() -> new URL("http://"
                + authentication.getUsername() + ":"
                + authentication.getAccessKey()
                + "@ondemand.saucelabs.com:80/wd/hub"));
    }

    public String getSessionId() {
        return sessionId;
    }
}