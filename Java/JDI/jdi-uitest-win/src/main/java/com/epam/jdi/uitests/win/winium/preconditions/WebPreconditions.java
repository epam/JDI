package com.epam.jdi.uitests.win.winium.preconditions;
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


import com.epam.jdi.uitests.core.preconditions.IPreconditions;

import static com.epam.jdi.uitests.win.settings.WinSettings.domain;
import static com.epam.jdi.uitests.win.settings.WinSettings.getDriver;

/**
 * Created by Roman_Iovlev on 10/27/2015.
 */
public interface WebPreconditions extends IPreconditions {
    static boolean checkUrl(String uri) {
        return getDriver().getCurrentUrl().matches(".*/" + uri + "(\\?.*)?");
    }

    static void openUri(String uri) {
        getDriver().navigate().to(getUrlByUri(uri));
    }

    static String getUrlByUri(String uri) {
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    default void open() {
        moveToAction();
    }
}