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

    FindBy root()       default @FindBy();
    String[] header()   default {};
    String[] rowsHeader() default {};

    FindBy headers()    default @FindBy();
    FindBy rowNames()   default @FindBy();
    FindBy cell()       default @FindBy();
    FindBy row()        default @FindBy();
    FindBy column()     default @FindBy();
    FindBy footer()     default @FindBy();

    int height()    default -1;
    int width()     default -1;
    String size()   default "";

    int rowStartIndex() default 1;
    int colStartIndex() default 1;

    TableHeaderTypes headerType() default COLUMNS_HEADERS;
    boolean useCache()  default false;
}
