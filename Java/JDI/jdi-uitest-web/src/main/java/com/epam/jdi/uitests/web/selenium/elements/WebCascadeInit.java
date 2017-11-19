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
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
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
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.*;
import com.epam.jdi.uitests.web.selenium.utils.Layout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.any;
import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement.getClassFromInterface;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory.currentDriverName;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.*;
import static com.epam.jdi.uitests.web.settings.WebSettings.*;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class WebCascadeInit extends CascadeInit {

    private boolean generateDefaultPath = false;
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

    protected void fillPageFromAnnotation(Field field, IBaseElement instance, Class<?> parentType) {
        if (field.getType().isAnnotationPresent(JPage.class)) {
            fillPageFromAnnotaiton((WebPage) instance, field.getType().getAnnotation(JPage.class),
                parentType);
        } else {
            if (field.isAnnotationPresent(JPage.class)) {
                fillPageFromAnnotaiton((WebPage) instance, field.getAnnotation(JPage.class),
                    parentType);
            }
        }
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

    @Override
    protected void preInitElements(Object parent, Class<?> parentType, Field field, String driverName)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            if (verifyLayout) {
                // Extracting path from ImagesRoot annotation of WebSite + path of webPage
                if (parent == null) {
                    totalPath.append(extractImageRootAnnotationValueFromWebSite(parentType));
                    totalPath.append(extractPath(field));
                }

                // Extracting path from the previous iteration and saving it to the current element and saving in to the imageRoot field of specific Page
                if ((parent instanceof IPage || parent instanceof IForm)
                    && totalPath.length() != 0) {
                    saveImagePathForFirstElement(parent);
                }

                // It adds element's path to the rootPath
                // Set imgPath for each Field
                if (parent != null) {
                    Object fieldObject = field.get(parent);
                    Method methodSetImgPath = extractSetImgPathMethod(fieldObject);
                    String totalPath = extractTotalPathForElement(parent, field);
                    setImgPathForField(methodSetImgPath, fieldObject, totalPath);
                }
            }
    }

    // Extract totalPath for Field (parent path + field path)
    private String extractTotalPathForElement(Object parent, Field field) {
        StringBuilder sb = new StringBuilder();

        if (parent instanceof IPage) {
            sb.append(((IPage) parent).getImageRoot())
                .append(totalPath.toString());
        } else {
            sb.append(getParentPath(parent))
                .append(fixImagePath(totalPath.toString()));
        }

        String valueFromImageAnnotation = extractImageAnnotationValueFromField(field);

        if (valueFromImageAnnotation == null) {
            sb.append(field.getName());
        } else if (valueFromImageAnnotation.equals("/")) {
            return null;
        } else {
            sb.append(valueFromImageAnnotation);
        }

        return fixImagePath(sb.toString());
    }

    // Extract link on the setImgPathForField() from Element or NULL if field isn't instance of IElement
    private Method extractSetImgPathMethod(Object fieldObject)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if (fieldObject instanceof IElement) {
            return fieldObject.getClass()
                .getMethod("setImgPath", String.class);
        }

        return null;
    }

    private static String fixImagePath(String path) {
        if (path != null) {
            return path.replaceAll("[\\\\|/]+", "/");
        }

        return null;
    }

    // Set totalPath for current Field.
    private void setImgPathForField(Method methodSetImgPath, Object fieldObject, String totalPath)
        throws IllegalAccessException, InvocationTargetException {
        if (methodSetImgPath != null) {
            if (fieldObject.getClass().getDeclaredFields().length == 0) {
                totalPath = checkTotalPathForLastElement(totalPath);
            }

            methodSetImgPath.invoke(fieldObject, totalPath);
        }
    }

    private String checkTotalPathForLastElement(String totalPath) {
        if (!isImage(totalPath)) {
            if (generateDefaultPath) {
                return getTotalPathIfImageExists(totalPath);
            } else {
                return null;
            }
        }
        Layout.validateImagePath(WebSite.getDefaultPath() + totalPath);
        return totalPath;
    }

    //if we find file with title -> save it into totalPath
    //if we don't find file with title -> totalPath = null
    private String getTotalPathIfImageExists(String path) {
        for (String extension : EXTENSIONS) {
            if (new File(WebSite.getDefaultPath() + path + extension).exists()) {
                return path + extension;
            }
        }
        return null;
    }

    // Verify that totalPath is image
    private boolean isImage(String totalPath) {
        String path = totalPath.toLowerCase();
        return path.endsWith(".png")
            || path.endsWith(".jpg")
            || path.endsWith(".jpeg");
    }

    //Get path from first parent which contains it
    private String getParentPath(Object parent) {
        StringBuilder parentPath = new StringBuilder();

        while (parentPath.length() == 0) {
            if (parent instanceof IElement) {
                parentPath.append(((IElement) parent).getImgPath());
            } else if (parent instanceof IPage) {
                parentPath.append(((IPage) parent).getImageRoot());
            } else if (parent instanceof IBaseElement) {
                parent = ((IBaseElement) parent).getParent();
            } else {
                break;
            }
        }

        checkNotNullParentPath(parentPath);

        return parentPath.toString();
    }

    private void checkNotNullParentPath(StringBuilder parentPath) {
        if (parentPath.toString().equals("null")) {
            parentPath.setLength(0);
            parentPath.append('/');
        }
    }

    private void saveImagePathForFirstElement(Object parent) {
        if (parent instanceof IPage) {
            ((IPage) parent).setImageRoot(fixImagePath(totalPath.toString()));
        } else if (parent instanceof IForm) {
            ((IForm) parent).setImgPath(fixImagePath(totalPath.toString()));
        }
        totalPath.setLength(0);
    }

    private String extractImageRootAnnotationValueFromWebSite(Class<?> parentType) {
        ImagesRoot annotation = parentType.getAnnotation(ImagesRoot.class);
        if (annotation != null) {
            return annotation.value();
        }

        return "/";
    }

    private String extractPath(Field field) {
        String annotationValue = extractImageAnnotationValueFromField(field);

        if (annotationValue != null) {
            return annotationValue;
        } else if (generateDefaultPath) {
            return "/" + field.getName() + "/";
        }

        return "";
    }

    private String extractImageAnnotationValueFromField(Field field) {
        ImageFile annotation = field.getAnnotation(ImageFile.class);
        if (annotation != null) {
            return annotation.value();
        }
        return null;
    }

    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type,
                                            String fieldName)
            throws IllegalAccessException, InstantiationException {
        By newLocator = getNewLocator(field);
        BaseElement instance = null;
        if (isClass(type, EntityTable.class)) {
            java.lang.reflect.Type[] types =((ParameterizedType) field.getGenericType())
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
        if (jfindbys.length > 0 && any(jfindbys, j -> APP_VERSION.equals(j.group()))) {
            return findByToBy(first(jfindbys, j -> APP_VERSION.equals(j.group())));
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