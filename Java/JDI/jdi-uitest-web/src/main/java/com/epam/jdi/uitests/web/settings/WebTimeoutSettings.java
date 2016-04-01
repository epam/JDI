package com.epam.jdi.uitests.web.settings;

import com.epam.jdi.uitests.core.settings.TimeoutSettings;

import static com.epam.web.matcher.base.BaseMatcher.setDefaultTimeout;

/**
 * Created by Roman_Iovlev on 12/10/2015.
 */
public class WebTimeoutSettings extends TimeoutSettings {

    @Override
    public void setCurrentTimeoutSec(int timeoutSec) {
        super.setCurrentTimeoutSec(timeoutSec);
        setDefaultTimeout(timeoutSec * 1000L);
    }
}
