package com.epam.jdi.uitests.core.preconditions;
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


import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.settings.JDIData.testName;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * Created by Roman_Iovlev on 10/27/2015.
 */
public final class PreconditionsState {
    public static boolean alwaysMoveToCondition;
    private PreconditionsState() { }

    public static void isInState(IPreconditions condition, Method method) {
        try {
            out.println(format("=== Start precondition. Thread id : %s", currentThread().getId()));
            logger.info("Move to condition: " + condition);
            if (method != null) testName = method.getName();
            if (!alwaysMoveToCondition && condition.checkAction())
                return;
            condition.moveToAction();
            out.println(format("=== Move to done precondition. Thread id : %s", currentThread().getId()));
            asserter.isTrue(condition::checkAction);
            logger.info(condition + " condition achieved");
        } catch (Exception ex) {
            throw asserter.exception(format("Can't reach state: %s. Reason: %s", condition, ex.getMessage()));
        }
    }

    public static void isInState(IPreconditions condition) {
        isInState(condition, null);
    }
}