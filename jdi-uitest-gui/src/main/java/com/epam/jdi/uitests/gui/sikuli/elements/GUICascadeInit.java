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

import com.epam.jdi.uitests.core.interfaces.CascadeInit;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Page;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Section;
import com.epam.jdi.uitests.gui.sikuli.elements.enums.ContextType;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.*;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.JPage;
import org.sikuli.script.Pattern;

import java.lang.reflect.Field;

import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class GUICascadeInit extends CascadeInit {

    protected Class<?>[] stopTypes() { return new Class<?>[] {Object.class, Page.class, Section.class, Element.class}; }

    protected void fillPageFromAnnotation(Field field, IBaseElement instance, Class<?> parentType) {
        if (field.isAnnotationPresent(JPage.class))
            GuiAnnotationsUtil.fillPageFromAnnotation((Page) instance, field.getAnnotation(JPage.class), parentType);
    }

    protected IBaseElement fillInstance(IBaseElement instance, Field field) {
        BaseElement element = (BaseElement) instance;
        if (element.getPattern() == null)
            element.avatar.pattern = getNewLocator(field);
        return element;
    }
    protected IBaseElement specificAction(IBaseElement instance, Field field, Object parent, Class<?> type) {
        BaseElement element = (BaseElement) instance;
        if (type != null) {
            Pattern frameBy = GuiAnnotationsUtil.getPattern(type.getDeclaredAnnotation(JLocation.class),
                    type.getDeclaredAnnotation(JOffset.class));
            if (frameBy != null)
                element.avatar.context.add(ContextType.Frame, frameBy);
        }

        element.avatar.setSimilarity(GuiAnnotationsUtil.getSimilarity(field.getAnnotation(JLocation.class)));
        if (field.getAnnotation(JRegion.class) != null) {
            element.avatar.setRectangle(GuiAnnotationsUtil.getRectangle(field.getAnnotation(JRegion.class)));
            element.avatar.setX(GuiAnnotationsUtil.getX(field.getAnnotation(JRegion.class)));
            element.avatar.setY(GuiAnnotationsUtil.getY(field.getAnnotation(JRegion.class)));
            element.avatar.setW(GuiAnnotationsUtil.getWight(field.getAnnotation(JRegion.class)));
            element.avatar.setH(GuiAnnotationsUtil.getHeight(field.getAnnotation(JRegion.class)));
        }
        return element;
    }

    protected IBaseElement getElementsRules(Field field, String driverName, Class<?> type, String fieldName) throws IllegalAccessException, InstantiationException {
        if (!type.isInterface()) {
            BaseElement instance;
            if (MapClassToAnnotation.mapForConstructor.get(type) != null)
                instance = MapClassToAnnotation.getMapForConstructor().get(type).value.apply(field);
            else
                instance = (BaseElement) type.newInstance();
            instance.avatar.pattern = getNewLocator(field);
            instance.avatar.setDriverName(driverName);
            return instance;
        }
        throw exception("Unknown interface: " + type
                + ". Add relation interface -> class in VIElement.InterfaceTypeMap");
    }

    protected Pattern getNewLocatorFromField(Field field) {
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
    }

}
