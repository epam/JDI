package com.epam.jdi.uitests.gui.sikuli.elements;

import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.GuiAnnotationsUtil;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JLocation;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JOffset;
import org.sikuli.script.Pattern;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;


/**
 * Created by Natalia_Grebenshchikova on 1/25/2016.
 */
public final class PatternCreator {
    private PatternCreator() { }

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

    public static Pattern getPattern(Field field) {
        for (Annotation a : field.getAnnotations()) {
            switch (field.getType().getName()) {
                case "com.epam.jdi.uitests.gui.sikuli.elements.base.CheckBox":
                    return getCheckBoxPattern(field);
                default:
                    System.out.println("nothing");
            }
        }

        return null;
    }

    public static Pattern getCheckBoxPattern(Field field) {
        try {
            return null;
        } catch (Exception ex) {
            throw exception("Error in get patter for type '%s'", field.getType().getName()
                    + LINE_BREAK + ex.getMessage());
        }
    }
}
