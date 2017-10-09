package com.epam.jdi.uitests.gui.sikuli.elements.base;

import com.epam.commons.LinqUtils;
import com.epam.commons.TryCatchUtil;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.gui.sikuli.elements.BaseElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import ru.yandex.qatools.allure.annotations.Step;

import java.awt.*;
import java.lang.reflect.Field;

import static com.epam.commons.LinqUtils.foreach;
import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.commons.StringUtils.namesEqual;
import static com.epam.jdi.uitests.core.logger.LogLevels.DEBUG;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.lang.String.format;

/**
 * Created by Natalia_Grebenshchik on 1/14/2016.
 */
public class Element extends BaseElement implements IElement {

    public Element() {
        super();
    }

    public Element(String filePath) {
    }

    public Element(Pattern pattern) {
        super(pattern);
    }

    @Step
    public Element getElement() {
        return invoker.doJActionResult("Get gui element",
                () -> this != null ? this : avatar.getElement(), DEBUG);
    }

    public static <T> Class<T> checkEntityIsNotNull(Class<T> entityClass) {
        if (entityClass == null)
            throw new IllegalArgumentException("Entity type was not specified");
        return entityClass;
    }

    public static <T> T newEntity(Class<T> entityClass) {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception("Can't instantiate %s. You must have empty constructor to do this",
                    entityClass.getSimpleName());
        }
    }

    public static <T> T extractEntity(Class<T> entityClass, BaseElement el) {
        try {
            T data = newEntity(entityClass);
            foreach(getFields(el, IHasValue.class), item -> {
                Field field = LinqUtils.first(getFields(data, String.class), f ->
                        namesEqual(f.getName(), item.getName()));
                if (field == null)
                    return;
                try {
                    field.set(data, ((IHasValue) getValueField(item, el)).getValue());
                } catch (Exception ignore) { }
            });
            return data;
        } catch (Exception ex) {
            throw exception("Can't get entity from Form" + el.getName() + " for class: " + entityClass.getClass());
        }
    }

    public void rightClick() {
        invoker.doJAction("Right click on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().rightClick(this.getPattern()));
        });
    }

    public void doubleClick() {
        invoker.doJAction("Double click on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().doubleClick(this.getPattern()));
        });
    }

    protected boolean isDisplayedAction() {
        return this.getParentRegion().exists(this.getPattern()) != null;
    }

    /**
     * @return Check is Element visible
     */
    @Override
    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }

    /**
     * @return Check is Element hidden
     */
    @Override
    public boolean isHidden() {
        return actions.isDisplayed(() -> !isDisplayedAction());
    }

    /**
     * Waits while Element becomes invisible
     */
    @Override
    public void waitVanished() {
        if (timer().wait(() -> isDisplayed()))
            throw new RuntimeException(String.format("Element '%s' not vanished after timeout", this.getName()));
    }

    /**
     * Waits while Element becomes visible
     */
    @Override
    public void waitDisplayed() {
        if (timer().wait(this::isHidden))
            throw new RuntimeException(String.format("Element '%s' not displayed after timeout", this.getName()));
    }

    public void highlight(HighlightSettings highlightSettings) {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().find(this.getPattern())
                .highlight(highlightSettings.getTimeoutInSec(), highlightSettings.getFrameColor()));
    }

    public void highlight() {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().find(this.getPattern()).highlight());
    }

    public void clickWithKeys(int... keys) {
        invoker.doJAction("Ctrl click on Element", () -> {
            Robot robot = null;
            try {
                robot = new Robot();
                for (int key : keys)
                    robot.keyPress(key);
                this.getParentRegion().click(this.getPattern());

            } catch (Exception | Error e) {
            } finally {
                for (int key : keys)
                    robot.keyRelease(key);
            }
        });

    }

    public void mouseOver() {
        invoker.doJAction("Move mouse over Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().mouseMove(this.getPattern()));
        });
    }

    public void focus() {
        invoker.doJAction("Focuce on Element", () -> {
            TryCatchUtil.tryGetResult(() -> this.getParentRegion().mouseMove(this.getPattern()));
        });
    }

    public void selectArea(int x1, int y1, int x2, int y2) {
        invoker.doJAction(format("Select area: from %d,%d;to %d,%d", x1, y1, x2, y2), () -> {

            TryCatchUtil.tryGetResult(() -> new Screen().dragDrop(new Region(x1, y2, 1, 1), new Region(x2, y2, 1, 1)));

        });
    }

    public void selectArea(Element e1, Element e2) {
        invoker.doJAction(format("Select are: from element '%s' ti '%s", e1.getName(), e2.getName()),
                () -> TryCatchUtil.tryGetResult(() -> this.getParentRegion().dragDrop(e1.getPattern(), e2.getPattern())));
    }

    public void dragAndDropBy(Element e) {
        invoker.doJAction(format("Drag and drop Element '%s' to element '%s'", this.getName(), e.getName()),
                () -> TryCatchUtil.tryGetResult(() -> this.getParentRegion().dragDrop(this.getPattern(), e.getPattern())));
    }

    //To  Move to diff place
    /**
     * Get element attribute
     *
     * @param name Specify name for attribute
     * @return Returns chosen attribute
     */
    public String getAttribute(String name) {
        return null;
    }

    /**
     * @param name  Specify attribute name
     * @param value Specify attribute value
     * Waits while attribute gets expected value. Return false if this not happens
     */
    public void waitAttribute(String name, String value) {

    }

    /**
     * @param attributeName Specify attribute name
     * @param value         Specify attribute value
     *                      Sets attribute value for Element
     */
    public void setAttribute(String attributeName, String value) {

    }
}
