package com.epam.jdi.uitests.testing.career.parallel.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.jdi.uitests.web.settings.WebSettings.getDriverFactory;
import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static java.lang.Thread.currentThread;

/**
 * Created by Roman_Iovlev on 12/17/2015.
 */
public class DriverBalancer {
    private final static transient ReentrantLock rLock = new ReentrantLock();
    private static Map<Long, String> threadsDrivers = new HashMap<>();

    private static String registerNewDriver(long threadId) {
        String driverName = getDriverFactory().registerDriver(CHROME);
        threadsDrivers.put(threadId, driverName);
        return driverName;
    }

    public synchronized static String getDriverName() {
        long threadId = currentThread().getId();
        return threadsDrivers.containsKey(threadId)
                ? threadsDrivers.get(threadId)
                : registerNewDriver(threadId);
    }
}
