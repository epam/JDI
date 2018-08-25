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
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.web.selenium.driver.DriverTypes;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.base.J;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.single;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement.getClassFromInterface;
import static com.epam.jdi.uitests.core.settings.JDIData.group;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory.currentDriverName;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.*;
import static com.epam.jdi.uitests.web.settings.WebSettings.*;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class WebCascadeInit extends CascadeInit {

    private StringBuilder totalPath = new StringBuilder();
    private static final Set<String> EXTENSIONS = new HashSet<>(
        Arrays.asList(".jpg", ".jpeg", ".png"));

    public Class<?>[] stopTypes() {
        return new Class<?>[]{Object.class, WebPage.class, Section.class, Element.class};
    }

    @Override
    public Class<?>[] decorators() {
        return new Class<?>[]{IBaseElement.class, List.class, WebElement.class};
    }

    protected void fillPageFromAnnotation(Field field, IBaseElement instance) {
        WebPage page = (WebPage) instance;
        instance.setName(field);
        if (field.getType().isAnnotationPresent(JPage.class)) {
            fillPageFromAnnotaiton(page, field.getType().getAnnotation(JPage.class));
        } else {
            if (field.isAnnotationPresent(JPage.class)) {
                fillPageFromAnnotaiton(page, field.getAnnotation(JPage.class));
            }
        }
        WebPage.addPage(page);
    }

    private static void initDriver() {
        if (!initialized) {
            try {
                initFromProperties();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static <T> T initPageObject(Class<T> clazz) {
        return initPageObject(clazz, currentDriverName);
    }

    public static <T> T initPageObject(Class<T> clazz, Supplier<WebDriver> driver) {
        return initPageObject(clazz, useDriver(driver));
    }

    public static <T> T initPageObject(Class<T> clazz, DriverTypes driver) {
        return initPageObject(clazz, useDriver(driver));
    }

    public static <T> T initPageObject(Class<T> clazz, String driverName) {
        initDriver();
        T page;
        try {
            page = clazz.newInstance();
        } catch (Exception ignore) {
            try {
                page = clazz.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
            } catch (Exception ex) {
                throw new RuntimeException(
                    "Can't init PageObject: " + clazz.getName() + ". Exception: " + ex
                        .getMessage());
            }
        }
        new WebCascadeInit().initElements(page, driverName);
        return page;
    }

    public static void initPageObjects(Class<?>... clazz) {
        initPageObjects(currentDriverName, clazz);
    }

    public static void initPageObjects(WebDriver driver, Class<?>... clazz) {
        initPageObjects(useDriver(() -> driver), clazz);
    }

    public static void initPageObjects(DriverTypes driver, Class<?>... clazz) {
        initPageObjects(useDriver(driver), clazz);
    }

    public static void initPageObjects(String driverName, Class<?>... classes) {
        for (Class<?> clazz : classes) {
            initPageObject(clazz, driverName);
        }
    }

    protected IBaseElement fillInstance(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (!element.hasLocator()) {
            element.setAvatar(new GetElementModule(getNewLocator(field), element));
        }
        return element;
    }

    @Override
    protected IBaseElement fillFromJDIAnnotation(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        fillFromAnnotation(element, field);
        return element;
    }

    @Override
    protected IBaseElement specificAction(IBaseElement instance, Field field, Object parent,
                                          Class<?> type) {
        BaseElement element = (BaseElement) instance;
        if (parent != null && type == null) {
            return element;
        }
        By frameBy = getFrame(field.getDeclaredAnnotation(Frame.class));
        if (frameBy != null) {
            element.avatar.frameLocator = frameBy;
        }
        return element;
    }

    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type,
                                            String fieldName)
            throws IllegalAccessException, InstantiationException {
        By newLocator = getNewLocator(field);
        BaseElement instance = null;
        if (isClass(type, EntityTable.class)) {
            Type[] types =((ParameterizedType) field.getGenericType())
                    .getActualTypeArguments();
            instance = new EntityTable((Class<?>) types[0], (Class<?>) types[1]);
        }
        if (instance == null && isInterface(type, List.class)) {
            Class<?> elementClass = (Class<?>) ((ParameterizedType) field.getGenericType())
                    .getActualTypeArguments()[0];
            if (isClass(elementClass, WebElement.class)) {
                elementClass = J.class;
            } else if (elementClass.isInterface()) {
                elementClass = getClassFromInterface(type);
            }
            if (elementClass != null && !isClass(elementClass, Table.class)) {
                instance = new Elements(newLocator, elementClass);
            }
        }
        if (instance == null) {
            if (type.isInterface()) {
                type = getClassFromInterface(type);
            }
            if (type != null) {
                instance = (BaseElement) type.newInstance();
                if (newLocator == null) {
                    newLocator = instance.getLocator();
                }
                if (instance.getAvatar() != null && newLocator == null) {
                    instance.setAvatar(new GetElementModule(instance));
                } else {
                    instance.setAvatar(new GetElementModule(newLocator, instance));
                }
            }
        }
        if (instance == null) {
            throw exception(
                "Unknown interface: %s (%s). Add relation interface -> class in VIElement.InterfaceTypeMap",
                type, fieldName);
        }
        instance.avatar.setDriverName(driverName);
        return instance;
    }

    protected By getNewLocatorFromField(Field field) {
        JFindBy[] jfindbys = field.getAnnotationsByType(JFindBy.class);
        if (jfindbys.length > 1) {
            JFindBy groupFindBy = single(jfindbys, j -> group.equals(j.group()));
            if (groupFindBy != null)
                return findByToBy(groupFindBy);
            groupFindBy = single(jfindbys, j -> j.group().equals(""));
            if (groupFindBy != null)
                return findByToBy(groupFindBy);
        }
        if (field.isAnnotationPresent(JFindBy.class)) {
            return findByToBy(field.getAnnotation(JFindBy.class));
        }
        if (field.isAnnotationPresent(FindBy.class)) {
            return findByToBy(field.getAnnotation(FindBy.class));
        }
        if (field.isAnnotationPresent(Css.class)) {
            return findByToBy(field.getAnnotation(Css.class));
        }
        if (field.isAnnotationPresent(XPath.class)) {
            return findByToBy(field.getAnnotation(XPath.class));
        }
        if (field.isAnnotationPresent(ByText.class)) {
            return findByToBy(field.getAnnotation(ByText.class));
        }
        if (field.isAnnotationPresent(Attribute.class)) {
            return findByToBy(field.getAnnotation(Attribute.class));
        }
        if (field.isAnnotationPresent(ByClass.class)) {
            return findByToBy(field.getAnnotation(ByClass.class));
        }
        if (field.isAnnotationPresent(ById.class)) {
            return findByToBy(field.getAnnotation(ById.class));
        }
        if (field.isAnnotationPresent(ByName.class)) {
            return findByToBy(field.getAnnotation(ByName.class));
        }
        if (field.isAnnotationPresent(NgRepeat.class)) {
            return findByToBy(field.getAnnotation(NgRepeat.class));
        }
        if (field.isAnnotationPresent(NgBinding.class)) {
            return findByToBy(field.getAnnotation(NgBinding.class));
        }
        if (field.isAnnotationPresent(NgModel.class)) {
            return findByToBy(field.getAnnotation(NgModel.class));
        }
        if (field.isAnnotationPresent(ByTitle.class)) {
            return findByToBy(field.getAnnotation(ByTitle.class));
        }
        if (field.isAnnotationPresent(ByTag.class)) {
            return findByToBy(field.getAnnotation(ByTag.class));
        }
        if (field.isAnnotationPresent(ByType.class)) {
            return findByToBy(field.getAnnotation(ByType.class));
        }
        if (field.isAnnotationPresent(ByValue.class)) {
            return findByToBy(field.getAnnotation(ByValue.class));
        }
        return null;
    }

    private static void fillFromAnnotation(BaseElement instance, Field field) {
        try {
            ISetup setup = (ISetup) instance;
            setup.setup(field);
        } catch (Exception ignore) {
        }
    }
}