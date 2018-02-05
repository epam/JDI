package com.epam.jdi.uitests.win.winnium.elements;

import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.actions.ActionInvoker;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class BaseElement implements IBaseElement {
    private Object parent;
    private String name;
    private String varName;
    private String typeName;

    protected GetElementModule avatar;
    protected ActionInvoker invoker = new ActionInvoker();

    public BaseElement() {
        avatar = new GetElementModule(this);
    }

    public By getLocator() {
        return avatar.getByLocator();
    }
    public void setLocator(By locator) {
        avatar.setByLocator(locator);
    }

    @Override
    public GetElementModule getAvatar() {
        return avatar;
    }

    public WebDriver getDriver() {
        return getAvatar().getDriver();
    }

    public void setWaitTimeout(int seconds) {
        logger.debug("Set wait timeout to " + seconds);
        getDriver().manage().timeouts().implicitlyWait(seconds, SECONDS);
        timeouts.setCurrentTimeoutSec(seconds);
    }

    public void restoreWaitTimeout() {
        setWaitTimeout(timeouts.getDefaultTimeoutSec());
    }

    @Override
    public void setParent(Object parent) {
        this.parent = parent;
    }

    @Override
    public void setName(Field field) {
        this.name = AnnotationsUtil.getElementName(field);
        this.varName = field.getName();
    }

    @Override
    public Object getParent() {
        return parent;
    }

    @Override
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String getName() {
        return name != null ? name : getTypeName();
    }

    @Override
    public String printContext() {
        if (!(parent instanceof BaseElement))
            return "";

        BaseElement parentBaseElement = (BaseElement)parent;
        By locator = parentBaseElement.avatar.getByLocator();
        if (locator == null)
            return "";

        String parentContext = parentBaseElement.printContext();
        if (parentContext.equals(""))
            return locator.toString();

        return locator.toString() + "; " + parentContext;
    }

    @Override
    public String toString() {
        return MessageFormat.format(shortLogMessagesFormat
                        ? "{1} ''{0}'' ({2}.{3}; {4})"
                        : "Name: ''{0}'', Type: ''{1}'' In: ''{2}'', {4}",
                getName(), getTypeName(), getParentName(), getVarName(), avatar);
    }

    private String getParentName() {
        return parent == null ? "" : parent.getClass().getSimpleName();
    }

    private String getTypeName() {
        return (typeName != null) ? typeName : getClass().getSimpleName();
    }

    private String getVarName() {
        return varName != null ? varName : getName();
    }

    public void setAvatar(GetElementModule avatar) {
        this.avatar = avatar;
    }

    public BaseElement setAvatar(IAvatar avatar) {
        this.avatar = (GetElementModule) avatar.copy();
        return this;
    }

    public boolean hasLocator() {
        return avatar.hasLocator();
    }

    public ActionInvoker getInvoker() {
        return invoker;
    }

    @Override
    public void setFunction(Functions function) {
        if (function != Functions.NONE)
            throw JDISettings.exception("Not supported");
    }
}
