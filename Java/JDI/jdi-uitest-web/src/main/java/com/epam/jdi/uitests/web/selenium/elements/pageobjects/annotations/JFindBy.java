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


import java.lang.annotation.*;

import static com.epam.jdi.uitests.core.settings.JDIData.APP_VERSION;

/**
 * Created by 12345 on 07.11.2014.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(JFindBys.class)
public @interface JFindBy {
    // Selenium
    String css() default "";
    String tagName() default "";
    String linkText() default "";
    String partialLinkText() default "";
    String xpath() default "";

    // Text
    String text() default "";

    //Attributes
    JAttribute attribute() default @JAttribute(name = "", value = "");
    String id() default "";
    String name() default "";
    String className() default "";
    String value() default "";
    String title() default "";
    String type() default "";

    // Angular
    String model() default "";
    String binding() default "";
    String repeat() default "";

    // Group
    String group() default APP_VERSION;
}