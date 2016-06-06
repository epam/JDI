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


import com.epam.jdi.uitests.core.interfaces.CascadeInit;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.mobile.appium.elements.base.Element;
import com.epam.jdi.uitests.mobile.appium.elements.composite.AppPage;
import com.epam.jdi.uitests.mobile.appium.elements.composite.Section;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;

import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.WebAnnotationsUtil.*;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class AppiumCascadeInit extends CascadeInit {

    protected Class<?>[] stopTypes() { return new Class<?>[] {Object.class, AppPage.class, Section.class, Element.class}; }

    protected void fillPageFromAnnotation(Field field, IBaseElement instance, Class<?> parentType) {
        if (isClass(parentType, AppPage.class) && parentType.isAnnotationPresent(JPage.class))
            fillPageFromAnnotaiton((AppPage) instance, parentType.getAnnotation(JPage.class), null);
    }

    protected IBaseElement fillInstance(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (element.getLocator() == null)
            element.avatar.byLocator = getNewLocator(field);
        return element;
    }
    @Override
    protected IBaseElement specificAction(IBaseElement instance, Field field, Object parent, Class<?> type) {
        BaseElement element = (BaseElement) instance;
        if (parent == null || type != null) {
            By frameBy = getFrame(field.getDeclaredAnnotation(Frame.class));
            if (frameBy != null)
                element.avatar.frameLocator =  frameBy;
        }
        return element;
    }
    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type, String fieldName) throws IllegalAccessException, InstantiationException {
        By newLocator = getNewLocator(field);
        BaseElement instance = null;
        /*if (isInterface(type, List.class)) {
            Class<?> elementClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            if (elementClass.isInterface())
                elementClass = com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement.getClassFromInterface(type);
            if (elementClass != null)
                instance = new Elements(newLocator, elementClass);
        } else {*/
            if (type.isInterface())
                type = com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement.getClassFromInterface(type);
            if (type != null) {
                instance = (BaseElement) type.newInstance();
                instance.avatar.byLocator = newLocator;
            }
        /*}*/
        if (instance == null)
            throw exception("Unknown interface: %s (%s). Add relation interface -> class in VIElement.InterfaceTypeMap",
                    type, fieldName);
        instance.avatar.setDriverName(driverName);
        return instance;
    }

    protected By getNewLocatorFromField(Field field) {
        By byLocator = null;
        String locatorGroup = APP_VERSION;
        if (locatorGroup != null) {
            JFindBy jFindBy = field.getAnnotation(JFindBy.class);
            if (jFindBy != null && locatorGroup.equals(jFindBy.group()))
                byLocator = findByToBy(jFindBy);
        }
        return byLocator != null
                ? byLocator
                : findByToBy(field.getAnnotation(FindBy.class));
    }

}