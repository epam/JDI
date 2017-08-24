package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JDropList {
    FindBy root() default @FindBy();
    FindBy list() default @FindBy();
    String separator() default ", ";
    FindBy button() default @FindBy();
    FindBy valueLocator() default @FindBy();

    JFindBy jRoot() default @JFindBy();
    JFindBy jList() default @JFindBy();
    JFindBy jButton() default @JFindBy();
    JFindBy jValueLocator() default @JFindBy();
}
