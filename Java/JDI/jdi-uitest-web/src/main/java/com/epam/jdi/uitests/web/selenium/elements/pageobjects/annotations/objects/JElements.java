package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.web.selenium.elements.base.IHasElement;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JElements {
    FindBy root() default @FindBy();
    FindBy list() default @FindBy();

    FindBy jRoot() default @FindBy();
    FindBy jList() default @FindBy();
}