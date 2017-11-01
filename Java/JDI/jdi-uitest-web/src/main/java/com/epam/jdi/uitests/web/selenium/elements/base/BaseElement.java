package com.epam.jdi.uitests.web.selenium.elements.base;
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


import com.epam.commons.LinqUtils;
import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit;
import com.epam.jdi.uitests.web.selenium.elements.actions.ActionInvoker;
import com.epam.jdi.uitests.web.selenium.elements.actions.ActionScenrios;
import com.epam.jdi.uitests.web.selenium.elements.actions.ElementsActions;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.GetElement;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.*;
import static com.epam.commons.StringUtils.namesEqual;
import static com.epam.jdi.uitests.core.logger.LogLevels.INFO;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.getByLocator;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public abstract class BaseElement implements IBaseElement {

    public static ActionScenrios actionScenrios = new ActionScenrios();
    public static BiConsumer<String, Consumer<String>> doActionRule = (text, action) -> {
        if (text == null) return;
        action.accept(text);
    };
    public static BiConsumer<String, Consumer<String>> setValueEmptyAction = (text, action) -> {
        if (text == null || text.equals("")) return;
        action.accept(text.equals("#CLEAR#") ? "" : text);
    };
    public Functions function = Functions.NONE;
    public void setFunction(Functions function) { this.function = function; }
    public GetElementModule avatar;
    public ActionInvoker invoker = new ActionInvoker(this);
    protected GetElement getElementClass = new GetElement(this);
    protected ElementsActions actions = new ElementsActions(this);
    private String name;
    private String varName;
    private String typeName;
    private Object parent;
    private String preposition;
    public boolean useCache;

    public BaseElement() {
        this(By.id("EMPTY"));
    }

    public BaseElement(By byLocator) {
        avatar = new GetElementModule(byLocator == null || getByLocator(byLocator).equals("EMPTY")
            ? null
            : byLocator, this);
        useCache = USE_CACHE;
    }

    public static void setActionScenarios(ActionScenrios actionScenrios) {
        BaseElement.actionScenrios = actionScenrios;
    }

    public BaseElement init(IBaseElement parent) {
        new WebCascadeInit().initElements(this, ((GetElementModule)parent.getAvatar()).getDriverName());
        setAvatar(parent.getAvatar());
        setParent(parent);

        return this;
    }
    public BaseElement init(Object parent, IAvatar avatar) {
        new WebCascadeInit().initElements(this, ((GetElementModule)avatar).getDriverName());
        setAvatar(avatar);
        setParent(parent);
        return this;
    }
    public static void setValueRule(String text, Consumer<String> action) {
        doActionRule.accept(text, action);
    }

    /**
     * @return Get Element’s name
     */
    public String getName() {
        return name != null ? name : getTypeName();
    }

    public void setName(Field field) {
        name = WebAnnotationsUtil.getElementName(field);
        varName = field.getName();
    }
    protected void initSubElements() {
        new WebCascadeInit().initElements(this, getAvatar().getDriverName());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVarName() {
        return varName != null ? varName : getName();
    }

    protected Timer timer() {
        return avatar.timer();
    }
    protected Timer timer(int sec) {
        return avatar.timer(sec);
    }
    protected JavascriptExecutor jsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public boolean hasLocator() {
        return avatar.hasLocator();
    }

    /**
     * @return Get WebDriver associated with Element
     */
    public WebDriver getDriver() {
        return avatar.getDriver();
    }

    /**
     * @return Get Element’s locator
     */
    public By getLocator() {
        return avatar.getLocator();
    }

    public GetElementModule getAvatar() {
        return avatar;
    }

    public BaseElement setAvatar(IAvatar avatar) {
        this.avatar = (GetElementModule) avatar.copy();
        return this;
    }
    public BaseElement setAvatar(By byLocator) {
        this.avatar = this.avatar.copy(byLocator);
        return this;
    }

    public BaseElement setAvatar(By byLocator, GetElementModule avatar) {
        this.avatar = avatar.copy(byLocator);
        return this;
    }

    public void setWaitTimeout(int seconds) {
        logger.debug("Set wait timeout to " + seconds);
        getDriver().manage().timeouts().implicitlyWait(seconds, SECONDS);
        timeouts.setCurrentTimeoutSec(seconds);
    }

    public void restoreWaitTimeout() {
        setWaitTimeout(timeouts.getDefaultTimeoutSec());
    }

    protected String getTypeName() {
        return (typeName != null) ? typeName : getClass().getSimpleName();
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    protected String getParentName() {
        return parent == null ? "" : parent.getClass().getSimpleName();
    }
    public String getPreposition() {
        return preposition == null ? "" : preposition;
    }
    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }
    public Object getParent() { return parent; }
    public String printContext() {
        By locator;
        BaseElement parent;
        String parentContext;
        return getParent() == null || !isClass(getParent().getClass(), BaseElement.class)
                || (locator = (parent = (BaseElement)getParent()).getLocator()) == null
            ? ""
            : ((parentContext = parent.printContext()).equals(""))
                ? locator.toString()
                : locator + "; " + parentContext;
    }
    public void setParent(Object parent) { this.parent = parent; }

    public void logAction(String actionName, LogLevels level) {
        toLog(format(shortLogMessagesFormat
                ? "%s for %s"
                : "Perform action '%s' with Element (%s)", actionName, this.toString()), level);
    }

    public void logAction(String actionName) {
        logAction(actionName, INFO);
    }

    public <T> T asEntity(Class<T> entityClass) {
        try {
            T data = newEntity(entityClass);
            foreach(getFields(this, IHasValue.class), item -> {
                Field field = LinqUtils.first(getFields(data, String.class), f ->
                        namesEqual(f.getName(), item.getName()));
                if (field == null)
                    return;
                try {
                    field.set(data, ((IHasValue) getValueField(item, this)).getValue());
                } catch (Exception ignore) { }
            });
            return data;
        } catch (Exception ex) {
            throw exception("Can't get entity from Form" + getName() + " for class: " + entityClass.getClass());
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format(shortLogMessagesFormat
                        ? "{1} ''{0}'' ({2}.{3}; {4})"
                        : "Name: ''{0}'', Type: ''{1}'' In: ''{2}'', {4}",
                getName(), getTypeName(), getParentName(), getVarName(), avatar);
    }
}