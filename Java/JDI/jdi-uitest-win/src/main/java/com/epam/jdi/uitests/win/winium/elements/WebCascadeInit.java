package com.epam.jdi.uitests.win.winium.elements;

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
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.win.winium.elements.base.Element;
import com.epam.jdi.uitests.win.winium.elements.complex.Elements;
import com.epam.jdi.uitests.win.winium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.WebAnnotationsUtil;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.WebAnnotationsUtil.fillPageFromAnnotaiton;
import static com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.WebAnnotationsUtil.getFindByLocator;
import static com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.setUpTable;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class WebCascadeInit extends CascadeInit {

    protected Class<?>[] stopTypes() { return new Class<?>[] {Object.class, WebPage.class, Section.class, Element.class}; }

    protected void fillPageFromAnnotation(Field field, IBaseElement instance, Class<?> parentType) {
        if (field.isAnnotationPresent(JPage.class))
            fillPageFromAnnotaiton((WebPage) instance, field.getAnnotation(JPage.class), parentType);
    }

    protected IBaseElement fillInstance(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (element.getLocator() == null)
            element.avatar.byLocator = getNewLocator(field);
        return element;
    }
    @Override
    protected IBaseElement fillFromJDIAttribute(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (hasJDIAttribute(field))
            fillFromAttribute(element, field);
        return element;
    }

    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type, String fieldName) throws IllegalAccessException, InstantiationException {
        By newLocator = getNewLocator(field);
        BaseElement instance = null;
        if (isInterface(type, List.class)) {
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
                byLocator = getFindByLocator(jFindBy);
        }
        return byLocator != null
                ? byLocator
                : WebAnnotationsUtil.findByToBy(field.getAnnotation(FindBy.class));
    }
    protected boolean hasJDIAttribute(Field field) {
        JTable jTable = field.getAnnotation(JTable.class);
        return jTable != null;
    }
    private void fillFromAttribute(BaseElement instance, Field field) {
        JTable jTable = field.getAnnotation(JTable.class);
        if (jTable == null || !isInterface(field, ITable.class))
            return;
        setUpTable((ITable) instance, jTable);
    }

}