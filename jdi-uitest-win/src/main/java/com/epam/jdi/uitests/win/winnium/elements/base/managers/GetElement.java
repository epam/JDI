package com.epam.jdi.uitests.win.winnium.elements.base.managers;

import com.epam.commons.ReflectionUtils;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.win.winnium.elements.BaseElement;
import com.epam.jdi.uitests.win.winnium.elements.common.Button;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;


public class GetElement {
    private BaseElement element;

    public GetElement(BaseElement element) {
        this.element = element;
    }

    public static boolean namesEqual(String name1, String name2) {
        return name1.toLowerCase().replace(" ", "").equals(name2.toLowerCase().replace(" ", ""));
    }

    public Button getButton(String buttonName) {
        List<Field> fields = ReflectionUtils.getFields(element, IButton.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s.", toString());
            case 1:
                return (Button) getValueField(fields.get(0), element);
            default:
                Optional<Button> buttonOpt = fields.stream().map(f -> (Button) getValueField(f, element)).
                        filter(b -> namesEqual(toButton(b.getName()), toButton(buttonName))).findFirst();
                return buttonOpt.orElseThrow(
                        () -> exception("Can't find button '%s' for Element '%s'", buttonName, toString()));
        }
    }

    private String toButton(String buttonName) {
        return buttonName.toLowerCase().contains("button") ? buttonName : buttonName + "button";
    }
}