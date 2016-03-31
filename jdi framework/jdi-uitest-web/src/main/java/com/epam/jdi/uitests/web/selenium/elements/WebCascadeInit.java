package com.epam.jdi.uitests.web.selenium.elements;
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


import com.epam.jdi.commons.pairs.Pairs;
import com.epam.jdi.uitests.core.interfaces.CascadeInit;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.ContextType;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.epam.jdi.commons.ReflectionUtils.*;
import static com.epam.jdi.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDIData.appVersion;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class WebCascadeInit extends CascadeInit {

    protected void setElement(Class<?> parentType, Field field, String driverName) {
        try {
            Class<?> type = field.getType();
            IBaseElement instance;
            if (isInterface(type, IPage.class)) {
                instance = (IBaseElement) getValueField(field, null);
                if (instance == null)
                    instance = (IBaseElement) type.newInstance();
                fillPage(instance, field, parentType);
            } else {
                instance = createChildFromFieldStatic(parentType, field, type, driverName);
                instance.setFunction(getFunction(field));
            }
            instance.setName(field);
            if (instance.getClass().getSimpleName().equals(""))
                instance.setTypeName(type.getSimpleName());
            instance.setParentName(parentType.getClass().getSimpleName());
            field.set(null, instance);
            if (isInterface(field, IComposite.class))
                initElements(instance, driverName);
        } catch (Exception ex) {
            throw exception("Error in setElement for field '%s' with parent '%s'", field.getName(), parentType.getClass().getSimpleName() + LINE_BREAK + ex.getMessage());
        }
    }

    protected void setElement(Object parent, Field field, String driverName) {
        try {
            Class<?> type = field.getType();
            IBaseElement instance;
            if (isInterface(type, IPage.class)) {
                instance = (IBaseElement) getValueField(field, parent);
                if (instance == null)
                    instance = (IBaseElement) type.newInstance();
                fillPage(instance, field, parent != null ? parent.getClass() : null);
            } else {
                instance = createChildFromField(parent, field, type, driverName);
                instance.setFunction(getFunction(field));
            }
            instance.setName(field);
            instance.getAvatar().setDriverName(driverName);
            if (instance.getClass().getSimpleName().equals(""))
                instance.setTypeName(type.getSimpleName());
            instance.setParentName(getClassName(parent));
            field.set(parent, instance);
            if (isInterface(field, IComposite.class))
                initElements(instance, driverName);
        } catch (Exception ex) {
            throw exception("Error in setElement for field '%s' with parent '%s'", field.getName(),
                    getClassName(parent) + LINE_BREAK + ex.getMessage());
        }
    }

    private void fillPage(IBaseElement instance, Field field, Class<?> parentType) {
        if (field.isAnnotationPresent(JPage.class))
            fillPageFromAnnotaiton((WebPage) instance, field.getAnnotation(JPage.class), parentType);
    }

    private BaseElement createChildFromFieldStatic(Class<?> parentClass, Field field, Class<?> type, String driverName) {
        BaseElement instance = (BaseElement) getValueField(field, null);
        if (instance == null)
            try {
                instance = getElementInstance(field, driverName);
            } catch (Exception ex) {
                throw exception(format("Can't create child for parent '%s' with type '%s'",
                        parentClass.getSimpleName(), field.getType().getSimpleName()));
            }
        else if (instance.getLocator() == null)
            instance.avatar.byLocator = getNewLocator(field);
        instance.avatar.context = new Pairs<>();
        By frameBy = getFrame(field.getDeclaredAnnotation(Frame.class));
        if (frameBy != null)
            instance.avatar.context.add(ContextType.Frame, frameBy);
        return instance;
    }

    private IBaseElement createChildFromField(Object parent, Field field, Class<?> type, String driverName) {
        BaseElement instance = (BaseElement) getValueField(field, parent);
        if (instance == null)
            try {
                instance = getElementInstance(field, driverName);
            } catch (Exception ex) {
                throw exception(
                        format("Can't create child for parent '%s' with type '%s'",
                                parent.getClass().getSimpleName(), field.getType().getSimpleName()));
            }
        else if (instance.getLocator() == null)
            instance.avatar.byLocator = getNewLocator(field);
        instance.avatar.context = (isBaseElement(parent))
                ? ((BaseElement) parent).avatar.context.copy()
                : new Pairs<>();
        if (type != null) {
            By frameBy = getFrame(field.getDeclaredAnnotation(Frame.class));
            if (frameBy != null)
                instance.avatar.context.add(ContextType.Frame, frameBy);
        }
        if (isBaseElement(parent)) {
            By parentLocator = ((BaseElement) parent).getLocator();
            if (parentLocator != null)
                instance.avatar.context.add(ContextType.Locator, parentLocator);
        }
        return instance;
    }

    private BaseElement getElementInstance(Field field, String driverName) {
        Class<?> type = field.getType();
        String fieldName = field.getName();
        By newLocator = getNewLocator(field);
        try {
            BaseElement instance = null;
            if (isClass(type, List.class)) {
                Class<?> elementClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                if (elementClass.isInterface())
                    elementClass = MapInterfaceToElement.getClassFromInterface(type);
                if (elementClass != null)
                    instance = new Elements(newLocator, elementClass);
            } else {
                if (type.isInterface())
                    type = MapInterfaceToElement.getClassFromInterface(type);
                if (type != null) {
                    instance = (BaseElement) type.newInstance();
                    instance.avatar.byLocator = newLocator;
                }
            }
            if (instance != null) {
                instance.avatar.setDriverName(driverName);
                return instance;
            }
            throw exception("Unknown interface: " + type
                    + ". Add relation interface -> class in VIElement.InterfaceTypeMap");
        } catch (Exception ex) {
            throw exception("Error in getElementInstance for field '%s' with type '%s'", fieldName, type.getSimpleName()
                    + LINE_BREAK + ex.getMessage());
        }
    }

    private By getNewLocator(Field field) {
        try {
            By byLocator = null;
            String locatorGroup = appVersion;
            if (locatorGroup != null) {
                JFindBy jFindBy = field.getAnnotation(JFindBy.class);
                if (jFindBy != null && locatorGroup.equals(jFindBy.group()))
                    byLocator = getFindByLocator(jFindBy);
            }
            return (byLocator != null)
                    ? byLocator
                    : getFindByLocator(field.getAnnotation(FindBy.class));
        } catch (Exception ex) {
            throw exception("Error in get locator for type '%s'", field.getType().getName()
                    + LINE_BREAK + ex.getMessage());
        }
    }

}