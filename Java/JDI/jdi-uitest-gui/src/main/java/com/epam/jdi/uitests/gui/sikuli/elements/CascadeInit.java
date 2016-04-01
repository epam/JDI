package com.epam.jdi.uitests.gui.sikuli.elements;
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



import com.epam.commons.LinqUtils;
import com.epam.commons.pairs.Pairs;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.gui.sikuli.elements.base.CheckBox;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Page;
import com.epam.jdi.uitests.gui.sikuli.elements.enums.ContextType;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.*;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.JPage;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.*;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.TryCatchUtil.tryGetResult;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isStatic;
/*
*
 * Created by Roman_Iovlev on 6/10/2015.
*/

public abstract class CascadeInit implements IBaseElement {
    public static boolean firstInstance = true;

    public static void initElements(Object parent, String driverName) {
        if (parent.getClass().getName().contains("$")) return;
        Object parentInstance = null;
        Class<?> parentType = parent.getClass();
        boolean firstInstanceCreated = false;

        if (firstInstance) {
            parentInstance = getParentInstance(parentType);
            firstInstanceCreated = true;
        }

        initSubElements(parent, parentInstance, driverName);

        if (isClass(parentType, Page.class) && parentType.isAnnotationPresent(JPage.class))
            GuiAnnotationsUtil.fillPageFromAnnotation((Page) parent, parentType.getAnnotation(JPage.class), null);

        if (firstInstanceCreated)
            firstInstance = true;
    }

    private static Object getParentInstance(Class<?> parentType) {
        firstInstance = false;
        BaseElement.createFreeInstance = true;
        Object parentInstance = tryGetResult(parentType::newInstance);
        BaseElement.createFreeInstance = false;
        return parentInstance;
    }

    private static void initSubElements(Object parent, Object parentInstance, String driverName) {
        foreach(deepGetFields(parent, IBaseElement.class),
                field -> setElement(parent, parentInstance, field, driverName));
    }

    private static List<Field> deepGetFields(Class<?> clazz) {
        List<Field> result = new ArrayList<>();
        if (isInterface(clazz, IBaseElement.class))
            result.addAll(deepGetFields(clazz.getSuperclass()));
        result.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return result;
    }

    public static List<Field> deepGetFields(Object obj, Class<?> type) {
        return LinqUtils.where(deepGetFields(obj.getClass()), field -> !isStatic(field.getModifiers()) && (isClass(field, type) || isInterface(field, type)));
    }

    public static void initPages(Class<?> parentType, String driverName) {
        foreach(getStaticFields(parentType, BaseElement.class),
                field -> setElement(parentType, field, driverName));
    }

    public static void initPages(Screen site, String driverName) {
        initElements(site, driverName);
    }

