package com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Natalia_Grebenshchikova on 1/21/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JCheckBox {
    String checkImg() default "";

    String unCheckImg() default "";

    double similarity() default 0.7;

    String group() default "";
}
