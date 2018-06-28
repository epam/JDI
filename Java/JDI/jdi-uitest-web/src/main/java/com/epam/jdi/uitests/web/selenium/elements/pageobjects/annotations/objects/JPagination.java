package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;
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

import org.openqa.selenium.support.FindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Morokov Danila on 15.05.2018.
 */

/**
 * Usable for https://epam.github.io/JDI/simple-table.html page
 *
 * @JPagination(root = @FindBy(css = ".uui-pagination"),
 *              firstLocator = @FindBy(xpath = "//*[text() = 'First']"),
 *              lastLocator = @FindBy(xpath = "//*[text() = 'Last']"),
 *              nextLocator = @FindBy(css = ".fa-long-arrow-right"),
 *              previousLocator = @FindBy(css = ".fa-long-arrow-left"),
 *              pagesLocator = @FindBy(xpath = "//a[@href and text() = '%s']"))
 * public IPagination myPagination;
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JPagination {

    /**
     * Locator of Paginator's container for assertion whether element is displayed or not.
     * @return
     */
    FindBy root()              default @FindBy();

    /**
     * Locator of the button "Next page" or smth like this.
     * @return
     */
    FindBy nextLocator()       default @FindBy();

    /**
     * Locator of the button "Previous page" or smth like this.
     * @return
     */
    FindBy previousLocator()   default @FindBy();

    /**
     * Locator of the first page.
     * @return
     */
    FindBy firstLocator()      default @FindBy();

    /**
     * Locator of the last page.
     * @return
     */
    FindBy lastLocator()       default @FindBy();

    /**
     * Parametrized locator list of pages. See example.
     * @return
     */
    FindBy pagesLocator()       default @FindBy();
}