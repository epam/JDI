package com.epam.jdi.uitests.gui.sikuli.elements;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.gui.sikuli.elements.actions.ActionInvoker;
import com.epam.jdi.uitests.gui.sikuli.elements.actions.ActionScenarios;
import com.epam.jdi.uitests.gui.sikuli.elements.actions.ElementsActions;
import com.epam.jdi.uitests.gui.sikuli.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.GuiAnnotationsUtil;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import java.awt.Rectangle;

import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.jdi.uitests.core.logger.LogLevels.INFO;
import static com.epam.jdi.uitests.core.settings.JDISettings.shortLogMessagesFormat;
import static com.epam.jdi.uitests.core.settings.JDISettings.toLog;
import static java.lang.String.format;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
public abstract class BaseElement implements IBaseElement {

    public GetElementModule avatar;
    public static ActionScenarios actionScenarios = new ActionScenarios();
    protected ElementsActions actions = new ElementsActions(this);
    public ActionInvoker invoker = new ActionInvoker(this);
    public static boolean createFreeInstance = false;
    public Functions function = Functions.NONE;
    public void setFunction(Functions function) { this.function = function; }
    protected String parentTypeName = "";
    private String name;
    private String varName;
    private String typeName;

    public BaseElement() {
        this(null);
    }
    public BaseElement(Pattern pattern) {
        avatar = new GetElementModule(pattern, this);
        if (isInterface(getClass(), IComposite.class) && !createFreeInstance && CascadeInit.firstInstance)
            CascadeInit.initElements(this, avatar.getDriverName());
        MapInterfaceToElement.init(seleniumDefaultMap());
    }

    private static Object[][] seleniumDefaultMap() {
        return new Object[][]{
                {IElement.class, Element.class}
        };
    }

    public void setName(Field field) {
        this.name = GuiAnnotationsUtil.getElementName(field);
        this.varName = field.getName();
    }
    public GetElementModule getAvatar() {
        return avatar;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setParentName(String parentName) {
        parentTypeName = parentName;
    }

    public String getName() {
        return  this.name;
    }

    public void logAction(String actionName, LogLevels level) {
        toLog(format(shortLogMessagesFormat
                ? "%s for %s"
                : "Perform action '%s' with Element (%s)", actionName, this.toString()), level);
    }

    public void logAction(String actionName) {
        logAction(actionName, INFO);
    }

    public Pattern getPattern() {
        return avatar.pattern;
    }

    public Element getParent() {
        //todo to have link to the parent
        return new Element();
    }

    public Rectangle getRectangle() {
        return this.avatar.getRectangle();
    }

    public Region getParentRegion() {
        Rectangle parentRectangle = this.getParent().getRectangle();

        if (parentRectangle == null || parentRectangle.getHeight() == 0 || parentRectangle.getWidth() == 0)
            return new Screen();

        return new Region(parentRectangle);
    }

    @Override
    public String toString() {
        return MessageFormat.format(shortLogMessagesFormat
                        ? "{0}"
                        : "Name: {0}", this.getName());
    }

    protected Timer timer() {
        return avatar.timer();
    }
}
