package com.epam.jdi.uitests.win.winium.elements.base;
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


import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winium.elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Function;

import static com.epam.jdi.uitests.core.logger.LogLevels.DEBUG;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static java.lang.String.format;

/**
 * Base Element control implementation
 *
 * @author Alexeenko Yan
 * @author Belin Yury
 * @author Belousov Andrey
 * @author Shubin Konstantin
 * @author Zharov Alexandr
 */
public class Element extends BaseElement implements IElement {

    public Element() {
        super();
    }

    public Element(By byLocator) {
        super(byLocator);
    }

    public Element(WebElement webElement) {
        avatar.setWebElement(webElement);
    }

    public static <T extends Element> T copy(T element, By newLocator) {
        try {
            T result = (T) element.getClass().newInstance();
            result.setAvatar(newLocator, element.getAvatar());
            return result;
        } catch (Exception ex) {
            throw JDISettings.exception("Can't copy Element: " + element);
        }
    }

    /**
     * Specified Selenium Element for this Element
     */
    @JDIAction
    public WebElement getWebElement() {
        return invoker.doJActionResult("Get web element",
                () -> avatar.getElement(), DEBUG);
    }
    public void setWebElement(WebElement webElement) {
        avatar.setWebElement(webElement);
    }
    public String getAttribute(String name) {
        return getWebElement().getAttribute(name);
    }

    public void waitAttribute(String name, String value) {
        wait(el -> el.getAttribute(name).equals(value));
    }

    public void setAttribute(String attributeName, String value) {
        invoker.doJAction(format("Set Attribute '%s'='%s'", attributeName, value),
                () -> jsExecutor().executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", attributeName),
                        getWebElement(), value));
    }



    protected boolean isDisplayedAction() {
        return actions.findImmediately(() -> getWebElement().isDisplayed(), false);
    }
    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }

    protected void waitDisplayedAction() {
        wait(WebElement::isDisplayed);
    }

    public boolean isHidden() {
        return actions.isDisplayed(() -> !isDisplayedAction());
    }

    public void waitDisplayed() {
        actions.waitDisplayed(getWebElement()::isDisplayed);
    }

    public void waitVanished() {
        actions.waitVanished(() -> timer().wait(() -> !isDisplayedAction()));
    }

    public WebElement getInvisibleElement() {
        avatar.searchAll();
        return getWebElement();
    }

    /**
     * @param resultFunc Specify expected function result
     * Waits while condition with WebElement happens during specified timeout and returns result using resultFunc
     */
    @JDIAction
    public void wait(Function<WebElement, Boolean> resultFunc) {
        boolean result = wait(resultFunc, r -> r);
        asserter.isTrue(result);
    }

    /**
     * @param resultFunc Specify expected function result
     * @param condition  Specify expected function condition
     * @return Waits while condition with WebElement happens and returns result using resultFunc
     */
    @JDIAction
    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition) {
        return timer().getResultByCondition(() -> resultFunc.apply(getWebElement()), condition::apply);
    }

    /**
     * @param resultFunc Specify expected function result
     * @param timeoutSec Specify timeout
     * Waits while condition with WebElement happens during specified timeout and returns wait result
     */
    @JDIAction
    public void wait(Function<WebElement, Boolean> resultFunc, int timeoutSec) {
        boolean result = wait(resultFunc, r -> r, timeoutSec);
        asserter.isTrue(result);
    }

    /**
     * @param resultFunc Specify expected function result
     * @param timeoutSec Specify timeout
     * @param condition  Specify expected function condition
     * @return Waits while condition with WebElement and returns wait result
     */
    @JDIAction
    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition, int timeoutSec) {
        setWaitTimeout(timeoutSec);
        T result = new Timer(timeoutSec).getResultByCondition(() -> resultFunc.apply(getWebElement()), condition::apply);
        restoreWaitTimeout();
        return result;
    }

    public void highlight() {
        WinSettings.driverFactory.highlight(this);
    }

    public void highlight(HighlightSettings highlightSettings) {
        WinSettings.driverFactory.highlight(this, highlightSettings);
    }

    public void clickWithKeys(Keys... keys) {
        invoker.doJAction("Ctrl click on Element",
                () -> {
                    Actions action = new Actions(getDriver());
                    for (Keys key : keys)
                        action = action.keyDown(key);
                    action = action.moveToElement(getWebElement()).click();
                    for (Keys key : keys)
                        action = action.keyUp(key);
                    action.perform();
                });
    }

    public void doubleClick() {
        invoker.doJAction("Double click on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.doubleClick(getWebElement()).perform();
        });
    }

    public void rightClick() {
        invoker.doJAction("Right click on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.contextClick(getWebElement()).perform();
        });
    }

    public void clickCenter() {
        invoker.doJAction("Click in Center of Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.click(getWebElement()).perform();
        });
    }

    public void mouseOver() {
        invoker.doJAction("Move mouse over Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.moveToElement(getWebElement()).build().perform();
        });
    }

    public void focus() {
        invoker.doJAction("Focus on Element", () -> {
            Dimension size = getWebElement().getSize(); //for scroll to object
            new Actions(getDriver()).moveToElement(getWebElement(), size.width / 2, size.height / 2).build().perform();
        });
    }

    public void selectArea(int x1, int y1, int x2, int y2) {
        invoker.doJAction(format("Select area: from %d,%d;to %d,%d", x1, y1, x2, y2), () -> {
            WebElement element = getWebElement();
            new Actions(getDriver()).moveToElement(element, x1, y1).clickAndHold()
                    .moveToElement(element, x2, y2).release().build().perform();
        });
    }

    public void dragAndDropBy(int x, int y) {
        invoker.doJAction(format("Drag and drop Element: (x,y)=(%s,%s)", x, y), () ->
                new Actions(getDriver()).dragAndDropBy(getWebElement(), x, y).build().perform());
    }

}