package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;


import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JEntityTable {
    FindBy[] rowClass() default {} ;
    JFindBy[] JrowClass() default {};
    FindBy[] entityClass() default {};
    JFindBy[] JentityClass() default {};


}
