package com.epam.jdi.site.epam.CustomElements;

import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Roman_Iovlev on 8/23/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JTree {
    FindBy select() default @FindBy();
    FindBy[] levels() default {};
}
