package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations;
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


import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Quotes;

import java.util.function.Consumer;

import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.CheckPageTypes.CONTAINS;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.CheckPageTypes.EQUAL;
import static com.epam.jdi.uitests.web.settings.WebSettings.domain;
import static java.lang.String.format;

/**
 * Created by roman.i on 25.09.2014.
 */
public class WebAnnotationsUtil extends AnnotationsUtil {

    public static String getUrlFromUri(String uri, Class<?> parentClass) {
        if (parentClass.isAnnotationPresent(JSite.class))
            domain = parentClass.getAnnotation(JSite.class).domain();
        String siteDomain = domain;
        if (siteDomain == null)
            siteDomain = "";
        return siteDomain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    public static void fillPageFromAnnotaiton(WebPage page, JPage pageAnnotation, Class<?> parentClass) {
        String url = pageAnnotation.url();
        if (!url.contains("://"))
            url = getUrlFromUri(url, parentClass);
        String title = pageAnnotation.title();
        String urlTemplate = pageAnnotation.urlTemplate();
        CheckPageTypes urlCheckType = pageAnnotation.urlCheckType();
        CheckPageTypes titleCheckType = pageAnnotation.titleCheckType();
        if (urlCheckType == EQUAL || urlCheckType == CONTAINS && urlTemplate.equals(""))
            urlTemplate = url;
        page.updatePageData(url, title, urlCheckType, titleCheckType, urlTemplate);
    }

    public static By getFrame(Frame frame) {
        if (frame == null) return null;
        if (!"".equals(frame.id()))
            return By.id(frame.id());
        if (!"".equals(frame.className()))
            return By.className(frame.className());
        if (!"".equals(frame.xpath()))
            return By.xpath(frame.xpath());
        if (!"".equals(frame.css()))
            return By.cssSelector(frame.css());
        if (!"".equals(frame.linkText()))
            return By.linkText(frame.linkText());
        if (!"".equals(frame.name()))
            return By.name(frame.name());
        if (!"".equals(frame.partialLinkText()))
            return By.partialLinkText(frame.partialLinkText());
        if (!"".equals(frame.tagName()))
            return By.tagName(frame.tagName());
        return null;
    }

    public static By findByToBy(FindBy locator) {
        if (locator == null) return null;
        if (!locator.id().isEmpty())
            return By.id(locator.id());
        if (!locator.className().isEmpty())
            return By.className(locator.className());
        if (!locator.xpath().isEmpty())
            return By.xpath(locator.xpath());
        if (!locator.css().isEmpty())
            return By.cssSelector(locator.css());
        if (!locator.linkText().isEmpty())
            return By.linkText(locator.linkText());
        if (!locator.name().isEmpty())
            return By.name(locator.name());
        if (!locator.partialLinkText().isEmpty())
            return By.partialLinkText(locator.partialLinkText());
        if (!locator.tagName().isEmpty())
            return By.tagName(locator.tagName());
        return null;
    }

    public static void fillLocator(FindBy value, Consumer<By> action) {
        By by = findByToBy(value);
        if (by != null)
            action.accept(by);
    }

    public static By findByToBy(Css locator){
        if (locator == null) return null;
        return By.cssSelector(locator.value());
    }

    public static By findByToBy(Attribute locator){
        if (locator == null) return null;
        return getAttribute(locator.name(), locator.value());
    }

    public static By findByToBy(ClassName locator){
        if (locator == null) return null;
        return By.className(locator.value());
    }

    public static By findByToBy(Id locator){
        if (locator == null) return null;
        return By.id(locator.value());
    }

    public static By findByToBy(ByName locator){
        if (locator == null) return null;
        return By.name(locator.value());
    }

    public static By findByToBy(Tag locator){
        if (locator == null) return null;
        return By.tagName(locator.value());
    }
    public static By findByToBy(Type locator){
        if (locator == null) return null;
        return getAttribute("type", locator.value());
    }

    public static By findByToBy(Text locator){
        if (locator == null) return null;
         return By.xpath(".//*/text()[normalize-space(.) = " +
                Quotes.escape(locator.value()) + "]/parent::*");
    }

    public static By findByToBy(NgModel locator){
        if (locator == null) return null;
            return getAttribute("ng-model", locator.value());
    }
    public static By findByToBy(NgBinding locator){
        if (locator == null) return null;
            return getAttribute("ng-binding", locator.value());
    }

    public static By findByToBy(NgRepeat locator){
        if (locator == null) return null;
            return getAttribute("ng-repeat", locator.value());
    }

    public static By findByToBy(Title locator){
        if (locator == null) return null;
            return getAttribute("title", locator.value());
    }

    public static By findByToBy(Value locator){
        if (locator == null) return null;
            return getAttribute("value", locator.value());
    }

    public static By findByToBy(Xpath locator){
        if (locator == null) return null;
            return By.xpath(locator.value());
    }

    public static By findByToBy(JFindBy locator) {
        if (locator == null) return null;

        if (!"".equals(locator.xpath()))
            return By.xpath(locator.xpath());
        if (!"".equals(locator.css()))
            return By.cssSelector(locator.css());
        if (!"".equals(locator.linkText()))
            return By.linkText(locator.linkText());
        if (!"".equals(locator.partialLinkText()))
            return By.partialLinkText(locator.partialLinkText());
        if (!"".equals(locator.tagName()))
            return By.tagName(locator.tagName());

        if (!"".equals(locator.text()))
            return By.xpath(".//*/text()[normalize-space(.) = " +
                Quotes.escape(locator.text()) + "]/parent::*");

        if (!"".equals(locator.attribute().name()))
            return getAttribute(locator.attribute().name(), locator.attribute().value());
        if (!"".equals(locator.id()))
            return By.id(locator.id());
        if (!"".equals(locator.className()))
            return By.className(locator.className());
        if (!"".equals(locator.name()))
            return By.name(locator.name());
        if (!"".equals(locator.value()))
            return getAttribute("value", locator.value());
        if (!"".equals(locator.title()))
            return getAttribute("title", locator.title());
        if (!"".equals(locator.type()))
            return getAttribute("type", locator.title());
        if (!"".equals(locator.model()))
            return getAttribute("ng-model", locator.model());
        if (!"".equals(locator.binding()))
            return getAttribute("ng-binding", locator.binding());
        if (!"".equals(locator.repeat()))
            return getAttribute("ng-repeat", locator.repeat());
        return null;
    }

    private static By getAttribute(String name, String value) {
        return By.cssSelector(format("[%s='%s']", name, value));
    }
}