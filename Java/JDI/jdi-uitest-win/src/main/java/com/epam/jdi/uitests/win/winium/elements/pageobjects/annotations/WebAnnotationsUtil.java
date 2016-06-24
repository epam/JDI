package com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations;
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
import com.epam.jdi.uitests.win.winium.elements.composite.CheckPageTypes;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.function.Consumer;

import static com.epam.jdi.uitests.win.settings.WinSettings.*;

/**
 * Created by roman.i on 25.09.2014.
 */
public class WebAnnotationsUtil extends AnnotationsUtil {

    public static void fillPageFromAnnotaiton(WebPage page, JPage pageAnnotation, Class<?> parentClass) {
        String url = pageAnnotation.url();
        if (!hasDomain() && parentClass != null && parentClass.isAnnotationPresent(JApp.class))
            domain = parentClass.getAnnotation(JApp.class).domain();
        url = url.contains("://") || parentClass == null || !hasDomain()
                ? url
                : WebPage.getUrlFromUri(url);
        String title = pageAnnotation.title();
        String urlTemplate = pageAnnotation.urlTemplate();
        if (urlTemplate != null && !urlTemplate.equals(""))
            urlTemplate = (urlTemplate.contains("://") || parentClass == null || !hasDomain())
                    ? urlTemplate
                    : WebPage.getMatchFromDomain(urlTemplate);
        CheckPageTypes urlCheckType = pageAnnotation.urlCheckType();
        CheckPageTypes titleCheckType = pageAnnotation.titleCheckType();
        if (urlCheckType == CheckPageTypes.MATCH || urlCheckType == CheckPageTypes.CONTAIN && (urlTemplate == null || urlTemplate.equals("")))
            urlTemplate = url;
        page.updatePageData(url, title, urlCheckType, titleCheckType, urlTemplate);
    }

    private static String getUrlFromDomain(Object parent, String uri) {
        domain = parent.getClass().getAnnotation(JApp.class).domain();
        return WebPage.getUrlFromUri(uri);
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
        if (!"".equals(locator.id()))
            return By.id(locator.id());
        if (!"".equals(locator.className()))
            return By.className(locator.className());
        if (!"".equals(locator.xpath()))
            return By.xpath(locator.xpath());
        if (!"".equals(locator.css()))
            return By.cssSelector(locator.css());
        if (!"".equals(locator.linkText()))
            return By.linkText(locator.linkText());
        if (!"".equals(locator.name()))
            return By.name(locator.name());
        if (!"".equals(locator.partialLinkText()))
            return By.partialLinkText(locator.partialLinkText());
        if (!"".equals(locator.tagName()))
            return By.tagName(locator.tagName());
        return null;
    }


    public static void fillLocator(FindBy value, Consumer<By> action) {
        By by = findByToBy(value);
        if (by != null)
            action.accept(by);
    }

    public static By getFindByLocator(JFindBy locator) {
        if (locator == null) return null;
        if (!"".equals(locator.id()))
            return By.id(locator.id());
        if (!"".equals(locator.className()))
            return By.className(locator.className());
        if (!"".equals(locator.xpath()))
            return By.xpath(locator.xpath());
        if (!"".equals(locator.css()))
            return By.cssSelector(locator.css());
        if (!"".equals(locator.linkText()))
            return By.linkText(locator.linkText());
        if (!"".equals(locator.name()))
            return By.name(locator.name());
        if (!"".equals(locator.partialLinkText()))
            return By.partialLinkText(locator.partialLinkText());
        if (!"".equals(locator.tagName()))
            return By.tagName(locator.tagName());
        return null;
    }
}