package com.epam.jdi.uitests.mobile.appium.elements;
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


import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JAction;
import com.epam.commons.map.MapArray;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 8/17/2015.
 */
public final class MapInterfaceToElement {
    private MapInterfaceToElement() { }
    private static MapArray<Class, Class> map = new MapArray<>();
    private static boolean defaultInitDone = false;
    private static boolean mapInUse = false;
    private static boolean getMapInUse() {
        return !mapInUse && (mapInUse = true);
    }
    private static void useMap(JAction action) {
        Timer.waitCondition(() -> !getMapInUse());
        action.invoke();
        mapInUse = false;
    }


    public static void init(Object[][] pairs) {
        useMap(() -> {
            if (defaultInitDone)
                return;
            update(pairs);
            defaultInitDone = true;
        });
    }
    public static void update(Object[][] pairs) {
        useMap(() -> {
            try {
                map.addOrReplace(pairs);
            } catch (Exception ex) {
                throw exception("Error in getInterfaceTypeMap" + LINE_BREAK + ex.getMessage());
            }
        });
    }

    public static Class getClassFromInterface(Class clazz) {
        return map.get(clazz);
    }

}