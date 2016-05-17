package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 12345 on 17.05.2016.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JDropdown {

    FindBy root() default @FindBy();
    FindBy value() default @FindBy();
    FindBy list() default @FindBy();
    FindBy expand() default @FindBy();
    FindBy elementByName() default @FindBy();

}