    private static void setElement(Class<?> parentType, Field field, String driverName) {
        try {
            Class<?> type = field.getType();
            BaseElement instance;
            if (isClass(type, Page.class)) {
                instance = (BaseElement) getValueField(field, null);
                if (instance == null)
                    instance = (BaseElement) type.newInstance();
                fillPage(instance, field, parentType);
            } else {
                instance = createChildFromFieldStatic(parentType, field, type, driverName);
                instance.function = GuiAnnotationsUtil.getFunction(field);
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

    private static String getClassName(Object obj) {
        return obj == null ? "NULL Class" : obj.getClass().getSimpleName();
    }

    public static void setElement(Object parent, Object pInstance, Field field, String driverName) {
        try {
            Class<?> type = field.getType();
            Object parentInstance = pInstance != null
                    ? pInstance
                    : parent;
            BaseElement instance;
            if (isClass(type, Page.class)) {
                instance = (BaseElement) getValueField(field, parentInstance);
                if (instance == null)
                    instance = (BaseElement) type.newInstance();
                fillPage(instance, field, parent != null ? parent.getClass() : null);
            } else {
                instance = createChildFromField(parent, parentInstance, field, type, driverName);
                instance.function = GuiAnnotationsUtil.getFunction(field);
            }
            instance.setName(field);
            instance.avatar.setDriverName(driverName);
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

    private static void fillPage(BaseElement instance, Field field, Class<?> parentType) {
        if (field.isAnnotationPresent(JPage.class))
            GuiAnnotationsUtil.fillPageFromAnnotation((Page) instance, field.getAnnotation(JPage.class), parentType);
    }

    private static BaseElement createChildFromFieldStatic(Class<?> parentClass, Field field, Class<?> type, String driverName) {
        BaseElement instance = (BaseElement) getValueField(field, null);
        if (instance == null)
            try {
                instance = getElementInstance(type, field.getName(), getNewPattern(field), driverName, field);
            } catch (Exception ex) {
                throw exception(format("Can't create child for parent '%s' with type '%s'",
                        parentClass.getSimpleName(), field.getType().getSimpleName()));
            }
        else if (instance.getPattern() == null)
            instance.avatar.pattern = getNewPattern(field);
        if (type != null) {
            Pattern frameBy = GuiAnnotationsUtil.getPattern(type.getDeclaredAnnotation(JLocation.class),
                    type.getDeclaredAnnotation(JOffset.class));
            if (frameBy != null)
                instance.avatar.context.add(ContextType.Frame, frameBy);
        }

        instance.avatar.setSimilarity(GuiAnnotationsUtil.getSimilarity(field.getAnnotation(JLocation.class)));
        if (field.getAnnotation(JRegion.class) != null) {
            instance.avatar.setRectangle(GuiAnnotationsUtil.getRectangle(field.getAnnotation(JRegion.class)));
            instance.avatar.setX(GuiAnnotationsUtil.getX(field.getAnnotation(JRegion.class)));
            instance.avatar.setY(GuiAnnotationsUtil.getY(field.getAnnotation(JRegion.class)));
            instance.avatar.setW(GuiAnnotationsUtil.getWight(field.getAnnotation(JRegion.class)));
            instance.avatar.setH(GuiAnnotationsUtil.getHeight(field.getAnnotation(JRegion.class)));
        }

        return instance;
    }

    private static BaseElement createChildFromField(Object parent, Object parentInstance, Field field, Class<?> type, String driverName) {
        BaseElement instance = (BaseElement) getValueField(field, parentInstance);

        CheckBox d = null;
        if (instance == null)
            try {
                instance = getElementInstance(type, field.getName(), getNewPattern(field), driverName, field);
            } catch (Exception ex) {
                throw exception(
                        format("Can't create child for parent '%s' with type '%s'",
                                parentInstance.getClass().getSimpleName(), field.getType().getSimpleName()));
            }
        else if (instance.getPattern() == null)
            instance.avatar.pattern = getNewPattern(field);

        instance.avatar.context = (isBaseElement(parent))
                ? ((BaseElement) parent).avatar.context.copy()
                : new Pairs<>();
        if (type != null) {
            Pattern frameBy = GuiAnnotationsUtil.getPattern(type.getDeclaredAnnotation(JLocation.class),
                    type.getDeclaredAnnotation(JOffset.class));
            if (frameBy != null)
                instance.avatar.context.add(ContextType.Frame, frameBy);
        }
        if (isBaseElement(parent)) {
            Pattern parentLocator = ((BaseElement) parent).getPattern();
            if (parentLocator != null)
                instance.avatar.context.add(ContextType.Locator, parentLocator);
        }

        if (field.getAnnotation(JLocation.class) != null)
            instance.avatar.setSimilarity(GuiAnnotationsUtil.getSimilarity(field.getAnnotation(JLocation.class)));
        if (field.getAnnotation(JRegion.class) != null) {
            instance.avatar.setRectangle(GuiAnnotationsUtil.getRectangle(field.getAnnotation(JRegion.class)));
            instance.avatar.setX(GuiAnnotationsUtil.getX(field.getAnnotation(JRegion.class)));
            instance.avatar.setY(GuiAnnotationsUtil.getY(field.getAnnotation(JRegion.class)));
            instance.avatar.setW(GuiAnnotationsUtil.getWight(field.getAnnotation(JRegion.class)));
            instance.avatar.setH(GuiAnnotationsUtil.getHeight(field.getAnnotation(JRegion.class)));
        }

        return instance;
    }

    private static boolean isBaseElement(Object obj) {
        return isClass(obj.getClass(), BaseElement.class);
    }

    private static BaseElement getElementInstance(Class<?> type, String fieldName, Pattern newPattern, String driverName, Field field) {
        try {
            if (!type.isInterface()) {
                BaseElement instance;
                if (MapClassToAnnotation.mapForConstructor.get(type) != null)
                    instance = MapClassToAnnotation.getMapForConstructor().get(type).value.apply(field);
                else
                    instance = (BaseElement) type.newInstance();
                instance.avatar.pattern = newPattern;
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

    private static Pattern getNewPattern(Field field) {
        try {
            Pattern pattern = null;
            String locatorGroup = APP_VERSION;
            if (locatorGroup != null) {
                JLocation jLocation = field.getAnnotation(JLocation.class);
                JOffset jOffset = field.getAnnotation(JOffset.class);
                if (jLocation != null && locatorGroup.equals(jLocation.group()))
                    pattern = GuiAnnotationsUtil.getPattern(jLocation, jOffset);
            }
            return (pattern != null)
                    ? pattern
                    : GuiAnnotationsUtil.getPattern(field.getAnnotation(JLocation.class),
                    field.getAnnotation(JOffset.class));
        } catch (Exception ex) {
            throw exception("Error in get patter for type '%s'", field.getType().getName()
                    + LINE_BREAK + ex.getMessage());
        }
    }

}
