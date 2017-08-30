package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JComboBox {
    JFindBy root() default @JFindBy();
    JFindBy value() default @JFindBy();
    JFindBy list() default @JFindBy();
    JFindBy expand() default @JFindBy();
    JFindBy labelLocator() default @JFindBy();
}