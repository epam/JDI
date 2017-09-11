package com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects;

import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.TableHeaderTypes.COLUMNS_HEADERS;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JTable {
    FindBy root() default @FindBy();

    String columnHeadersInTableXpath() default "";
    String rowsInTableXpath() default "";
    String headerInRowXpath() default "";
    String columnsInRowXpath() default "";

    int rowStartIndex() default 1;
    int colStartIndex() default 1;

    TableHeaderTypes headerType() default COLUMNS_HEADERS;

    boolean useCache() default true;
}
