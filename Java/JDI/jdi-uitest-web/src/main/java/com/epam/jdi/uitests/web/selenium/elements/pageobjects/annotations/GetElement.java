package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations;
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
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 9/3/2015.
 */
public class GetElement {
    private BaseElement element;

    public GetElement(BaseElement element) {
        this.element = element;
    }

    public static boolean namesEqual(String name1, String name2) {
        return name1.toLowerCase().replace(" ", "").equals(name2.toLowerCase().replace(" ", ""));
    }

    public Button getButton(String buttonName) {
        List<Field> fields = getFields(element, IButton.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s.", toString());
            case 1:
                return (Button) getValueField(fields.get(0), element);
            default:
                Collection<Button> buttons = LinqUtils.select(fields, f -> (Button) getValueField(f, element));
                Button button = LinqUtils.first(buttons, b -> namesEqual(toButton(b.getName()), toButton(buttonName)));
                if (button == null)
                    throw exception("Can't find button '%s' for Element '%s'", buttonName, toString());
                return button;
        }
    }

    private String toButton(String buttonName) {
        return buttonName.toLowerCase().contains("button") ? buttonName : buttonName + "button";
    }

    public Button getButton(Functions funcName) {
        List<Field> fields = getFields(element, IButton.class);
        if (fields.size() == 1)
            return (Button) getValueField(fields.get(0), element);
        Collection<Button> buttons = LinqUtils.select(fields, f -> (Button) getValueField(f, element));
        Button button = LinqUtils.first(buttons, b -> b.function.equals(funcName));
        if (button == null) {
            String name = funcName.name;
            String buttonName = name.toLowerCase().contains("button") ? name : name + "button";
            button = LinqUtils.first(buttons, b -> namesEqual(b.getName(), buttonName));
            if (button == null)
                throw exception("Can't find button '%s' for Element '%s'", name, toString());
        }
        return button;
    }

    public Text getTextElement() {
        Field textField = LinqUtils.first(getClass().getDeclaredFields(), f -> (f.getType() == Text.class) || (f.getType() == IText.class));
        if (textField == null)
            throw exception("Can't find Text Element '%s'", toString());
        return (Text) getValueField(textField, element);
    }
}