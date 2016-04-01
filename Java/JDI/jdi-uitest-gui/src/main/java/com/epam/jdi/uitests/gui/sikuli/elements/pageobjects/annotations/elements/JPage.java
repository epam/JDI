package com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements;

import com.epam.jdi.uitests.gui.sikuli.elements.CheckPageTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JPage {
    String fileLogoPath() default "";

    double similarity() default 0.7;

    int[] rectangle() default {0, 0, 0, 0};

    CheckPageTypes checkPage() default CheckPageTypes.NONE;
}
