package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;


import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.TableHeaderTypes.COLUMNS_HEADERS;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JEntityTable {

    FindBy root() default @FindBy();
    JFindBy jRoot() default @JFindBy();
    String[] header() default {};
    String[] rowsHeader() default {};

    FindBy headers() default @FindBy();
    FindBy rowNames() default @FindBy();
    FindBy cell() default @FindBy();
    FindBy row() default @FindBy();
    FindBy column() default @FindBy();
    FindBy footer() default @FindBy();
    JFindBy jHeaders() default  @JFindBy();
    JFindBy jRowNames() default @JFindBy();
    JFindBy jCell() default     @JFindBy();
    JFindBy jRow() default      @JFindBy();
    JFindBy jColumn() default   @JFindBy();
    JFindBy jFooter() default   @JFindBy();

    int height() default -1;
    int width() default -1;
    String size() default "";

    int rowStartIndex() default 1;
    int colStartIndex() default 1;

    TableHeaderTypes headerType() default COLUMNS_HEADERS;
    boolean useCache() default false;

    Class<?>rowClass() default ;

    JFindBy[] JrowClass() default {};
    FindBy[] entityClass() default {};
    JFindBy[] JentityClass() default {};


}
