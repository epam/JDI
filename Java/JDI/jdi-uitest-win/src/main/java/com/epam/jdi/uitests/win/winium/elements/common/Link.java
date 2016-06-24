package com.epam.jdi.uitests.win.winium.elements.common;
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


import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.win.winium.elements.base.ClickableText;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;

import static com.epam.commons.Timer.getByCondition;
import static com.epam.commons.TryCatchUtil.tryGetResult;
import static java.lang.String.format;

/**
 * Link control implementation
 *
 * @author Roman Iovlev
 */
public class Link extends ClickableText implements ILink {
    public Link() {
    }

    public Link(By byLocator) {
        super(byLocator);
    }

    public Link(WebElement webElement) {
        super(webElement);
    }

    protected String getReferenceAction() {
        return getWebElement().getAttribute("href");
    }

    public final URL getURL(){ return tryGetResult(() -> new URL(getReference())); }

    public final String getReference() {
        return invoker.doJActionResult("Get link reference", this::getReferenceAction, href -> "Get href of link '" + href + "'");
    }

    public final String waitReferenceContains(String text) {
        return invoker.doJActionResult(format("Wait link contains '%s'", text),
                () -> getByCondition(this::getReferenceAction, t -> t.contains(text)));
    }

    public final String waitMatchReference(String regEx) {
        return invoker.doJActionResult(format("Wait link match regex '%s'", regEx),
                () -> getByCondition(this::getReferenceAction, t -> t.matches(regEx)));
    }

    protected String getTooltipAction() {
        return getWebElement().getAttribute("title");
    }

    public final String getTooltip() {
        return invoker.doJActionResult("Get link tooltip", this::getTooltipAction, href -> "Get link tooltip '" + href + "'");
    }
}