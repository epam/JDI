package com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations;

import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.gui.sikuli.elements.PatternCreator;
import com.epam.jdi.uitests.gui.sikuli.elements.base.CheckBox;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;
import com.epam.jdi.uitests.gui.sikuli.elements.common.Button;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.common.JCheckBox;
import org.sikuli.script.Pattern;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Natalia_Grebenshchikova on 1/25/2016.
 */
public final class MapClassToAnnotation {
    private MapClassToAnnotation() { }
    public static Map<Class, Pair<Class, Function<Field, Pattern>>> mapForPatternCreator;
    public static Map<Class<CheckBox>, Pair<Class, Function<Field, CheckBox>>> mapForConstructor;

    static {
        mapForPatternCreator = new HashMap<Class, Pair<Class, Function<Field, Pattern>>>();
        mapForPatternCreator.put(Element.class, new Pair<Class, Function<Field, Pattern>>(JLocation.class, field -> PatternCreator.getPattern(field)));
        mapForPatternCreator.put(CheckBox.class, new Pair<Class, Function<Field, Pattern>>(JCheckBox.class, field -> PatternCreator.getCheckBoxPattern(field)));
        mapForPatternCreator.put(Button.class, new Pair<Class, Function<Field, Pattern>>(JLocation.class, field -> PatternCreator.getPattern(field)));

        mapForConstructor = new HashMap<Class<CheckBox>, Pair<Class, Function<Field, CheckBox>>>();
        mapForConstructor.put(CheckBox.class, new Pair<Class, Function<Field, CheckBox>>(JCheckBox.class, field -> {
            JCheckBox jCheckBox = field.getAnnotation(JCheckBox.class);
            return new CheckBox(jCheckBox.checkImg(), jCheckBox.unCheckImg());
        }));
    }

    public static Map<Class<CheckBox>, Pair<Class, Function<Field, CheckBox>>> getMapForConstructor() {
        if (mapForConstructor != null)
            return mapForConstructor;

        return  null;

    }
}
