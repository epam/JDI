package com.epam.jdi.uitests.core.interfaces;
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


import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.*;
import static com.epam.commons.TryCatchUtil.tryGetResult;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public abstract class CascadeInit {

    protected abstract void setElement(Object parent, Class<?> parentType, Field field, String driverName);

    public synchronized void initElements(Object parent, String driverName) {
        foreach(getFields(parent, decorators(), stopTypes()),
                field -> setElement(parent, parent.getClass(), field, driverName));
    }

    protected Class<?>[] decorators() { return new Class<?>[] {IBaseElement.class, List.class }; }
    protected abstract Class<?>[] stopTypes();

    public synchronized void initStaticPages(Class<?> parentType, String driverName) {
        foreach(getStaticFields(parentType, decorators()),
                field -> setElement(null, parentType, field, driverName));
    }

    public synchronized <T extends Application> T initPages(Class<T>  site, String driverName) {
        T instance = tryGetResult(site::newInstance);
        instance.setDriverName(driverName);
        initElements(instance, driverName);
        return instance;
    }

    protected boolean isBaseElement(Object obj) {
        return isInterface(obj.getClass(), IBaseElement.class);
    }

}