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


import com.epam.jdi.uitests.core.interfaces.CascadeInit;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.core.interfaces.complex.IMenu;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JMenu;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.*;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.*;

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
        if (!element.hasLocator())
            element.setAvatar(new GetElementModule(getNewLocator(field), instance));
        return element;
    }
    @Override
    protected IBaseElement fillFromJDIAttribute(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        fillFromAttribute(element, field);
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
                instance.setAvatar(new GetElementModule(newLocator, instance));
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
            : findByToBy(field.getAnnotation(FindBy.class));
    }

    private void fillFromAttribute(BaseElement instance, Field field) {
        setUpTableFromAnnotation(instance, field);
        setUpMenuFromAnnotation(instance, field);
        setUpDropdownFromAnnotation(instance, field);
    }

    private void setUpTableFromAnnotation(BaseElement instance, Field field) {
        JTable jTable = field.getAnnotation(JTable.class);
        if (jTable == null || !isInterface(field, ITable.class))
            return;
        setUpTable((Table) instance, jTable);
    }
    private void setUpDropdownFromAnnotation(BaseElement instance, Field field) {
        JDropdown jDropdown = field.getAnnotation(JDropdown.class);
        if (jDropdown == null || !isInterface(field, IDropDown.class))
            return;
        setUpDropdown((Dropdown) instance, jDropdown);
    }
    private void setUpMenuFromAnnotation(BaseElement instance, Field field) {
        JMenu jMenu = field.getAnnotation(JMenu.class);
        if (jMenu == null || !isInterface(field, IMenu.class))
            return;
        setUpMenu((Menu) instance, jMenu);
    }

}