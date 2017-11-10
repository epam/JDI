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


import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.*;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.TryCatchUtil.tryGetResult;
import static com.epam.jdi.uitests.core.annotations.AnnotationsUtil.getFunction;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.core.settings.JDISettings.verifyLayout;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public abstract class CascadeInit {

    public Class<?>[] decorators() {
        return new Class<?>[]{IBaseElement.class, List.class};
    }

    public synchronized void initElements(Object parent, String driverName) {
        setFieldsForInit(parent, getFields(parent, decorators(), stopTypes()), parent.getClass(),
                driverName);
    }

    protected abstract Class<?>[] stopTypes();

    public synchronized void initStaticPages(Class<?> parentType, String driverName) {
        setFieldsForInit(null,
                getFields(asList(parentType.getDeclaredFields()), decorators(),
                        f -> isStatic(f.getModifiers())),
                parentType, driverName);
    }

    private void setFieldsForInit(Object parent, List<Field> fields, Class<?> parentType,
                                  String driverName) {
        foreach(fields, field -> setElement(parent, parentType, field, driverName));
    }

    public synchronized <T extends Application> T initPages(Class<T> site, String driverName) {
        T instance = tryGetResult(site::newInstance);
        instance.setDriverName(driverName);
        initElements(instance, driverName);
        return instance;
    }

    protected abstract void fillPageFromAnnotation(Field field, IBaseElement instance,
                                                   Class<?> parentType);

    private StringBuilder totalPath;
    private Pattern p = Pattern.compile("\\S*[(]value=((\\/?\\w*.?)*\\/?)[)]");

    private void setElement(Object parent, Class<?> parentType, Field field, String driverName) {
        try {
            Class<?> type = field.getType();
            IBaseElement instance = isInterface(type, IPage.class)
                    ? getInstancePage(parent, field, type, parentType)
                    : getInstanceElement(parent, type, parentType, field, driverName);
            instance.setName(field);
            if (parent != null) {
                instance.getAvatar().setDriverName(driverName);
            }
            instance.setTypeName(type.getSimpleName());
            field.set(parent, instance);

            createDefaultImagePath(parent, parentType, field);
            if (verifyLayout) {
                if (parent instanceof IPage && totalPath.length() != 0)
                    saveImagePathForPage((IPage) parent);
                setImgPath(parent, field);
            }
            if (isInterface(field, IComposite.class)) {
                initElements(instance, driverName);
            }
        } catch (Exception ex) {
            throw exception("Error in setElement for field '%s' with parent '%s'", field.getName(),
                    parentType == null ? "NULL Class"
                            : parentType.getSimpleName() + LINE_BREAK + ex.getMessage());
        }
    }

    private void setImgPath(Object parent, Field field)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (parent != null) {
            Method methodSetImgPath = field.getType()
                    .getMethod("setImgPath", new Class[]{String.class});
            Object titleElement = field.get(parent);
            getValueFromImageAnnotation(field);

            if (parent instanceof IPage) {
                setImgPathByMethod(methodSetImgPath, titleElement,
                        ((IPage) parent).getImageRoot() + totalPath.toString());
            } else {
                setImgPathByMethod(methodSetImgPath, titleElement,
                        getParentPath(parent) + totalPath.toString());
            }
        }
    }

    private void setImgPathByMethod(Method methodSetImgPath, Object titleElement, String imgPath)
            throws IllegalAccessException, InvocationTargetException {
        methodSetImgPath.invoke(titleElement, imgPath);
        totalPath.setLength(0);
    }

    private String getParentPath(Object parent) {
        StringBuilder parentPath = new StringBuilder();

        while (parentPath.length() == 0) {
            if (parent instanceof IElement) {
                parentPath.append(((IElement) parent).getImgPath());
            } else if (parent instanceof IPage) {
                parentPath.append(((IPage) parent).getImageRoot());
            }
            parent = ((IBaseElement) parent).getParent();
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

    private void saveImagePathForPage(IPage parent) {
        parent.setImageRoot(totalPath.toString());
        totalPath.setLength(0);
    }

    private void createDefaultImagePath(Object parent, Class<?> parentType, Field field) {
        if (parent == null) {
            Annotation[] annotations = parentType.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (isAnnotationEquals(annotation, "ImagesRoot")) {
                    getValueFromImagesRootAnnotation(annotation);
                    getValueFromImageAnnotation(field);
                    break;
                }
            }
            generateDelaultPathIfImagesRootAnnotationDoesNotExist(field);
        }
    }

    private void generateDelaultPathIfImagesRootAnnotationDoesNotExist(Field field) {
        if (totalPath == null || totalPath.length() == 0) {
            totalPath = new StringBuilder("/");
            getValueFromImageAnnotation(field);
        }
    }

    private void getValueFromImagesRootAnnotation(Annotation annotation) {
        Matcher matcher = p.matcher(annotation.toString());
        if (matcher.matches()) {
            totalPath = new StringBuilder(matcher.group(1));
        } else {
            totalPath = new StringBuilder("/");
        }
    }

    private boolean isAnnotationEquals(Annotation annotation, String imagesRoot) {
        return annotation.annotationType().getSimpleName().equals(imagesRoot);
    }

    private void getValueFromImageAnnotation(Field field) {
        Matcher matcher;
        for (Annotation annotation1 : field.getDeclaredAnnotations()) {
            if (isAnnotationEquals(annotation1, "Image")) {
                matcher = p.matcher(annotation1.toString());
                if (matcher.matches()) {
                    totalPath.append(matcher.group(1));
                    break;
                }
            }
        }
    }

    private IBaseElement getInstancePage(Object parent, Field field, Class<?> type,
                                         Class<?> parentType)
            throws IllegalAccessException, InstantiationException {
        IBaseElement instance = (IBaseElement) getValueField(field, parent);
        if (instance == null) {
            instance = (IBaseElement) type.newInstance();
        }
        fillPageFromAnnotation(field, instance, parentType);
        return instance;
    }

    private IBaseElement getInstanceElement(Object parent, Class<?> type, Class<?> parentType,
                                            Field field, String driverName) {
        IBaseElement instance = createChildFromFieldStatic(parent, parentType, field, type,
                driverName);
        instance.setFunction(getFunction(field));
        return instance;
    }

    protected abstract IBaseElement fillInstance(IBaseElement instance, Field field);

    protected abstract IBaseElement getElementsRules(Field field, String driverName, Class<?> type,
                                                     String fieldName)
            throws IllegalAccessException, InstantiationException;

    protected IBaseElement specificAction(IBaseElement instance, Field field, Object parent,
                                          Class<?> type) {
        return instance;
    }

    protected IBaseElement fillFromJDIAnnotation(IBaseElement instance, Field field) {
        return instance;
    }

    private IBaseElement createChildFromFieldStatic(Object parent, Class<?> parentClass,
                                                    Field field, Class<?> type, String driverName) {
        IBaseElement instance = (IBaseElement) getValueField(field, parent);
        if (instance == null) {
            try {
                instance = getElementInstance(field, driverName, parent);
            } catch (Exception ex) {
                throw exception(
                        format("Can't create child for parent '%s' with type '%s'. Exception: %s",
                                parentClass.getSimpleName(), field.getType().getSimpleName(),
                                ex.getMessage()));
            }
        }else {
            instance = fillInstance(instance, field);
        }
        if (field.isAnnotationPresent(Root.class)){
            instance.setParent(null);
        }else {
            instance.setParent(parent);
        }
        instance = fillFromJDIAnnotation(instance, field);
        instance = specificAction(instance, field, parent, type);
        return instance;
    }

    private IBaseElement getElementInstance(Field field, String driverName, Object parent) {
        Class<?> type = field.getType();
        String fieldName = field.getName();
        try {
            return getElementsRules(field, driverName, type, fieldName);
        } catch (Exception ex) {
            throw exception("Error in getElementInstance for field '%s'%s with type '%s'",
                    fieldName,
                    parent != null ? "in " + parent.getClass().getSimpleName() : "",
                    type.getSimpleName() + LINE_BREAK + ex.getMessage());
        }
    }

    protected abstract <T> T getNewLocatorFromField(Field field);

    protected <T> T getNewLocator(Field field) {
        try {
            return getNewLocatorFromField(field);
        } catch (Exception ex) {
            throw exception("Error in get locator for type '%s'", field.getType().getName()
                    + LINE_BREAK + ex.getMessage());
        }
    }
}