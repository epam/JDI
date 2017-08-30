package com.epam.jdi.uitests.web.selenium.elements.complex;
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


import com.epam.jdi.uitests.core.interfaces.complex.ICheckList;
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JCheckList;
import org.openqa.selenium.By;
import java.lang.reflect.Field;

import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;

/**
 * Select control implementation
 *
 * @author Alexeenko Yan
 * @author Belousov Andrey
 */
public class CheckList<TEnum extends Enum> extends MultiSelector<TEnum> implements ICheckList<TEnum> {
    public CheckList() {
        super();
    }

    public CheckList(By optionsNamesLocator) {
        super(optionsNamesLocator);
    }

    public CheckList(By optionsNamesLocator, By allOptionsNamesLocator) {
        super(optionsNamesLocator, allOptionsNamesLocator);
    }

    public static void setUp(BaseElement el, Field field) {
        if (!fieldHasAnnotation(field, JCheckList.class, ICheckList.class)) {
            return;
        }
        ((CheckList) el).setUp(field.getAnnotation(JCheckList.class));
    }

    private ICheckList setUp(JCheckList jCheckList) {
        By root = findByToBy(jCheckList.root());
        By list = findByToBy(jCheckList.list());
        String separator = jCheckList.separator();

        if (root == null) {
            root = findByToBy(jCheckList.jRoot());
        }
        if (root != null) {
            Element el = new Element(root);
            el.setParent(getParent());
            setParent(el);
            setAvatar(root);
        }

        if (list == null) {
            list = findByToBy(jCheckList.jList());
        }
        if (list != null) {
            this.allLabels = new GetElementType(list, this);
        }

        setValuesSeparator(separator);

        return this;
    }
}