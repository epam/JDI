package com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Natalia_Grebenshchik on 1/21/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JRegion {

    int[] rectangle() default {0, 0, 0, 0};

    String group() default "";
}
